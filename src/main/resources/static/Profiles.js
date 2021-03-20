let SERVER_URL = "http://localhost:8080/api/portfolios";

const updatePortfoliosBoard = () => {
    $.ajax({
        url: SERVER_URL
    }).then(function(data) {
        $('#portfolioBoard-body').empty();
        data.forEach(function(row) {
            $('#portfolioBoard-body').append(
                '<tr>' +
                '<td>' + row.id + '</td>' +
                '<td>' + row.twitterUserName + '</td>' +
                '<td>' + row.title + '</td>'
            );
        });
    });
}

const getProfile = (id, name) => {
    $.ajax({
        url: SERVER_URL + "/" + id,
        async: false,
        success: function(data) {
            $('#avatar').empty();
            $('#avatar').append('' +
                '<img src="' + data.portfolio.imageUrl + '" ' +
                'className="rounded float-start" height="230px" width="200px" >');

            $('.list-group').empty();
            $('.list-group').append('<h5>' + name + '\'s Timeline</h5>');
            data.twtTweets.forEach(function(row) {
                $('.list-group').append('<li class="list-group-item d-flex">' + row.text  + '<span>' + row.createdAt +
                    '</span></li>'
                );
            });

            $('#profile').empty();
            $('#profile').append('<h1>' + name + '</h1>');
            $('#profile').append('<br><h5>My work experience</h5>');
            $('#profile').append('<br><p>' + data.portfolio.description + '</p><br>')
            $('#profile').append('<button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#editProfileModal">Edit</button>');
        }
    });
}



$(document).ready(function() {

    updatePortfoliosBoard();

    $("#refresh-portfolioBoard").click(function( event ) {
        updatePortfoliosBoard();
    });


    $("#portfoliosId").on('click', '#portfolioBoard-body tr', function(){
        console.log(this);
        let td = $(this).find('td');
        getProfile($(td[0]).text(), $(td[2]).text());

    });


});