package Body.Manager;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Model.Type.*;
import Model.Enum.*;

public class FileBackendTasksManager extends InMemoryTaskManager{
    private static final Logger logger = Logger.getLogger(FileBackendTasksManager.class.getName());

    public static void toSave(){
        try (FileWriter save = new FileWriter("Save.csv")) {
            save.write("id,type,name,description,status,epicId");
            for (Task task: tasks.values()){
                save.write("\n" + task.id + ',' + Type.TASK + ',' +  task.name + ',' + task.description + ',' + task.status + ',');
            }
            for (Epic epic: epics.values()){
                save.write("\n" + epic.id + ',' + Type.EPIC + ',' +  epic.name + ',' + epic.description + ',' + epic.status + ',');
            }
            for (SubTask subTask: subTasks.values()){
                save.write("\n" + subTask.id + ',' + Type.SUBTASK + ',' +  subTask.name + ',' + subTask.description + ',' + subTask.status + ',' + subTask.epicId);
            }

            save.write("\n\n");

            for (Task task: historyManager.getHistory()){
                save.write(String.valueOf(task.id) + ',');
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
    }
    
    @Override
    public void createTask(String name, String description){
        super.createTask(name, description);
        toSave();

    }

    @Override
    public void createEpic(String name, String description){
        super.createEpic(name, description);
        toSave();
    }

    @Override
    public void createSubTask(String name, String description, int epicId){
        super.createSubTask(name, description, epicId);
        toSave();
    }

    @Override
    public void getTask(int id){
        super.getTask(id);
        toSave();
    }

    @Override
    public void getEpic(int id){
        super.getEpic(id);
        toSave();
    }

    @Override
    public void getSubTask(int id){
        super.getSubTask(id);
        toSave();
    }

    @Override
    public void printMainMenu(){
        fromSave();
        super.printMainMenu();
    }

    public void fromSave(){
        try{
            List<String> lines = Files.readAllLines(Paths.get("Save.csv"));
            lines.removeFirst();
            for (String line : lines) {
                if (line.length()>3){
                    String[] values = line.split(",");
                    if (values[1].equals(Type.TASK.toString())){
                        tasks.put(Integer.parseInt(values[0]), new Task(Integer.parseInt(values[0]), values[2], values[3], Status.valueOf(values[4])));
                    } else if (values[2].equals(Type.SUBTASK.toString())){
                        subTasks.put(Integer.parseInt(values[0]), new SubTask(Integer.parseInt(values[0]), values[2], values[3], Status.valueOf(values[4]), Integer.parseInt(values[5])));
                    } else if (values[2].equals(Type.EPIC.toString())){
                        epics.put(Integer.parseInt(values[0]), new Epic(Integer.parseInt(values[0]), values[2], values[3], Status.valueOf(values[4]), new ArrayList<>()));
                    } else {
                        for (String value: values){
                            if(tasks.get(Integer.parseInt(value))!=null){
                                historyManager.add(tasks.get(Integer.parseInt(value)));
                            } else if (subTasks.get(Integer.parseInt(value))!=null){
                                historyManager.add(subTasks.get(Integer.parseInt(value)));
                            } else if (epics.get(Integer.parseInt(value))!=null){
                                historyManager.add(epics.get(Integer.parseInt(value)));
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
    }
}
