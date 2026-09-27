const formularioRegistro = document.getElementById("formularioRegistro");

const nombre = document.getElementById("regNombre");
const correo = document.getElementById("regEmail");
const password = document.getElementById("regPassword");
const confirmarPassword = document.getElementById("regConfirmarPassword");

const mensaje = document.getElementById("mensajeRegistro");

formularioRegistro.addEventListener("submit", function(event) {

    event.preventDefault();

    mensaje.textContent = "";

    if (nombre.value.trim() === "") {
        mensaje.textContent = "Ingrese su nombre y apellidos";
        mensaje.className = "alert alert-danger";
        return;
    }

    if (correo.value.trim() === "") {
        mensaje.textContent = "Ingrese su correo institucional";
        mensaje.className = "alert alert-danger";
        return;
    }

    if (password.value.trim() === "") {
        mensaje.textContent = "Ingrese una contraseña";
        mensaje.className = "alert alert-danger";
        return;
    }

    if (password.value !== confirmarPassword.value) {
        mensaje.textContent = "Las contraseñas no coinciden";
        mensaje.className = "alert alert-danger";
        return;
    }

    mensaje.textContent = "Cuenta registrada correctamente";
    mensaje.className = "alert alert-success";

});