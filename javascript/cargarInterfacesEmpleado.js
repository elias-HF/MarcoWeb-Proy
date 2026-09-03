/* function cargarVista(pagina, elemento){
    fetch(`/html/CompoEmpleado/${pagina}`).then(response => response.text()).then(data => {
        // Insertar el HTML dentro del main abajo del navbar
        document.getElementById("panelPrincipal").innerHTML = data;

        // Quitar el 'active' de todos los enlaces
        const enlaces = document.querySelectorAll("#sidebar .nav-link");
        enlaces.forEach(enlace => {
            enlace.classList.remove("active");
            enlace.classList.add("text-white");
        });

        // Activar el enlace clickeado
        elemento.classList.add("active");
        elemento.classList.remove("text-white");
    })
    .catch(error => {
        console.error("Error al cargar la vista", error)
    });
}

// Cargar la interfaz de inicio automáticamente
document.addEventListener("DOMContentLoaded", function () {
    const inicio = document.querySelector("#sidebar .nav-link");
    cargarVista("inicio.html", inicio);
});

function cambiarSidebar() {
    const sidebar = document.getElementById("sidebar");
    sidebar.classList.toggle("collapsed");
}

// Función propia del java de bootstrap
const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]');
tooltipTriggerList.forEach(function (tooltipTriggerEl) {
    new bootstrap.Tooltip(tooltipTriggerEl);
});
*/
//parte nueva para el iframe//
function cargarVista(pagina, elemento) {
    document.getElementById("frameContenido").src = pagina;

    const enlaces = document.querySelectorAll("#sidebar .nav-link");
    enlaces.forEach(enlace => {
        enlace.classList.remove("active");
        enlace.classList.add("text-white");
    });

    elemento.classList.add("active");
    elemento.classList.remove("text-white");
}

function cambiarSidebar() {
    const sidebar = document.getElementById("sidebar");
    sidebar.classList.toggle("collapsed");
}