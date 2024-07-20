package Type;

import Enum.*;

public class Task {
    public int id;
    public String name;
    public String description;
    public Status status;

    public Task(int id, String name, String description, Status status){
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }
}
