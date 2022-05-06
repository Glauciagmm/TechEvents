
function CompareDates() {
    //Comprobamos que tenga formato correcto
    const date1 = document.getElementById("date").value;
    const today = new Date('yyyy-MM-dd HH:mm:ss');

    if (date1 <= today.getTimezoneOffset()){
        return false;
    }
    else {
        return true;
    }
}

function Inscribirse() {
    let userEvent = userEvent.join;
    document.getElementById("apuntate").value;

}