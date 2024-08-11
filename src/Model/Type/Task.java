package Model.Type;

import Model.Enum.Status;

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

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                '}' + '\n';
    }
}
