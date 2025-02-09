# Clínica Médica

- Se asume que para una consulta médica, con paquete, todo el paquete se realiza en la misma fecha y hora de la consulta.
- No hay relación entre la especialidad médica de un médico y el servicio médico.
  - Se asume que un médico es el "responsable" de la consulta médica. Es decir que si bien un paquete tiene varios servicios médicos, una consulta con paquete,
  solo tendrá un médico asociado.
- Una consulta solo puede tener un servicio médico, o un paquete, pero no ambos, o ninguno.
- El precio de un paquete y de una consulta se calculan automáticamente a partir de lo que se haya elegido para la consulta, al momento de crearla:
   - Descuento del 15% al crear un paquete.
   - Descuento del 20% si tiene obra social. (Y la consulta es por paquete)
- El precio de un paquete y de una consulta no se actualizan automáticamente al editar cada uno. (Se debe pasar manualmente).
- Cada médico tiene turnos disponibles o no, en un horario y fecha. Para crear una consulta o editarla, la fecha y hora de esta deben ser la de un turno disponible para el médico elegido.
- Si se edita la fecha y hora de una consulta, el turno anterior vuelve a estar disponible.
- Si se edita el médico de una consulta, el turno del médico anterior vuelve a estar disponible.
- Si se elimina una consulta, el turno asociado a esta vuelve a estar disponible.
- Si se elimina una consulta y estaba asociada a un paquete, este también se elimina automáticamente.
