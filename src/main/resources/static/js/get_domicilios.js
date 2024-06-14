window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de peliculas con el método GET
      //nos devolverá un JSON con una colección de peliculas
      const url = '/domicilios';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de peliculas del JSON
         for(domicilio of data){
            //por cada pelicula armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos la pelicula
            var table = document.getElementById("domicilioTable");
            var domicilioRow =table.insertRow();
            let tr_id = domicilio.id;
            domicilioRow.id = tr_id;

            //por cada pelicula creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar una pelicula
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + domicilio.id + '\"' +
                                      ' type="button" onclick="deleteBy('+domicilio.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            //por cada pelicula creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar la pelicula que queremos
            //modificar y mostrar los datos de la misma en un formulario.
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + domicilio.id + '\"' +
                                      ' type="button" onclick="findBy('+domicilio.id+')" class="btn btn-info btn_id">' +
                                      domicilio.id +
                                      '</button>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos de la pelicula
            //como ultima columna el boton eliminar
            domicilioRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_calle\">' + domicilio.calle.toUpperCase() + '</td>' +
                    '<td class=\"td_numero\">' + domicilio.numero + '</td>' +
                    '<td class=\"td_localidad\">' + domicilio.localidad.toUpperCase() + '</td>' +
                    '<td class=\"td_provincia\">' + domicilio.provincia.toUpperCase() + '</td>' +
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