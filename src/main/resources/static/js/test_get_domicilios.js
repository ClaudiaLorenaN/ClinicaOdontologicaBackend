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

    function populateDomicilioTable(domicilios) {
        var table = document.getElementById("domicilioTable");

        domicilios.forEach(domicilio => {
            var domicilioRow = table.insertRow();
            let tr_id = domicilio.id;
            domicilioRow.id = tr_id;

            let deleteButton = '<button' +
                ' id=' + '\"' + 'btn_delete_' + domicilio.id + '\"' +
                ' type="button" onclick="deleteBy(' + domicilio.id + ')" class="btn btn-danger btn_delete">' +
                '&times' +
                '</button>';

            let updateButton = '<button' +
                ' id=' + '\"' + 'btn_id_' + domicilio.id + '\"' +
                ' type="button" onclick="findBy(' + domicilio.id + ')" class="btn btn-info btn_id">' +
                domicilio.id +
                '</button>';

            domicilioRow.innerHTML = '<td>' + updateButton + '</td>' +
                '<td class=\"td_calle\">' + domicilio.calle.toUpperCase() + '</td>' +
                '<td class=\"td_numero\">' + domicilio.numero + '</td>' +
                '<td class=\"td_localidad\">' + domicilio.localidad.toUpperCase() + '</td>' +
                '<td class=\"td_provincia\">' + domicilio.provincia.toUpperCase() + '</td>' +
                '<td>' + deleteButton + '</td>';
        });
    }

    fetchDomicilios()
        .then(domicilios => {
            populateDomicilioSelect(domicilios);
            populateDomicilioTable(domicilios);
        });

    (function() {
        let pathname = window.location.pathname;

        //change the next line
        if (pathname == "/test_get_domicilios.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});
