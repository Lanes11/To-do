package Model.Type;

import Model.Enum.Status;

import java.util.ArrayList;

public class Epic extends Task {

    public ArrayList<Integer> subTasksId;

    public Epic(int id, String name, String description, Status status, ArrayList<Integer> subTasksId){
        super(id, name, description, status);
        this.subTasksId = subTasksId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                ", subTasksId='" + subTasksId + '\'' +
                '}' + '\n';
    }
}
