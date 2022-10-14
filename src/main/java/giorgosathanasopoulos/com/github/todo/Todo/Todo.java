package giorgosathanasopoulos.com.github.todo.Todo;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Todo {
    @Id
    @SequenceGenerator(name = "todo_id_generator", sequenceName = "todo_id_sequence")
    @GeneratedValue(generator = "todo_id_generator", strategy = GenerationType.SEQUENCE)
    private long id;
    @Setter
    @NonNull
    private String name;
    @Setter
    @NonNull
    private String description;
    @Setter
    private boolean done = false;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dateCreated = LocalDate.now();
    @NonNull
    @Setter
    LocalDate deadline;

    @Override
    public String toString() {
        return String.format(
                "Todo: {\n\tId: %d,\n\tName: %s,\n\tDescription: %s,\n\tDone: %b,\n\tDate created: %s,\n\tDeadline: %s\n}",
                id, name, description, done, dateCreated, deadline
        );
    }
}
