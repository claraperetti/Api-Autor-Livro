from locust import HttpUser, task, between
import random
import string
import time


def random_email():
    suffix = ''.join(random.choices(string.ascii_lowercase + string.digits, k=8))
    timestamp = int(time.time() * 1000)
    return f"mitsuo_{suffix}_{timestamp}@teste.com"


class UserAutorLoadTest(HttpUser):
    wait_time = between(1, 2)

    @task
    def register_and_list_authors(self):
        payload = {
            "email": random_email(),
            "password": "123456"
        }

        with self.client.post(
            "/auth/register",
            json=payload,
            name="POST /auth/register",
            catch_response=True
        ) as post_response:

            if post_response.status_code not in [200, 201]:
                post_response.failure(
                    f"Falha no cadastro. Status: {post_response.status_code} | Body: {post_response.text}"
                )
                return

            try:
                response_json = post_response.json()
                api_key = response_json.get("apiKey")
            except Exception as e:
                post_response.failure(f"Resposta JSON inválida no cadastro: {e}")
                return

            if not api_key:
                post_response.failure("Cadastro retornou sucesso, mas sem apiKey.")
                return

        headers = {"X-API-KEY": api_key}

        with self.client.get(
            "/autores",
            headers=headers,
            name="GET /autores",
            catch_response=True
        ) as get_response:

            if get_response.status_code != 200:
                get_response.failure(
                    f"Falha ao listar autores. Status: {get_response.status_code} | Body: {get_response.text}"
                )
                return

            get_response.success()