package Model.Type;

import java.time.Duration;
import java.time.LocalDateTime;

import Model.Enum.Status;

public class Task {

    private final int id;
    private final String name;
    private final String description;
    private Status status;

    private Duration duration;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public Task(int id, String name, String description, Status status, Duration duration, LocalDateTime startTime){
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;

        this.duration = duration;
        this.startTime = startTime;
        this.endTime = startTime.plus(duration);
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public Duration getDuration(){
        return duration;
    }

    public void setDuration(Duration duration){
        this.duration = duration;
    }

    public LocalDateTime getStartTime(){
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime){
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime(){
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime){
        this.endTime = endTime;
    }


    @Override
    public String toString() {
        return "Task{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", startTime='" + getStartTime() + '\'' +
                ", endTime='" + getEndTime() + '\'' +
                '}' + '\n';
    }
}
