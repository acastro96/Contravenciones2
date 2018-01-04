/* 
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */


window.onload = function () {
    var isIE = /*@cc_on!@*/false || !!document.documentMode;
    if (!isIE) {
        PF('incompatibleDialog').show();
    }
}

function autenticar(template) {
    document.getElementById('form:txtAUTHID').value = ejecutarHuellero(template);
    dipararEvento(document.getElementById('form:btnAutenticado'), 'click');
}
function autenticar_template(template) {
    document.getElementById('formAutenticar:txtAUTHID').value = ejecutarHuellero(template);
    dipararEvento(document.getElementById('formAutenticar:btnAutenticado'), 'click');
}
function ejecutarHuellero(template) {
    //Autenticación RUNT 
    if (template.length === 0) {
        alert("Problema al autenticar, intente de nuevo.");
        console.log("PreToken vacio!");
        return false;
    }
    var obj = new ActiveXObject("IEActiveRUNT.IEActiveRUNT");
    var rta = obj.Autenticar_Ciudadano(template);
    if (rta === "") {
        alert("Problema al autenticar, intente de nuevo.");
        return false;
    }
    if (rta.length < 100 && rta.length > 0) {
        alert("Problema al autenticar, verifique su conexión \n de red y que el huellero se encuentre conectado.");
        return false;
    }
    return rta;
}
function dipararEvento(el, etype) {
    if (el.fireEvent) {
        el.fireEvent('on' + etype);
    } else {
        var evObj = document.createEvent('Events');
        evObj.initEvent(etype, true, false);
        el.dispatchEvent(evObj);
    }
}

function enrolar(template) {
    if (template.length === 0) {
        alert("Problema al autenticar, intente de nuevo.");
        console.log("PreToken vacio!");
        return false;
    }
    var obj = new ActiveXObject("IEActiveRUNT.IEActiveRUNT")
    var rta = obj.Enrolar(template);
    if (rta === "") {
        alert("Problema al autenticar, intente de nuevo.");
        return false;
    }
    if (rta.length < 100 && rta.length > 0) {
//        alert(rta);
        alert("Problema al enrolar, verifique su conexión \n de red y que el huellero se encuentre conectado.");
    }
    document.getElementById('form:txtAUTHID').value = rta;
    dipararEvento(document.getElementById('form:btnAutenticadoBiometrico'), 'click');
}