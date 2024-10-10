-1. ¿Qué sucede si intentas borrar una encuesta que no existe? ¿Cómo lo has controlado?
  Si no hay encuestas en la base de datos no se pueden borrar. No se si te refieres a eso,mi respuesta es según mi logica.
  
-2. Si introduces en un texto del tipo <style>body background-color:red</style> en uno de los
campos ¿Qué sucede al ver la encuesta? ¿el navegador ejecuta ese código o no? ¿porqué?¿cómo podrías hacer que se ejecute ese código o que se deje de ejecutar?
  El navegador mostrará el texto tal cual se ha escrito en el campo de la encuesta, porque no interpretan codigo, solo lo recibe y asi lo mmuestra.
  Para que se ejecute es necesario que el sistema que procesa las respuestas esté configurado para interpretar y renderizar código

-3. ¿Qué has tenido que modificar en el repositorio para filtrar por motivo de búsqueda? ¿has
tenido que escribir el código específico o como lo has realizado?
No lo he terminado por falta de conocimiento, pero esto es lo que puse: List<Encuesta> findByNsgeneral(String nsgeneral);
