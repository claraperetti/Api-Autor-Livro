from locust import HttpUser, task, between
import random
import string

def gerar_email():
    nome = ''.join(random.choices(string.ascii_lowercase, k=6))
    return f"{nome}@teste.com"


class UserTest(HttpUser):
    wait_time = between(1, 2)

    @task
    def fluxo(self):
        response = self.client.post("/auth/register", json={
            "email": gerar_email(),
            "password": "123456"
        })

        if response.status_code == 200:
            user = response.json()
            api_key = user["apiKey"]

            self.client.get(
                "/auth/users",
                headers={"x-api-key": api_key}
            )