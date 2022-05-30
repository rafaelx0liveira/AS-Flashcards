const base_url = 'http://127.0.0.1:8080/users'

const form = document.getElementById('signup');

async function auth() {

  const body_data = {
    name: "",
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

  const response = await fetch(base_url + '/login', options);
  const data = await response.json();
  
  if (response.ok) {
    writeCookie("user_id", data.id, 1);
    location.replace("../workspace.html");
  } else {
    location.replace("./login.html");
  }
}

function writeCookie(name, value, days) {
  var date, expires;
  if (days) {
    date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    expires = "; expires=" + date.toGMTString();
  } else {
    expires = "";
  }
  document.cookie = name + "=" + value + expires + "; path=/";
}