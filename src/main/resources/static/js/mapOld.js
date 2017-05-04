/**
 * Created by Aleksandar on 01/05/2017.
 */
type = "text/javascript";

GMaps.geolocate({
    success: function (position) {
        map.setCenter(position.coords.latitude, position.coords.longitude);
    },
    error: function (error) {
        alert('Geolocation failed: ' + error.message);
    },
    not_supported: function () {
        alert("Your browser does not support geolocation");
    },
    always: function () {
        // alert("Done!");
    }
});

var marker;
var map = new GMaps({
    div: '#map',
    lat: 42.70659491513423,
    lng: 23.36578655987978,
    zoom: 15,
    height: 100,
    click: function (e) {
            marker = {
                lat: e.latLng.lat(),
                lng: e.latLng.lng(),
                title: 'Garage',
                draggable: true,
                infoWindow: {
                    content: ''
                },
                click: function (e) {
                    console.log('You clicked in this marker');
                }
            };
            map.addMarker(marker);
    }
});

map.addControl({
    position: 'top_right',
    content: 'Geolocate',
    style: {
        margin: '5px',
        padding: '1px 6px',
        border: 'solid 1px #717B87',
        background: '#fff'
    },
    events: {
        click: function () {
            GMaps.geolocate({
                success: function (position) {
                    map.setCenter(position.coords.latitude, position.coords.longitude);
                },
                error: function (error) {
                    alert('Geolocation failed: ' + error.message);
                },
                not_supported: function () {
                    alert("Your browser does not support geolocation");
                }
            });
        }
    }
});

$('#addressBtn').on('click', function () {

    GMaps.geocode({
        address: $('#address').val(),
        callback: function (results, status) {
            if (status == 'OK') {
                var latlng = results[0].geometry.location;
                map.setCenter(latlng.lat(), latlng.lng());
                map.addMarker({
                    lat: latlng.lat(),
                    lng: latlng.lng()
                });
            }
        }
    });
});


