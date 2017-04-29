type = "text/javascript";

$(function () {
    loadJSONCategories();
    hideAddItemInputs();
    changeActiveCar();
    newCategoryListener();
    $('#btnSave').click(saveCategory);
    $('#btnCancel').click(hideAddItemInputs);
    $('#addCategoryButton').click(showAddItemInputs);
    // $('#searchBox').on('input', search);
});



//Menu current car dropdown
function changeActiveCar() {

    $(".dropdown-menu a").click(function () {
        let selection = $(this);
        let active = $('#selectedCar');
        active.text(selection.text());
        if (selection.text() == "Add Car") {
            addNewCarForm();
        } else {
            //TODO select car info
        }
        //TODO ADD car id and functionality to display that car items only

    });
}



//Show Add Items
function showAddItemInputs() {
    $('#addCategoryInput').show();
}

//Hide Add Items
function hideAddItemInputs() {
    $('#addCategoryInput').hide();
}

//Function to listen for key pres on input
function newCategoryListener() {
    $('#newCategoryName')
        .keypress(function (e) {
            if (e.which == 13) {
                // alert('You pressed enter!');
                let currentDOMItem = $(this);
                let category = {};
                category.name = currentDOMItem.val();
                saveCategory(category);
            }
        })
        .on('keyup', function (e) {
            var currentDOMItem = $(this);
            if (e.which == 27) {
                currentDOMItem.empty();
                hideAddItemInputs();
            }
        })
}

//Delete category
function deleteCategory(id) {

    $('#categoriesUnorderedList li')

}

//Save category
function saveCategory() {
    var category = $('#newCategoryName').val();
    let data = {};
    let tokenName = $("#csrfTokenName").attr("value");
    data[tokenName] = $("#csrfToken").attr("value");
    data['category'] = category;

    $.ajax({
        type: 'POST',
        url: '/categories',
        data: data,
        dataType: 'json',
        contentType: 'application/x-www-form-urlencoded',
        success:  function (category) {
            addCategoryToDOM(category);
        }
    });
}


//Add Categories to DOM
function addCategoryToDOM(category) {
    var id = category.id;
    var name = category.name;
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
    hideAddItemInputs();
}


//Create Category DOM
function    loadDOMCategories(categories) {
    $('#categoriesUnorderedList').empty();
    $.each(categories, function (i, category) {
        var categoryName = category.name;
        var categoryId = category.id;
        $('#categoriesUnorderedList')
            .append($('<li></li>')
                .addClass('nav-item')
                .append($('<a></a>')
                    .addClass('nav-link')
                    .attr('href', '#')
                    .attr('categoryid', categoryId)
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


//Get JSON Categories Data
function loadJSONCategories() {
    $.ajax({
        type: 'GET',
        url: '/categories',
        data: 'json',
        success: function (categories) {
            loadDOMCategories(categories);
        }
    });
}


// Format date number to date not used for now
function formatDate(dateInNumber) {
    var date = new Date(dateInNumber);
    var day = date.getDate();
    var month = date.getMonth() + 1;
    var year = date.getFullYear();
    return year + '/' + month + '/' + day;
}


function loadAllCategoriesItems() {
    var categoryId = $('#categoriesUnorderedList li .active').attr('categoryid');
    $.ajax({
        type: 'GET',
        url: '/items/category/' + categoryId,
        data: 'json',
        success: function (items) {
            $.each(items, function (i, toDoItem) {
                addCategoryToDOM(toDoItem);
            });
        }
    });
}

function updateItem(item) {
    var itemId = item.id;
    $.ajax({
        type: 'PUT',
        url: '/items/' + itemId,
        data: JSON.stringify(item),
        contentType: 'application/json'
    })
}

function deleteItem(itemId) {
    $.ajax({
        type: 'DELETE',
        url: '/items/delete/' + itemId
    })
}