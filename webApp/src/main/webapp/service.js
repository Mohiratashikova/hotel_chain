var workers = [
    {
        username: "deskclerk1",
        password: "secret1"    
    },
    {
        username: "deskclerk2",
        password: "secret2"    
    }
]

function getInfo() {

    var username = document.getElementById("username").value
    var password = document.getElementById("password").value

    for(i = 0; i < workers.length; i ++){
        if(username == workers[i].username && password == workers[i].password){
            window.location.href = 'deskclerk.jsp'
            return
        }
    }
    alert("Invalid data. Please try again!")
}