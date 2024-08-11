package Model.Type;

import Model.Enum.Status;

public class SubTask extends Task {

    public Integer epicId;

    public SubTask(int id, String name, String description, Status status, Integer epicId){
        super(id, name, description, status);
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                ", epicId='" + epicId + '\'' +
                '}' + '\n';
    }
}
