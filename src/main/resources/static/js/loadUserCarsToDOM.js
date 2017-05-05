type = "text/javascript";
$(function () {
    loadCarsJsonData();
});

function loadCarsJsonData() {

    $.get("/cars/user", function (data) {
        addUserCarsToDOM(data);
    })

}


function addUserCarsToDOM(data) {
    let dropDownMenu = $('#dropdown-car-menu').find('.dropdown-menu');
    dropDownMenu.find('.dropdown-divider').prevAll('a').remove();
    $.each(JSON.parse(data), function (i, car) {
        let item = ($('<a></a>'));
        item.addClass('dropdown-item');
        item.attr("href", "#");
        item.attr("carId", car.id);
        // item.attr("href", "user/" +car.userId + "/car/" + car.id);
        item.text(car.make + " " + car.model);
        item.insertBefore(dropDownMenu.find('.dropdown-divider'));
        item.click(function () {
            let selection = $(this);
            let active = $('#selectedCar');
            active.text(selection.text());
            $.removeCookie("activeCar");
            $.cookie("activeCar", selection.text());


            if (selection.text() !== "Add Car") {

                console.log(car.id);

                let data = {};
                let tokenName = $("#csrfTokenName").attr("value");
                data[tokenName] = $("#csrfToken").attr("value");
                data['carId'] = car.id;
                $.ajax({
                    type: 'POST',
                    url: '/cars/active',
                    data: data
                });
                window.location.replace("/cars/" + car.id + "/repairs")
            }
        });
    });

    $('#selectedCar').text($.cookie('activeCar'));
}
