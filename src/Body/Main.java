package Body;

import Body.Manager.FileBackendTaskManager;

public class Main {
    public static void main(String[] args) {
        FileBackendTaskManager fileBackendTaskManager= new FileBackendTaskManager();
        fileBackendTaskManager.printMainMenu();
    }
}
