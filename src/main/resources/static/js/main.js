

function cambio(){
    let messages = document.querySelectorAll(".time");
    let str3 = document.querySelectorAll(".signup");
    for (let i = 0; i < messages.length; i++) {
        let str = Date.parse(messages[i].innerHTML) - new Date;
        let str2 = msToTime(str);
        if (str <0){
            str3[i].innerHTML = '<a th:unless="${eventsList.vacants}>${eventsList.signed}" class="btn btn-link btn-block text-danger">Caducado</a>'
        }
        messages[i].innerHTML = str2;
    }
}

function msToTime(s) {
    let ms = s % 1000;
    s = (s - ms) / 1000;
    let secs = s % 60;
    s = (s - secs) / 60;
    let mins = s % 60;
    let hrs = (s - mins) / 60;

    return 'Faltan: ' + hrs + 'h:' + mins + 'm:' + secs + 's.';
}

document.addEventListener("DOMContentLoaded", cambio);