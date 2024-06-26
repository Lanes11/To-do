import java.util.ArrayList;

public class Epic extends Task{
    ArrayList<Integer> subTasksId;
    public Epic(int id, String name, String description, Status status, ArrayList<Integer> subTasksId){
        super(id, name, description, status);
        this.subTasksId = subTasksId;
    }
}
