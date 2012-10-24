var timer = 0;
var item = 0;
 
function log(text) {
    var logElement = document.getElementById('log');
    if(logElement) logElement.innerHTML = text + '<br/>' + logElement.innerHTML;
}
 
//function for opening of submenu elements
function openMenu(id) {
    log('openMenu('+id+')');
    window.clearTimeout(timer);
 
    if(item) item.style.visibility = 'hidden';
 
    item = document.getElementById(id);
    item.style.visibility = 'visible';
}
 
function closeMenu() {
    // sets timer to close the open submenu in 0.5 seconds
    if(item) {
        log('closeMenu, schedule timer for ' + item.id);
        timer = window.setTimeout(
            "log('onTimer'); if(item) {log('hide ' + item.id); item.style.visibility = 'hidden';}",
            500);
    }
}
 
function keepMenuOpen() {
    log('keepMenuOpen');
    window.clearTimeout(timer);
}
 
document.onclick = closeelement;