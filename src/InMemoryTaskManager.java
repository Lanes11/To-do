import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    Scanner scanner = new Scanner(System.in);

    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, SubTask> subTasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

    int identifier = 0;

    @Override
    public void printMainMenu() {
        while (true) {
            checkingStatusAndSubTasksEpics();

            System.out.println("Выберите операцию:\n" +
                    "1. Создать задачу\n" +
                    "2. Создать эпик\n" +
                    "3. Создать подзадачу\n" +
                    "4. Обновление\n" +
                    "5. Список всех задач\n" +
                    "6. Удаление всех задач\n"+
                    "7. Получить по индентификатору\n" +
                    "8. Удалить по индентификатору");

            int operation = scanner.nextInt();
            switch (operation) {
                case 1 -> tasks.put(identifier++, cteateTask());
                case 2 -> epics.put(identifier++, cteateEpic());
                case 3 -> subTasks.put(identifier++, createSubTask());
                case 4 -> updateEnity();
                case 5 -> {
                    printTasks();
                    printEpics();
                    printSubTasks();
                }
                case 6 -> deletingAllTasks();
                case 7 -> getByIdentifier();
                case 8 -> deleteByIdentifier();
                case 9 -> System.out.println(historyManager.getHistory());
                default -> System.out.println("Такой операции нет!");
            }
        }
    }

    @Override
    public Task cteateTask() {
        scanner.nextLine();
        System.out.print("Название: ");
        String name = scanner.nextLine();
        System.out.print("Описание: ");
        String description = scanner.nextLine();

        return new Task(name, description, Status.NEW);
    }

    @Override
    public Epic cteateEpic() {
        scanner.nextLine();
        System.out.print("Название: ");
        String name = scanner.nextLine();
        System.out.print("Описание: ");
        String description = scanner.nextLine();

        return new Epic(name, description, Status.NEW, new ArrayList<>());
    }

    @Override
    public SubTask createSubTask() {
        scanner.nextLine();
        System.out.print("Название: ");
        String name = scanner.nextLine();
        System.out.print("Описание: ");
        String description = scanner.nextLine();
        System.out.print("Эпик: ");
        Integer epicIdentifier = scanner.nextInt();

        return new SubTask(name, description, Status.NEW, epicIdentifier);
    }

    @Override
    public void updateEnity(){
        System.out.println("Выберите операцию:\n" +
                "1. Обновление задачи\n" +
                "2. Обновление эпика\n" +
                "3. Обновление подзадачи");

        int operation = scanner.nextInt();

        switch (operation) {
            case 1 -> updateTask();
            case 2 -> updateEpic();
            case 3 -> updateSubTask();
            default -> System.out.println("Такой операции нет!");
        }
    }

    @Override
    public void updateTask() {
        System.out.print("Индекс задачи, которую нужно заменить: ");
        int identifier = scanner.nextInt();

        tasks.put(identifier, cteateTask());
    }

    @Override
    public void updateEpic() {
        System.out.print("Индекс эпика, которого нужно заменить: ");
        int identifier = scanner.nextInt();

        tasks.put(identifier, cteateEpic());
    }

    @Override
    public void updateSubTask() {
        System.out.print("Индекс подзадачи, которой нужно заменить: ");
        int identifier = scanner.nextInt();

        tasks.put(identifier, createSubTask());
    }

    @Override
    public void printTasks() {
        System.out.println("Задачи: ");
        for (Integer indexTask : tasks.keySet()) {
            getTask(indexTask);
        }
    }

    @Override
    public void printEpics() {
        System.out.println("Эпики: ");
        for (Integer indexEpic : epics.keySet()) {
            getEpic(indexEpic);
        }
    }

    @Override
    public void printSubTasks() {
        System.out.println("Подзадачи: ");
        for (Integer indexSubTask : subTasks.keySet()) {
            getSubTask(indexSubTask);
        }
    }

    @Override
    public void deletingAllTasks() {
        tasks.clear();
        subTasks.clear();
        epics.clear();
    }

    @Override
    public void getByIdentifier() {
        System.out.println("Выберите операцию:\n" +
                "1. Получить задачу\n" +
                "2. Получить эпик\n" +
                "3. Получить подзадачу");
        int operation = scanner.nextInt();

        System.out.print("Индентификатор: ");
        int identifier = scanner.nextInt();

        switch (operation) {
            case 1 -> getTask(identifier);
            case 2 -> getEpic(identifier);
            case 3 -> getSubTask(identifier);
            default -> System.out.println("Такой операции нет!");
        }
    }

    @Override
    public void getTask(int identifier){
        Task task = tasks.get(identifier);
        System.out.println(identifier + ": имя — " + task.name + "; описание — " + task.description + "; статус — " + task.status);

        historyManager.add(task);
    }

    @Override
    public void getEpic(int identifier){
        Epic epic = epics.get(identifier);
        System.out.println(identifier + ": имя — " + epic.name + "; описание — " + epic.description + "; статус — " + epic.status + "; подзадачи — " + epic.subTaskIdentifiers);

        historyManager.add(epic);
    }

    @Override
    public void getSubTask(int identifier){
        SubTask subTask = subTasks.get(identifier);
        System.out.println(identifier + ": имя — " + subTask.name + "; описание — " + subTask.description + "; статус — " + subTask.status + "; эпик — " + subTask.epicIdentifier);

        historyManager.add(subTask);
    }

    @Override
    public void deleteByIdentifier() {
        System.out.print("Индентификатор: ");
        int identifier = scanner.nextInt();

        if (tasks.remove(identifier)==null && subTasks.remove(identifier)==null && epics.remove(identifier)==null) {
            System.out.println("Такого индентификатора нет!");
        }
    }

    @Override
    public void checkingStatusAndSubTasksEpics() {
        for (Integer indexEpic : epics.keySet()) {
            Epic epic = epics.get(indexEpic);
            int countInProgress = 0, countDone = 0, countNew = 0;
            for(int k = 0; k<epic.subTaskIdentifiers.size(); k++){
                for (int indexSubTaskIdentifier : epic.subTaskIdentifiers) {
                    if (subTasks.get(indexSubTaskIdentifier).status.equals(Status.IN_PROGRESS)) {
                        countInProgress++;
                    } else if (subTasks.get(indexSubTaskIdentifier).status.equals(Status.DONE)) {
                        countDone++;
                    } else {
                        countNew++;
                    }
                }
            }

            if (countInProgress == 0 && countDone == 0) {
                epic.status = Status.NEW;
            } else if (countInProgress == 0 && countNew == 0) {
                epic.status = Status.DONE;
            } else {
                epic.status = Status.IN_PROGRESS;
            }
        }

        for (Integer indexEpic : epics.keySet()) {
            Epic epic = epics.get(indexEpic);
            for (Integer indexSubTask: subTasks.keySet()){
                SubTask subtask = subTasks.get(indexSubTask);
                if (subtask.epicIdentifier.equals(indexEpic) && !epic.subTaskIdentifiers.contains(indexSubTask)){
                    epic.subTaskIdentifiers.add(indexSubTask);
                }
            }
        }
    }


}
