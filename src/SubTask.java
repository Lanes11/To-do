public class SubTask extends Task{
    Integer epicId;
    public SubTask(int id, String name, String description, Status status, Integer epicId){
        super(id, name, description, status);
        this.epicId = epicId;
    }
}
