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

    $.each(JSON.parse(data), function (i, car) {
        let item = ($('<a></a>'));
        item.addClass('dropdown-item');
        item.attr("href", "#");
        // item.attr("href", "user/" +car.userId + "/car/" + car.id);
        item.text(car.make +" "+ car.model);
        item.insertBefore(dropDownMenu.find('.dropdown-divider'));
        item.click(function () {
            let selection = $(this);
            let active = $('#selectedCar');
            active.text(selection.text());
        });
    })
}
