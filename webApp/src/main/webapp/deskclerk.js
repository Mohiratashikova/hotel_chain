var guestID = [1, 2, 3]


function removeB(id){
    $.ajax({
        url: 'deleteBooking?res=' + id,
    }).done(function(){
        location.reload()
    })
}


