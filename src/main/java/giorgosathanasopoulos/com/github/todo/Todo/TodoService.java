package giorgosathanasopoulos.com.github.todo.Todo;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoService {
    @SuppressWarnings("unused")
    private TodoRepository todoRepository;

    List<Todo> getTodos(String filter) {
        return todoRepository.findAll().stream().filter(todo -> todo.getName().toLowerCase().contains(filter.toLowerCase())).toList();
    }

    void deleteTodo(long id) throws TodoNotFoundError {
        if(todoRepository.findById(id).isEmpty()) {
            throw new TodoNotFoundError("Todo with id: " + id + " was not found!");
        }
        
        todoRepository.deleteById(id);
    }
    
    void postTodo(Todo todo) throws TodoAlreadyExistsError {
        List<Todo> todoList = todoRepository.findTodosByName(todo.getName());

        if(!todoList.isEmpty()) {
            throw new TodoAlreadyExistsError("Todo with name: " + todo.getName() + " already exists!");
        }

        todoRepository.save(todo);
    }

    @Transactional
    void putTodo(long id, String name, String description, boolean done, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deadline) throws TodoNotFoundError {
        Optional<Todo> todoOptional = todoRepository.findById(id);

        if(todoOptional.isEmpty()) {
            throw new TodoNotFoundError("Todo with id: " + id + " was not found!");
        }

        Todo todo = todoOptional.get();

        if(name != null && name.length() > 0 && !Objects.equals(name, todo.getName())) {
            todo.setName(name);
        }

        if(description != null && description.length() > 0 && !Objects.equals(description, todo.getDescription())) {
            todo.setDescription(description);
        }

        if(done != todo.isDone()) {
            todo.setDone(done);
        }

        if(deadline != todo.getDeadline()) {
            todo.setDeadline(deadline);
        }

        todoRepository.save(todo);
    }
}
