window.addEventListener('load', function() {
    function fetchPacientes() {
        const url = '/pacientes';
        const settings = {
            method: 'GET'
        };

        return fetch(url, settings)
            .then(response => response.json());
    }

    function populatePacienteSelect(pacientes) {
        let select = document.getElementById("paciente");

        // Limpiar opciones anteriores
        select.innerHTML = "";

        // Añadir una opción por defecto
        let defaultOption = document.createElement("option");
        defaultOption.value = "";
        defaultOption.text = "Seleccione un paciente";
        select.appendChild(defaultOption);

        // Añadir opciones del paciente
        pacientes.forEach(paciente => {
            let option = document.createElement("option");
            option.value = paciente.id;
            //falta domicilio==============================
            option.text = `${paciente.nombre} ${paciente.apellido}, ${paciente.cedula}, ${paciente.fechaIngreso}, ${paciente.email}`;
            select.appendChild(option);
        });
    }

    fetchPacientes()
        .then(pacientes => {
            populatePacienteSelect(pacientes);
        });

    (function() {
        let pathname = window.location.pathname;
        if (pathname == "/get_pacientes.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});