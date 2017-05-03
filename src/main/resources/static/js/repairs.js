type = "text/javascript";

$(function () {
        showHidePartForm();
});

function showHidePartForm() {
    $('#addButton').click(function () {
        $('#addPartForm').show();
        $(this).hide();
    });

    $('#cancelFormButton').click(function () {
        $('#addPartForm').hide();
        $('#addButton').show();
    })
}


//Everything down is not used

//Save part
function savePart() {
    let name = $('#name').val();

    let data = {};
    let tokenName = $("#csrfTokenName").attr("value");
    data[tokenName] = $("#csrfToken").attr("value");
    data['category'] = category;

    $.ajax({
        type: 'POST',
        url: '/repairs/parts',
        data: data,
        dataType: 'json',
        contentType: 'application/x-www-form-urlencoded',
        success:  function (part) {
            addPartToDOM(part);
        }
    });
}


//Add Part to DOM
function addPartToDOM(part) {
    var id = part.id;
    var name = part.name;
    $('#categoriesUnorderedList').append(
        $('<li></li>')
            .addClass('nav-item')
            .append($('<a></a>')
                .addClass('nav-link')
                .attr('href', '#')
                .attr('categoryid', id)
                .text(name)
                .on('click', function () {
                    $('#categoriesUnorderedList li .active').removeClass('active');
                    var currentCategory = $(this);
                    currentCategory.addClass('active');

                })
            )
    );
    hideAddPartInputs();
}

function hideAddPartInputs() {
    $('#addPartForm').hide();
}


//Create Part DOM
function    loadDOMCategories(parts) {
    $('#categoriesUnorderedList').empty();
    $.each(parts, function (i, part) {
        var partName = part.name;
        var partId = part.id;
        $('#parts-table')
            .append($('<td></td>')
                .append($('<a></a>')
                    .addClass('nav-link')
                    .attr('href', '#')
                    .attr('partId', partId)
                    .text(categoryName)
                    .on('click', function () {
                        $('#categoriesUnorderedList li .active').removeClass('active');
                        var currentCategory = $(this);
                        currentCategory.addClass('active');

                    })
                )
            )
    });

    $('#categoriesUnorderedList li:nth-child(2) a').addClass('active');
}


//Get JSON Part Data
function loadJSONParts() {
    $.ajax({
        type: 'GET',
        url: '/repairs/parts',
        data: 'json',
        success: function (categories) {
            loadDOMCategories(categories);
        }
    });
}