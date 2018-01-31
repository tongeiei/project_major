$('#sound-active-input').prop('checked', true);

if (Array.prototype.forEach) {
    var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));

    elems.forEach(function (html) {
        var switchery = new Switchery(html);
    });
} else {
    var elems = document.querySelectorAll('.js-switch');

    for (var i = 0; i < elems.length; i++) {
        var switchery = new Switchery(elems[i]);
    }
}

// Getting checkbox state
// On click
