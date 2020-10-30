const tab = document.querySelectorAll('.tab');

$('.nav ul li').click(function() {
    $(this).addClass("active").siblings().removeClass("active");
})

tabs(0);


$(".clear").click(function() {
    document.getElementById("form").reset();
});


const form = document.getElementById('form');

function tabs(panelIndex) {
    tab.forEach(function(node) {
        node.style.display = 'none';
    });
    tab[panelIndex].style.display = 'block';
}



