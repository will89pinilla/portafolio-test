let SERVER_URL = "http://localhost:8080/api/portfolios";
let currentPortfolio = {};

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
};


const getProfile = (id, name) => {
    $.ajax({
        url: SERVER_URL + "/" + id,
        async: false,
        success: function(data) {
            showAvatar(data.portfolio);

            let twts = $('.list-group');
            twts.empty();
            twts.append('<h5>' + name + '\'s Timeline</h5>');

            data.twtTweets.forEach(function(row) {
                $('.list-group').append('<li class="list-group-item d-flex">' + row.text  + '<span>' + row.createdAt + '</span></li>');
            });
            workExperience(data.portfolio);
            currentPortfolio = data.portfolio;
        }
    });
};

const updateForm = (newPortfolio) => {
    $.ajax({
        url: SERVER_URL +  "/" + newPortfolio.id,
        type: 'PATCH',
        data: JSON.stringify(newPortfolio),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        success: function(result){
            if(result) {
                currentPortfolio = result;
                console.log(result);
                $('#notification').empty()
                    .append('<div class="alert alert-success" role="alert">It was updated!</div>');
                workExperience(result);
                updatePortfoliosBoard();
                showAvatar(result);
            } else {
                $('#notification').empty()
                    .append('<div class="alert alert-danger" role="alert">Server Error!</div>');
            }
        }
    });
};


const  showAvatar = (portfolio) => {
    let avatar = $('#avatar');
    avatar.empty();
    avatar.append('<img src="' + portfolio.imageUrl + '" ' + 'className="rounded float-start" height="230px" width="200px" >');
};


const  workExperience = (portfolio) => {
    let profileSel = $('#profile');
    profileSel.empty();
    profileSel.append('<h1>' + portfolio.twitterUserName + '</h1>');
    profileSel.append('<br><h5>My work experience</h5>');
    profileSel.append('<br><p>' + portfolio.description + '</p><br>')
    profileSel.append('<button id="edit" type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#editProfileModal">Edit</button>');
};

const setFormPortfolioModal = () => {
    $('#nameFormInput').val(currentPortfolio.twitterUserName);
    $('#titleFormInput').val(currentPortfolio.title);
    $('#descriptionFormInput').val(currentPortfolio.description);
    $('#urlFormInput').val(currentPortfolio.imageUrl);
};

const buildNewPortfolio = () => {
    return {
        "id": currentPortfolio.id,
        "description": $('#descriptionFormInput').val(),
        "imageUrl": $('#urlFormInput').val(),
        "twitterUserName":  $('#nameFormInput').val(),
        "title": $('#titleFormInput').val()
    }
}

$(document).ready(function() {

    updatePortfoliosBoard();

    $("#refresh-portfolioBoard").click(function( event ) {
        updatePortfoliosBoard();
    });

    $("#portfoliosId").on('click', '#portfolioBoard-body tr', function(){
        let td = $(this).find('td');
        getProfile($(td[0]).text(), $(td[2]).text());
    });

    $('#editProfileModal').on('shown.bs.modal', function () {
        setFormPortfolioModal();
    });

    $("#savePortfolioModal").click(function( event ) {
        updateForm(buildNewPortfolio());
    });

});