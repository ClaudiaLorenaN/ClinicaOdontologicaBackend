window.addEventListener('load', function() {
    function fetchDomicilios() {
        const url = '/domicilios';
        const settings = {
            method: 'GET'
        };

        return fetch(url, settings)
            .then(response => response.json());
    }

    function populateDomicilioSelect(domicilios) {
        let select = document.getElementById("domicilio");

        // Limpiar opciones anteriores
        select.innerHTML = "";

        // Añadir una opción por defecto
        let defaultOption = document.createElement("option");
        defaultOption.value = "";
        defaultOption.text = "Seleccione un domicilio";
        select.appendChild(defaultOption);

        // Añadir opciones del domicilio
        domicilios.forEach(domicilio => {
            let option = document.createElement("option");
            option.value = domicilio.id;
            option.text = `${domicilio.calle} ${domicilio.numero}, ${domicilio.localidad}, ${domicilio.provincia}`;
            select.appendChild(option);
        });
    }

    fetchDomicilios()
        .then(domicilios => {
            populateDomicilioSelect(domicilios);
        });

    (function() {
        let pathname = window.location.pathname;
        if (pathname == "/get_domicilios.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});