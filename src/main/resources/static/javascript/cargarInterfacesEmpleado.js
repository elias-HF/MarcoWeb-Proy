
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