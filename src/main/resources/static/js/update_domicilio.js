window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la pelicula
    const formulario = document.querySelector('#update_domicilio_form');

    formulario.addEventListener('submit', function (event) {
        let domicilioId = document.querySelector('#domicilio_id').value;

        //creamos un JSON que tendrá los datos de la película
        //a diferencia de una pelicula nueva en este caso enviamos el id
        //para poder identificarla y modificarla para no cargarla como nueva
        const formData = {
            id: document.querySelector('#domicilio_id').value,
            calle: document.querySelector('#calle').value,
            numero: document.querySelector('#numero').value,
            localidad: document.querySelector('#localidad').value,
            provincia: document.querySelector('#provincia').value,

        };

        //invocamos utilizando la función fetch la API peliculas con el método PUT que modificará
        //la película que enviaremos en formato JSON
        const url = '/domicilios';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })

    //Es la funcion que se invoca cuando se hace click sobre el id de una pelicula del listado
    //se encarga de llenar el formulario con los datos de la pelicula
    //que se desea modificar
    function findBy(id) {
          const url = '/domicilios'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let domicilio = data;
              document.querySelector('#domicilio_id').value = domicilio.id;
              document.querySelector('#calle').value = domicilio.calle;
              document.querySelector('#numero').value = domicilio.numero;
              document.querySelector('#localidad').value = domicilio.localidad;
              document.querySelector('#provincia').value = domicilio.provincia;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_domicilio_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }