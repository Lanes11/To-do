import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    Scanner scanner = new Scanner(System.in);

    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, SubTask> subTasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

    int id = 0;

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
                    "8. Удалить по индентификатору\n"+
                    "9. Посмотреть историю просмотренных задач");

            int operation = scanner.nextInt();
            switch (operation) {
                case 1 -> {
                    String[] taskDetails = readTaskDetails();
                    createTask(taskDetails[0], taskDetails[1]);
                }
                case 2 -> {
                    String[] taskDetails = readTaskDetails();
                    createEpic(taskDetails[0], taskDetails[1]);
                }
                case 3 -> {
                    String[] taskDetails = readTaskDetails();
                    System.out.print("Эпик: ");
                    int epicId = scanner.nextInt();
                    createSubTask(taskDetails[0], taskDetails[1], epicId);
                }
                case 4 -> updateEnity();
                case 5 -> printAllTasks();
                case 6 -> deletingAllTasks();
                case 7 -> {
                    System.out.println("Id: ");
                    getById(scanner.nextInt());
                }
                case 8 -> {
                    System.out.println("Id: ");
                    deleteById(scanner.nextInt());
                }
                case 9 -> System.out.println(historyManager.getHistory());
                default -> System.out.println("Такой операции нет!");
            }
        }
    }

    @Override
    public String[] readTaskDetails() {
        scanner.nextLine();
        System.out.print("Название: ");
        String name = scanner.nextLine();
        System.out.print("Описание: ");
        String description = scanner.nextLine();
        return new String[] { name, description };
    }

    @Override
    public void createTask(String name, String description) {
        tasks.put(id, new Task(id, name, description, Status.NEW));
        id++;
    }

    @Override
    public void createEpic(String name, String description) {
        epics.put(id, new Epic(id, name, description, Status.NEW, new ArrayList<>()));
        id++;
    }

    @Override
    public void createSubTask(String name, String description, int epicId) {
        subTasks.put(id, new SubTask(id, name, description, Status.NEW, epicId));
        id++;
    }

    @Override
    public void updateEnity(){
        System.out.println("Выберите операцию:\n" +
                "1. Обновление задачи\n" +
                "2. Обновление эпика\n" +
                "3. Обновление подзадачи");
        int operation = scanner.nextInt();

        System.out.print("Id: ");
        int id = scanner.nextInt();

        if (!tasks.containsKey(id) && !epics.containsKey(id) && !subTasks.containsKey(id)) {
            System.out.println("Задачи с таким id нет!");
        } else {
            switch (operation) {
                case 1 -> {
                    String[] taskDetails = readTaskDetails();
                    updateTask(id, taskDetails[0], taskDetails[1]);
                }
                case 2 -> {
                    String[] taskDetails = readTaskDetails();
                    updateEpic(id, taskDetails[0], taskDetails[1]);
                }
                case 3 -> {
                    String[] taskDetails = readTaskDetails();
                    System.out.print("Эпик: ");
                    int epicId = scanner.nextInt();
                    updateSubTask(id, taskDetails[0], taskDetails[1], epicId);
                }
                default -> System.out.println("Такой операции нет!");
            }
        }
    }

    @Override
    public void updateTask(int id, String name, String description) {
        tasks.put(id, new Task(id, name, description, Status.NEW));
    }

    @Override
    public void updateEpic(int id, String name, String description) {
        epics.put(id, new Epic(id, name, description, Status.NEW, new ArrayList<>()));
    }

    @Override
    public void updateSubTask(int id, String name, String description, int epicId) {
        subTasks.put(id, new SubTask(id, name, description, Status.NEW, epicId));
    }

    @Override
    public void printAllTasks() {
        printTasks();
        printEpics();
        printSubTasks();
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
    public void getById(int id) {
        if (tasks.containsKey(id)) {
            getTask(id);
        } else if (epics.containsKey(id)) {
            getEpic(id);
        } else if (subTasks.containsKey(id)) {
            getSubTask(id);
        } else {
            System.out.println("Задачи с таким id нет!");
        }
    }

    @Override
    public void getTask(int id){
        Task task = tasks.get(id);
        System.out.println(id + ": имя — " + task.name + "; описание — " + task.description + "; статус — " + task.status);

        historyManager.add(task);
    }

    @Override
    public void getEpic(int id){
        Epic epic = epics.get(id);
        System.out.println(id + ": имя — " + epic.name + "; описание — " + epic.description + "; статус — " + epic.status + "; подзадачи — " + epic.subTasksId);

        historyManager.add(epic);
    }

    @Override
    public void getSubTask(int id){
        SubTask subTask = subTasks.get(id);
        System.out.println(id + ": имя — " + subTask.name + "; описание — " + subTask.description + "; статус — " + subTask.status + "; эпик — " + subTask.epicId);

        historyManager.add(subTask);
    }

    @Override
    public void deleteById(int id) {
        if (tasks.remove(id)==null && subTasks.remove(id)==null && epics.remove(id)==null) {
            System.out.println("Такого индентификатора нет!");
        }
    }

    @Override
    public void checkingStatusAndSubTasksEpics() {
        for (Integer indexEpic : epics.keySet()) {
            Epic epic = epics.get(indexEpic);
            int countInProgress = 0, countDone = 0, countNew = 0;
            for(int k = 0; k<epic.subTasksId.size(); k++){
                for (int indexSubTaskIdentifier : epic.subTasksId) {
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
                if (subtask.epicId.equals(indexEpic) && !epic.subTasksId.contains(indexSubTask)){
                    epic.subTasksId.add(indexSubTask);
                }
            }
        }
    }
}
