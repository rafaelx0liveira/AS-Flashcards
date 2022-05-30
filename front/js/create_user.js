const base_url = 'http://127.0.0.1:8080/users'

const form = document.getElementById('create');

async function create_user() {

  const body_data = {
    name: form.elements['nome'].value,
    email: form.elements['email'].value,
    password: form.elements['senha'].value
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
  
  if (response === 201) {
    location.replace("../login.html");
  } else {
    location.replace("../creat.html");
  }
}