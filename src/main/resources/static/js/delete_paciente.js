function deleteBy(id)
{
          //con fetch invocamos a la API de pacientes con el método DELETE
          //pasandole el id en la URL
          const url = '/pacientes/eliminar/'+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings);

          //borrar la fila del paciente eliminado
          //let row_id = "#" + id;
          document.getElementById(id).remove();

}