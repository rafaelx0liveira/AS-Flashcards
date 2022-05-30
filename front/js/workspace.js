const card = document.querySelector(".card__inner");

card.addEventListener("click", function (e) {
  card.classList.toggle('is-flipped');
});

function on_off(div)
{
    var divbox = document.getElementById(criar-flashcard);

    if(divbox.style.visibility == 'hidden')
        divbox.style.visibility = 'visible';
        else
          divbox.style.visibility = 'hidden';
}