import java.util.ArrayList;

public class Epic extends Task{
    ArrayList<Integer> subTaskIdentifiers;
    public Epic(String name, String description, Status status, ArrayList<Integer> subTasksHashCode){
        super(name, description, status);
        this.subTaskIdentifiers = subTasksHashCode;
    }
}
