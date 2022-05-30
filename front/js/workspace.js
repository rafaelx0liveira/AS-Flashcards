const card = document.querySelector(".card__inner");

card.addEventListener("click", function (e) {
  card.classList.toggle('is-flipped');
});

function readCookie(name) {
  var i, c, ca, nameEQ = name + "=";
  ca = document.cookie.split(';');
  for (i = 0; i < ca.length; i++) {
    c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1, c.length);
    }
    if (c.indexOf(nameEQ) == 0) {
      return c.substring(nameEQ.length, c.length);
    }
  }
  return ca[1]; 
  // get the user id -> var user_id = readCookie("user_id");
}