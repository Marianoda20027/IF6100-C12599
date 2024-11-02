package ucr.ac.C12599.handlers;

public interface RegisterUserHandler {

    record Command(String name, String email, String password){}
    sealed interface Result {
        final record Success (String message) implements Result {} //es final ya que es una inerfaz sellada lo que quiere decir es que
        //hay un numero delimitaado por hijos y yo lo voy a delimitar
        final record InvalidData(String... fields) implements Result {} //el record no tiene hijos por definicion y se usa cuando
        // no se quiere modificar ya que es de solo lectura
    }
        Result RegisterUser(Command command);     //CQRS new Branch






}
