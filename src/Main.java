public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        taskManager.createTask("Задача", "Описание задачи");
        taskManager.createEpic("Эпик", "Описание эпика");
        taskManager.createSubTask("Подзадача", "Описание подзадачи", 1);
        taskManager.printAllTasks();
        System.out.println(" ");
        taskManager.checkingStatusAndSubTasksEpics();
        taskManager.printAllTasks();
        System.out.println(" ");
        taskManager.getById(0);
        taskManager.getById(1);
        System.out.println(" ");
        System.out.println(taskManager.historyManager.getHistory());
    }
}