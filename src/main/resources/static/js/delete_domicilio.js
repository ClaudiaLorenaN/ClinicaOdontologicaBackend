function deleteBy(id)
{
          //con fetch invocamos a la API de peliculas con el método DELETE
          //pasandole el id en la URL
          const url = '/domicilios/eliminar/'+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => response.json());

          //borrar la fila del domicilio eliminado
          //let row_id = "#" + id;
          document.getElementById(id).remove();

}