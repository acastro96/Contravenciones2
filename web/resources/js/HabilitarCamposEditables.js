/* 
 * Civitrans
 * La Cívica Impresores S.A.S
 * Copyright 2016.
 */


function habilitarCampo(input,opcion) {

    if (opcion === 1) {
        document.getElementById(input).style.display = "none";        
        document.getElementById(input+"_edit").style.display = "block";
        
    }else {
        
        document.getElementById(input).readOnly = false;
    }
}
