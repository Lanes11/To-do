package Model.Type;

import Model.Enum.Status;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Integer> subTasksId;

    public Epic(int id, String name, String description, Status status, ArrayList<Integer> subTasksId){
        super(id, name, description, status, Duration.ofSeconds(0), LocalDateTime.now());
        this.subTasksId = subTasksId;
    }

    public ArrayList<Integer> getSubTasksId(){
        return subTasksId;
    }

    public void addSubTasksId(int id){
        this.subTasksId.add(id);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", subTasksId='" + getSubTasksId() + '\'' +
                ", startTime='" + getStartTime() + '\'' +
                ", endTime='" + getEndTime() + '\'' +
                '}' + '\n';
    }
}
