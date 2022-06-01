const base_url = "http://127.0.0.1:8080/flashcards";

const card = document.querySelector(".card__inner");
const form = document.getElementById("formulario-flashcard");

card.addEventListener("click", function (e) {
  card.classList.toggle("is-flipped");
  //getFlashcard();
  //getDailyFlashcards();
  //getAllFlashcardsFromUser();
  //createFlashcard();
  //deleteFlashcard();
});

async function getFlashcard() {
  const flashcard_id = "077bf0d3-b541-489b-af33-c2ef9705aeb9"; // pegar do form
  const user_id = readCookie("user_id");
  const url = base_url + "/" + flashcard_id;

  const options = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      "user-id": user_id,
    },
  };

  const response = await fetch(url, options);
  const data = await response.json();
}

async function getDailyFlashcards() {
  const url = base_url + "/all-daily";
  const user_id = readCookie("user_id");

  const options = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      "user-id": user_id,
    },
  };

  const response = await fetch(url, options);
  const data = await response.json();
  console.log(data);
}

async function getAllFlashcardsFromUser() {
  const url = base_url + "/all-from-user";
  const user_id = readCookie("user_id");

  const options = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      "user-id": user_id,
    },
  };

  const response = await fetch(url, options);
  const data = await response.json();
  console.log(data);
}

async function createFlashcard() {
  const url = base_url;
  const user_id = readCookie("user_id");

  const body_data = {
    front: document.getElementById('pergunta').value,
    back: document.getElementById('resposta').value
  };

  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "user-id": user_id,
    },
    body: JSON.stringify(body_data),
  };

  const response = await fetch(url, options);
  const data = await response.json();
  console.log(data);
}


// ============================================================================

let flashcard_being_played;

async function pickFlashcard() {

  const user_id = readCookie("user_id");
  const url = base_url + "/pick";

  const options = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      "user-id": user_id,
    },
  };

  const response = await fetch(url, options);
  const flashcard = await response.json();

  flashcard_being_played = flashcard;
  console.log(flashcard);
}

async function playFlashcard(boolean) {

  const flashcard_id = flashcard_being_played.id;
  const url = base_url + "/" + flashcard_id;

  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "is-correct": boolean,
    },
  };

  const response = await fetch(url, options);
  const data = await response.json();
  console.log(data);
}

async function waitTrueOrFalseAnswer(value) {

  if (value === "true") {
    playFlashcard(true);
  } else if (value === "false") {
    playFlashcard(false);
  }

}

async function deleteFlashcard() {
  const flashcard_id = "dc6b29d9-7f90-483d-9e13-630a89d969d5"; // pegar do form
  const url = base_url + "/" + flashcard_id;

  const options = {
    method: "DELETE",
    headers: {
      mode: 'no-cors',
      "Access-Control-Allow-Origin": "*",
      "Content-Type": "application/json"
    },
  };

  const response = await fetch(url, options);
  const data = await response.json();
  console.log(data);
}

function readCookie(name) {
  var i,
    c,
    ca,
    nameEQ = name + "=";
  ca = document.cookie.split(";");
  for (i = 0; i < ca.length; i++) {
    c = ca[i];
    while (c.charAt(0) == " ") {
      c = c.substring(1, c.length);
    }
    if (c.indexOf(nameEQ) == 0) {
      return c.substring(nameEQ.length, c.length);
    }
  }
  return ca[1];
  // get the user id -> var user_id = readCookie("user_id");
}

function on_off(div) {
  var divbox = document.getElementById(criar - flashcard);

  if (divbox.style.visibility == "hidden") {
    divbox.style.visibility = "visible";
  } else {
    divbox.style.visibility = "hidden";
    c08921b2c0d568e2c7a6a1062d1e878c349fc4;
  }
}

let userSection = document.querySelector(".acerto-erro");
let isShow = true;

function on_off() {
  userSection.classList.toggle("hide");
}
