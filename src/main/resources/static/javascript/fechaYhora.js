function actualizarRelojYFecha() {
    const ahora = new Date();

    const opcionesFecha = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
    let fechaString = ahora.toLocaleDateString('es-ES', opcionesFecha);

    fechaString = fechaString.charAt(0).toUpperCase() + fechaString.slice(1);

    const horaString = ahora.toLocaleTimeString('es-ES');

    document.getElementById('fechaActual').textContent = fechaString;
    document.getElementById('horaActual').textContent = horaString;
}

actualizarRelojYFecha();

setInterval(actualizarRelojYFecha, 1000);