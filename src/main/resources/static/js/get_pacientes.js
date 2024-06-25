window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de pacientes con el método GET
      //nos devolverá un JSON con una colección de pacientes
      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      const table = document.getElementById("pacienteTable");
      table.style.width = "100%";
      //recorremos la colección de pacientes del JSON
         for(paciente of data){
            //por cada paciente armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el paciente
            //var table = document.getElementById("pacienteTable");
            var pacienteRow =table.insertRow();
            let tr_id = paciente.id;
            pacienteRow.id = tr_id;

            //por cada paciente creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar un paciente
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                      ' type="button" onclick="deleteBy('+paciente.id+')" class="btn btn-danger btn_delete">' +
                                      '<i class="fas fa-trash"></i>' +
                                      '</button>';

            //por cada paciente creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar el paciente que queremos
            //modificar y mostrar los datos de la misma en un formulario.
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                      ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                                      '<i class="fas fa-pencil-alt"></i>' +
                                      '</button>';
            let idSpan = '<span' +
                                     ' id=' + '\"' + 'span_id_' + paciente.id + '\"' +
                                     ' class="btn btn-info btn_id span-as-button">' +
                                     paciente.id +
                                     '</span>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos del paciente
            //como ultima columna el boton eliminar
            pacienteRow.innerHTML = '<td>' + idSpan + '</td>' +
                    '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_cedula\">' + paciente.cedula.toUpperCase() + '</td>' +
                    '<td class=\"td_fechaIngreso\">' + paciente.fechaIngreso.toUpperCase() + '</td>' +
                    '<td class=\"td_domicilio\">' +
                    'Cl: ' +
                    paciente.domicilio.calle + ', ' +
                    '#' +
                    paciente.domicilio.numero + ', ' +
                    'Localidad: ' +
                    paciente.domicilio.localidad + ', ' +
                    'Provincia: ' +
                    paciente.domicilio.provincia + ' ' +
                    '</td>' +
                    '<td class=\"td_email\">' + paciente.email + '</td>' +
                    '<td>' + updateButton + '</td>'+
                    '<td>' + deleteButton + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_domicilios.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })