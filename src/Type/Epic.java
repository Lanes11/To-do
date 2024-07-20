package Type;

import java.util.ArrayList;
import Enum.*;

public class Epic extends Task {
    public ArrayList<Integer> subTasksId;
    public Epic(int id, String name, String description, Status status, ArrayList<Integer> subTasksId){
        super(id, name, description, status);
        this.subTasksId = subTasksId;
    }
}
