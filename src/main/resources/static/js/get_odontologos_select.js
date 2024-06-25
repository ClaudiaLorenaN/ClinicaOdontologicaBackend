window.addEventListener('load', function() {
    function fetchOdontologos() {
        const url = '/odontologos';
        const settings = {
            method: 'GET'
        };

        return fetch(url, settings)
            .then(response => response.json());
    }

    function populateOdontologoSelect(odontologos) {
        let select = document.getElementById("odontologo");

        // Limpiar opciones anteriores
        select.innerHTML = "";

        // Añadir una opción por defecto
        let defaultOption = document.createElement("option");
        defaultOption.value = "";
        defaultOption.text = "Seleccione un odontólogo";
        defaultOption.disabled = true;
        defaultOption.selected = true;
        select.appendChild(defaultOption);

        // Añadir opciones del odontologo
        odontologos.forEach(odontologo => {
            let option = document.createElement("option");
            option.value = odontologo.id;
            option.text = `${odontologo.nombre} ${odontologo.apellido}`;
            select.appendChild(option);
        });
    }

    fetchOdontologos()
        .then(odontologos => {
            populateOdontologoSelect(odontologos);
        });

    (function() {
        let pathname = window.location.pathname;
        if (pathname == "/get_odontologos.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});