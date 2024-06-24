public interface TaskManager {
    void printMainMenu();
    Task cteateTask();
    Epic cteateEpic();
    SubTask createSubTask();
    void updateTask();
    void updateEpic();
    void updateSubTask();
    void printTasks();
    void printEpics();
    void printSubTasks();
    void updateEnity();
    void getByIdentifier();
    void getTask(int identifier);
    void getEpic(int identifier);
    void getSubTask(int identifier);
    void deletingAllTasks();
    void deleteByIdentifier();
    void checkingStatusAndSubTasksEpics();
}
