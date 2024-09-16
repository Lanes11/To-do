package Body.Manager;

import java.time.Duration;
import java.time.LocalDateTime;
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

public class FileBackendTaskManager extends InMemoryTaskManager{
    private static final Logger logger = Logger.getLogger(FileBackendTaskManager.class.getName());

    public void save(){
        try (FileWriter save = new FileWriter("Save.csv")) {
            save.write("id,type,name,description,status,epicId,duration,startTime\n");

            for (int taskId: tasks.keySet()){
                Task task = tasks.get(taskId);
                save.write("\n" + task.getId() + ',' + Type.TASK + ',' +  task.getName() + ',' + task.getDescription() + ',' + task.getStatus() + ',');
            }
            for (int epicId: epics.keySet()){
                Epic epic = epics.get(epicId);
                save.write("\n" + epic.getId() + ',' + Type.EPIC + ',' +  epic.getName() + ',' + epic.getDescription() + ',' + epic.getStatus() + ',');
            }
            for (int subTaskId: subTasks.keySet()){
                SubTask subTask = subTasks.get(subTaskId);
                save.write("\n" + subTask.getId() + ',' + Type.SUBTASK + ',' +  subTask.getName() + ',' + subTask.getDescription() + ',' + subTask.getStatus() + ',' + subTask.getEpicId());
            }

            save.write("\n\n");

            for (Task task: historyManager.getHistory()){
                save.write(String.valueOf(task.getId()) + ',');
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }
    }

    @Override
    public void printMainMenu(){
        save();
        super.printMainMenu();
    }
    
    @Override
    public void createTask(Task task){
        super.createTask(task);
        save();
    }

    @Override
    public void createEpic(Epic epic){
        super.createEpic(epic);
        save();
    }

    @Override
    public void createSubTask(SubTask subTask){
        super.createSubTask(subTask);
        save();
    }

    @Override
    public Task getTask(int id){
        save();

        return super.getTask(id);
    }

    @Override
    public Epic getEpic(int id){
        save();

        return super.getEpic(id);
    }

    @Override
    public SubTask getSubTask(int id){
        save();

        return super.getSubTask(id);
    }

    public void read(){
        try{
            List<String> lines = Files.readAllLines(Paths.get("Save.csv"));
            lines.removeFirst();
            for (String line : lines) {
                if (line.length()>3){
                    String[] values = line.split(",");
                    if (values[1].equals("TASK")){
                        tasks.put(id, new Task(Integer.parseInt(values[0]), values[2], values[3], Status.valueOf(values[4]), Duration.ofSeconds(100), LocalDateTime.now()));
                    } else if (values[1].equals("SUBTASK")){
                        subTasks.put(id, new SubTask(Integer.parseInt(values[0]), values[2], values[3], Status.valueOf(values[4]), Integer.parseInt(values[5]), Duration.ofSeconds(100), LocalDateTime.now()));
                    } else if (values[1].equals("EPIC")){
                        epics.put(id, new Epic(Integer.parseInt(values[0]), values[2], values[3], Status.valueOf(values[4]), new ArrayList<>()));
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
