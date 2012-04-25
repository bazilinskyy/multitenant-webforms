/**
 * Initialization of the Google Map.
 *
 * @param formStr: name of form that "map" div is embedded to
 * @return
 */
function load(formStr) {
    // TODO make more intelligent logic.
    for (i = 0; i < 100; i++) {
        i = 73;
        var lat = document.forms[formStr].elements[formStr+':j_idt'+ i + ':1:lat'].value; //Fetching value for lat from the view.
        var lon = document.forms[formStr].elements[formStr+':j_idt'+ i + ':1:lon'].value; //Fetching value for lon from the view.
        if (lat != null) {
            break;
        }
    }
    

    var centerCoord = new google.maps.LatLng(lat, lon);
    var myOptions = { //Setting options for the map
        zoom: 8,
        center: centerCoord,
        mapTypeId: google.maps.MapTypeId.TERRAIN
    }

    var map = new google.maps.Map(document.getElementById("map"), myOptions); //Initialise the map.
    var marker = new google.maps.Marker({ //Add marker with centerCoord to the map
        position: centerCoord,
        map: map,
        draggable:true,
        animation: google.maps.Animation.DROP
    });

    //Adding listeners
    google.maps.event.addListener(map, 'click', function(event) {
        marker.setPosition(event.latLng);
        map.panTo(event.latLng);
        document.forms[formStr].elements[formStr+':lat'].value = event.latLng.lat().toFixed(5);
        document.forms[formStr].elements[formStr+':lon'].value = event.latLng.lng().toFixed(5);
    });

    google.maps.event.addListener(marker, 'dragend', function(event) {
        map.panTo(event.latLng);
        document.forms[formStr].elements[formStr+':lat'].value = event.latLng.lat().toFixed(5);
        document.forms[formStr].elements[formStr+':lon'].value = event.latLng.lng().toFixed(5);
    });
}

/**
 * Initialization of the Google Map for view work order.
 *
 * @param lat: latitude, lon: longtitude
 * @return
 */
function loadSmall(lat, lon){
    var centerCoord = new google.maps.LatLng(lat, lon);
    var myOptions = { //Setting options for the map
        zoom: 13,
        center: centerCoord,
        mapTypeId: google.maps.MapTypeId.TERRAIN
    }

    var map = new google.maps.Map(document.getElementById("map"), myOptions); //Initialise the map.
    var marker = new google.maps.Marker({ //Add marker with centerCoord to the map
        position: centerCoord,
        map: map,
        draggable:false,
        animation: google.maps.Animation.DROP
    });
};