package giorgosathanasopoulos.com.github.todo.Todo;

public class TodoAlreadyExistsError extends Exception {
    public TodoAlreadyExistsError(String msg) {
        super(msg);
    }
}
