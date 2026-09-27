const formularioLogin = document.getElementById("formularioLogin");

const correoLogin = document.getElementById("loginEmail");
const passwordLogin = document.getElementById("loginPassword");

const mensajeLogin = document.getElementById("mensajeLogin");

formularioLogin.addEventListener("submit", function(event) {

    event.preventDefault();

    mensajeLogin.textContent = "";

    if (correoLogin.value.trim() === "") {
        mensajeLogin.textContent = "Ingrese su correo institucional";
        mensajeLogin.className = "alert alert-danger";
        return;
    }

    if (passwordLogin.value.trim() === "") {
        mensajeLogin.textContent = "Ingrese su contraseña";
        mensajeLogin.className = "alert alert-danger";
        return;
    }

    mensajeLogin.textContent = "Datos ingresados correctamente";
    mensajeLogin.className = "alert alert-success";

});