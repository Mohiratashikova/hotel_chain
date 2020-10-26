$('.nav ul li').click(function() {
    $(this).addClass("active").siblings().removeClass("active");
})

const tabBtn = document.querySelector('.nav ul li');
const tab = document.querySelectorAll('.tab');

function tabs(panelIndex) {
    tab.forEach(function(node) {
        node.style.display = 'none';
    });
    tab[panelIndex].style.display = 'block';
}

tabs(0);

let bio = document.querySelector('.bio');

$('#delete1').click(function(){
    $("div").remove("#d1");
});

$('#delete2').click(function(){
    $("div").remove("#d2");
});

$('#delete3').click(function(){
    $("div").remove("#d3");
});

//create a function that clears the null value in the personal info sections: email, phone, 
//can leave just a whitespace






