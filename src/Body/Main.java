package Body;

import Body.Manager.FileBackendTaskManager;
import Body.Manager.InMemoryTaskManager;

public class Main {
    public static void main(String[] args) {
        FileBackendTaskManager taskManager = new FileBackendTaskManager();
        taskManager.printMainMenu();
    }
}
