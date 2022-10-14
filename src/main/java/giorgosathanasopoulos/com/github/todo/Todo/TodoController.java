package giorgosathanasopoulos.com.github.todo.Todo;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RequestMapping(path = "api/v1/todo")
public class TodoController {
    @SuppressWarnings("unused")
    private TodoService todoService;

    @GetMapping
    List<Todo> getTodos(@RequestHeader String filter) {
        return todoService.getTodos(filter);
    }

    @DeleteMapping(path = "{id}")
    void deleteTodo(@PathVariable long id) throws TodoNotFoundError {
        todoService.deleteTodo(id);
    }

    @PostMapping
    void postTodo(@RequestBody Todo todo) throws TodoAlreadyExistsError {
        todoService.postTodo(todo);
    }

    @PutMapping(path = "{id}")
    void putTodo(@PathVariable long id, @RequestHeader String name, @RequestHeader String description, @RequestHeader boolean done, @RequestHeader @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadline) throws TodoNotFoundError {
        todoService.putTodo(id, name, description, done, deadline);
    }
}
