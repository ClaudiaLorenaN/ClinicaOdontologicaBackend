window.addEventListener('load', function () {

let odontologo;
let paciente;

    function findOdontologoPacientById(idOdontologo, idPaciente) {
              const url = '/odontologos/buscar'+"/"+idOdontologo;
              const settings = {
                  method: 'GET'
              };

              return fetch(url,settings)
              .then(response => response.json())
              .then(o => {
                  odontologo = o;

                  const url = '/pacientes/buscar'+"/"+idPaciente;
                            const settings = {
                                method: 'GET'
                            };

                            return fetch(url,settings)
                            .then(response => response.json())
                            .then(p => {
                                paciente = p;
                            }).catch(error => {
                                alert("Error: " + error);
                            });

              }).catch(error => {
                  alert("Error: " + error);
              });
          }

    (function(){

      //con fetch invocamos a la API de turnos con el método GET
      //nos devolverá un JSON con una colección de turnos
      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de turnos del JSON
         for(turno of data){
            //por cada turno armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el turno
            //var table = document.getElementById("turnoTable");
            var table = document.getElementById("turnoTable");
            var turnoRow =table.insertRow();
            let tr_id = turno.id;
            turnoRow.id = tr_id;

            //por cada turno creamos un boton delete que agregaremos en cada fila para poder eliminar el mismo
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar un turno
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                      ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                                      '<i class="fas fa-trash"></i>' +
                                      '</button>';

            //por cada turno creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar el turno que queremos
            //modificar y mostrar los datos del mismo en un formulario.
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                      ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                                      '<i class="fas fa-pencil-alt"></i>' +
                                      '</button>';

            let idSpan = '<span' +
                         ' id=' + '\"' + 'span_id_' + turno.id + '\"' +
                         ' class="btn btn-info btn_id span-as-button">' +
                         turno.id +
                         '</span>';

            findOdontologoPacientById(turno.odontologoId, turno.pacienteId)
            .then(data => {
                //armamos cada columna de la fila
                            //como primer columna pondremos el boton modificar
                            //luego los datos del turno
                            //como ultima columna el boton eliminar
                            turnoRow.innerHTML = '<td>' + idSpan + '</td>' +
                                    '<td class=\"td_paciente\">' +
                                    paciente.nombre + ' ' +
                                    paciente.apellido + ' ' +
                                    'Cédula: ' +
                                    paciente.cedula + ' ' +
                                    '</td>' +
                                    '<td class=\"td_odontologo\">' +
                                    odontologo.nombre + ' ' +
                                    odontologo.apellido + ' ' +
                                    '</td>' +
                                    '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
                                    '<td>' + updateButton + '</td>'+
                                    '<td>' + deleteButton + '</td>';
            });
        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_turnos.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })