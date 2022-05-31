const base_url = 'http://127.0.0.1:8080/users'

const form = document.getElementById('create');

async function create_user() {

  const body_data = {
    name: document.getElementById("nome").value,
    email: document.getElementById("email").value,
    password: document.getElementById("senha").value
  }

  const options = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(body_data)
  };

  const response = await fetch(base_url, options);
  const data = await response.json();

  if (response.status === 201) {
    location.replace("../login.html");
  } else {
    location.replace("../creat-user.html");
  }
}