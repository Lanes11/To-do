package Body;

import Body.Interface.HistoryManager;
import Body.Interface.TaskManager;
import Body.Manager.InMemoryHistoryManager;
import Body.Manager.InMemoryTaskManager;

public class Managers {
    public static TaskManager getDeafault(){
        return new InMemoryTaskManager();
    }

    static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}
