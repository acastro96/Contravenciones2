/* 
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */

bloquear();
function bloquear() {
    var x = document.getElementsByTagName("input");
    for (i = 0; i < x.length; i++) {
        var campo = x[i];
        campo.onpaste = function (e) {
            e.preventDefault();
        }
    }
    document.oncontextmenu = function (e) {
        e.preventDefault();
    }

}

