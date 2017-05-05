type = "text/javascript";

$(function () {
    $('#homeId').click(function () {
        $.removeCookie('activeCar');
        window.location.replace("/");
    });
    changeActiveCategory();

    var pathname = window.location.pathname; // Returns path only
    if ((pathname.match(/car/g)) || (pathname.match(/repair/g)) || (pathname.match(/garages/g))) {
        $('#categoriesUnorderedList').show();
    }
});

function changeActiveCategory() {

    $('#categoriesUnorderedList li').on('click', 'a', function () {
        $(this).parent().parent().find('.active').removeClass('active');
        $(this).addClass('active');
    });

}

$(document).ready(function () {
    if (pathname.match(/garages/g)) {
        $('#glist').addClass('active');

    } else if (pathname.match('/garages/add'/g)) {
        $('#gadd').addClass('active');
    }
});

// Format date number to date not used for now
function formatDate(dateInNumber) {
    var date = new Date(dateInNumber);
    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();
    return year + '/' + month + '/' + day;
}
