/* <![CDATA[ */


////<!-- Original:  Ronnie T. Moore -->
//<!-- Web Site:  The JavaScript Source -->
//
//<!-- Dynamic 'fix' by: Nannette Thacker -->
//<!-- Web Site: http://www.shiningstar.net -->
//
//<!-- This script and many more are available free online at -->
//<!-- The JavaScript Source!! http://javascript.internet.com -->
//
//<!-- Begin
function textCounter(field, countfield, maxlimit) {
    if (field.value.length > maxlimit) {// if too long...trim it!
        field.value = field.value.substring(0, maxlimit);
    }
    // otherwise, update 'characters left' counter
    else
        countfield.value = maxlimit - field.value.length + 1;
}

function initTextCounter(field, countfield, maxlimit) {
    countfield.value = maxlimit - field.value.length;
}
// End -->


//Counter for passwords
function mirror(text){
    document.getElementById("theDiv").innerHTML=text.value.length;
}
window.onload = function() {document.getElementById("theDiv").innerHTML="0"};

function getInputText(text)
{
        alert(text.value);
}