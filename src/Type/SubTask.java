package Type;

import Enum.*;

public class SubTask extends Task {
    public Integer epicId;
    public SubTask(int id, String name, String description, Status status, Integer epicId){
        super(id, name, description, status);
        this.epicId = epicId;
    }
}
