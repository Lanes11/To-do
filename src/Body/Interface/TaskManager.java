package Body.Interface;

public interface TaskManager {
    void printMainMenu();
    String[] readTaskDetails();
    void createTask(String name, String description);
    void createEpic(String name, String description);
    void createSubTask(String name, String description, int epicId);
    void updateEnity();
    void updateTask(int id, String name, String description);
    void updateEpic(int id, String name, String description);
    void updateSubTask(int id, String name, String description, int epicId);
    void printTasks();
    void printEpics();
    void printSubTasks();
    void printAllTasks();
    void getById(int id);
    void getTask(int id);
    void getEpic(int id);
    void getSubTask(int id);
    void deletingAllTasks();
    void deleteById(int id);
    void checkingStatusAndSubTasksEpics();
}
