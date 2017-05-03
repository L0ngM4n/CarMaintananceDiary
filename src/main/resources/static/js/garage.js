/**
 * Created by Aleksandar on 02/05/2017.
 */

type = "text/javascript";

$('#glist').click(function () {
    $('#g-list-div').show();
    $('#map-div').hide();
});

$('#gadd').click(function () {
    $('#map-div').show();
    $('#g-list-div').hide();
});
