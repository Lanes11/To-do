package Body;

import Body.Manager.FileBackendTasksManager;

public class Main {
    public static void main(String[] args) {
        FileBackendTasksManager fileBackendTasksManager = new FileBackendTasksManager();
        fileBackendTasksManager.printMainMenu();
    }
}