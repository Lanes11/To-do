public class SubTask extends Task{
    Integer epicIdentifier;
    public SubTask(String name, String description, Status status, Integer epicHashCode){
        super(name, description, status);
        this.epicIdentifier = epicHashCode;
    }
}
