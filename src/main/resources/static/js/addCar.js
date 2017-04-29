type = "text/javascript";

$(function () {
    loadBrands();
    loadModelsOnSelectOfBrand();
});

function loadBrands() {
    let brandSelectDiv = $('#makeSelect');
    $.ajax({
        type: 'GET',
        url: '/cars/brands',
        data: 'json',
        success: function (brands) {
            brandSelectDiv.empty();
            brandSelectDiv.append( $('<option></option>'));
            $.each(JSON.parse(brands), function (i, brand) {
                let optionDiv = $('<option></option>').text(brand);
                optionDiv.appendTo(brandSelectDiv);
                // console.log(brand);
            });
        }
    });
}

function loadModelsOnSelectOfBrand() {

    $('#makeSelect').change(function () {
        console.log(this.value);
        let modelSelectElement = $('#modelSelect');
        $.ajax({
            type: 'GET',
            url: '/cars/models?make=' + this.value,
            data: 'json',
            success: function (models) {
                modelSelectElement.empty();
                modelSelectElement.append( $('<option></option>'));
                $.each(JSON.parse(models), function (i, model) {
                    console.log(model);
                    let option = $('<option></option>').text(model);
                    option.appendTo(modelSelectElement);
                })
            }
        })
    });
    loadYearsOnSelectOfModel();
}

function loadYearsOnSelectOfModel() {
    let modelElement = $('#modelSelect');
    modelElement.change(function () {
        console.log(this.value);
        let yearSelectElement = $('#yearSelect');
        let make = $('#makeSelect').val();
        // alert(make+" "+ $(this.value));
        console.log(make+" "+ modelElement.val());
        $.ajax({
            type: 'GET',
            url: '/cars/models?make=' + make + "&model=" + modelElement.val(),
            data: 'json',
            success: function (years) {
                yearSelectElement.empty();
                yearSelectElement.append( $('<option></option>'));
                $.each(JSON.parse(years), function (i, year) {
                    console.log(year);
                    let option = $('<option></option>').text(year);
                    option.appendTo(yearSelectElement);
                })
            }
        })

    })


}

// Format date number to date not used for now
function formatDate(dateInNumber) {
    var date = new Date(dateInNumber);
    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();
    return year + '/' + month + '/' + day;
}