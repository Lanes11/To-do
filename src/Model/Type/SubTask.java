package Model.Type;

import Model.Enum.Status;

import java.time.Duration;
import java.time.LocalDateTime;

public class SubTask extends Task {

    private final Integer epicId;

    public SubTask(int id, String name, String description, Status status, Integer epicId, Duration duration, LocalDateTime startTime){
        super(id, name, description, status, duration, startTime);
        this.epicId = epicId;
    }

    public Integer getEpicId(){
        return epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", epicId='" + getEpicId() + '\'' +
                ", startTime='" + getStartTime() + '\'' +
                ", endTime='" + getEndTime() + '\'' +
                '}' + '\n';
    }
}
