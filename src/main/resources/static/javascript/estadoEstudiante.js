document.addEventListener("DOMContentLoaded", function () {
    const estadoAttr = document.body.getAttribute("data-estado");

    const estaHabilitado = estadoAttr === "true";

    if (!estaHabilitado) {
        const modalElement = document.getElementById('modalInhabilitado');
        if (modalElement) {
            const modalInhabilitado = new bootstrap.Modal(modalElement);
            modalInhabilitado.show();
        }

        const btnExplorar = document.querySelector('a.btn-success.btn-lg');
        if (btnExplorar) {
            btnExplorar.classList.add('disabled');
            btnExplorar.setAttribute('aria-disabled', 'true');
            btnExplorar.onclick = function (e) {
                e.preventDefault();
                alert("Acceso denegado: Tu cuenta está inhabilitada por deméritos.");
            };
        }
    }
});