const baseUrl = 'http://127.0.0.1:8080/users'

const form = document.getElementById('signup');

function loginEndpoint() {

    // var form = new FormData 
    fetch(baseUrl + "/login", {
        method: 'POST',
        headers: new Headers({
          //'Accept': 'application/json',
          'Content-Type': 'application/json'
        }),
        body: JSON.stringify ({
          name:"",
          email: form.elements['email'].value,
          password: form.elements['senha'].value
        })})
      .then(response =>
        {
          if(response.status === 200) {
            location.replace("../workspace.html")
          }
        } 
      )
      .catch(error => {
        console.log('There has been a problem with your fetch operation: ' + error.message)
      });

}