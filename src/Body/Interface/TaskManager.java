package Body.Interface;

import Model.Type.*;

import java.util.ArrayList;
import java.util.HashMap;

public interface TaskManager {
    /*Create*/
    void createTask(Task task);
    void createEpic(Epic epic);
    void createSubTask(SubTask subtask);

    /*Update*/
    void updateEnity(int id);
    void updateTask(Task task);
    void updateEpic(Epic epic);
    void updateSubTask(SubTask subTask);

    /*Get Tasks*/
    ArrayList<Task> getTasks();
    ArrayList<Epic> getEpics();
    ArrayList<SubTask> getSubTasks();

    /*Get by id*/
    Task getTask(int id);
    Epic getEpic(int id);
    SubTask getSubTask(int id);

    /*Delete*/
    void deleteAllTasks();
    void deleteById(int id);

    /*Print*/
    void printAllTasks();
    void printById(int id);

    void checkingStatusAndSubTasksEpics();
}
