function separarHorarios(valor, espacioId) {
    const inputInicio = document.getElementById("horaInicio-" + espacioId);
    const inputFin = document.getElementById("horaFin-" + espacioId);

    if (valor && valor.includes(" - ")) {
        const partes = valor.split(" - ");
        if (inputInicio && inputFin) {
            inputInicio.value = partes[0].trim();
            inputFin.value = partes[1].trim();
        }
    } else if (inputInicio && inputFin) {
        inputInicio.value = "";
        inputFin.value = "";
    }
}

function filtrarHorariosPorFecha(espacioId) {
    const inputFecha = document.getElementById("fechaReserva-" + espacioId);
    const selectHorario = document.getElementById("selectHorario-" + espacioId);
    if (!inputFecha || !selectHorario) return;

    const fechaSeleccionada = inputFecha.value;
    const keyEspacio = String(espacioId);

    const ahora = new Date();
    const yyyy = ahora.getFullYear();
    const mm = String(ahora.getMonth() + 1).padStart(2, "0");
    const dd = String(ahora.getDate()).padStart(2, "0");
    const hoyStr = `${yyyy}-${mm}-${dd}`;

    const esHoy = fechaSeleccionada === hoyStr;
    const minutosActuales = ahora.getHours() * 60 + ahora.getMinutes();

    const ocupadosEnFecha = (typeof horariosOcupados !== "undefined"
        && horariosOcupados
        && horariosOcupados[keyEspacio]
        && horariosOcupados[keyEspacio][fechaSeleccionada])
        ? horariosOcupados[keyEspacio][fechaSeleccionada]
        : [];

    let seleccionInvalida = false;

    Array.from(selectHorario.options).forEach(option => {
        if (!option.value) return;

        const bloque = option.value.trim();
        const horaInicioStr = bloque.split(" - ")[0];
        const [horas, minutos] = horaInicioStr.split(":").map(Number);
        const minutosInicioBloque = horas * 60 + minutos;

        const yaPaso = esHoy && minutosInicioBloque <= minutosActuales;
        const yaReservado = ocupadosEnFecha.includes(bloque);

        if (yaPaso || yaReservado) {
            option.disabled = true;
            option.textContent = yaReservado ? `${bloque} (Ocupado)` : `${bloque} (No disponible)`;
            if (option.selected) {
                seleccionInvalida = true;
            }
        } else {
            option.disabled = false;
            option.textContent = bloque;
        }
    });

    if (seleccionInvalida) {
        selectHorario.value = "";
        separarHorarios("", espacioId);
    }
}

document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll('[id^="modalReserva-"]').forEach(modal => {
        modal.addEventListener("show.bs.modal", () => {
            const espacioId = modal.id.replace("modalReserva-", "");
            filtrarHorariosPorFecha(espacioId);
        });
    });
});