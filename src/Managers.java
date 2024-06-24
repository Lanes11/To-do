public class Managers {
    public static TaskManager getDeafault(){
        return new InMemoryTaskManager();
    }

    static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }
}
