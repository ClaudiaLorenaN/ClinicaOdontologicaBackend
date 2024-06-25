function deleteBy(id)
{
          //con fetch invocamos a la API de odontologos con el método DELETE
          //pasandole el id en la URL
          const url = '/odontologos/eliminar/'+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings);

          //borrar la fila del odontologo eliminado
          //let row_id = "#" + id;
          document.getElementById(id).remove();

}