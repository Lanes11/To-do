package Body.Manager;

import java.util.*;

import Body.Interface.TaskManager;
import Model.Type.*;
import Model.Enum.*;

public class InMemoryTaskManager implements TaskManager {
    public final InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    Scanner scanner = new Scanner(System.in);

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    public int id = 0;

    public void printMainMenu() {
        while (true) {
            System.out.println("""
                    Выберите операцию:
                    1. Создать задачу
                    2. Создать эпик
                    3. Создать подзадачу
                    4. Обновление
                    5. Список всех задач
                    6. Удаление всех задач
                    7. Получить по индентификатору
                    8. Удалить по индентификатору
                    9. Посмотреть историю просмотренных задач
                    """);

            int operation = scanner.nextInt();
            switch (operation) {
                case 1 -> {
                    String[] taskDetails = readTaskDetails();
                    createTask(new Task(id, taskDetails[0], taskDetails[1], Status.NEW));
                }
                case 2 -> {
                    String[] taskDetails = readTaskDetails();
                    createEpic(new Epic(id, taskDetails[0], taskDetails[1], Status.NEW, new ArrayList<>()));
                }
                case 3 -> {
                    String[] taskDetails = readTaskDetails();
                    System.out.print("Эпик: ");
                    int epicId = scanner.nextInt();
                    createSubTask(new SubTask(id, taskDetails[0], taskDetails[1], Status.NEW, epicId));
                }
                case 4 -> {
                    System.out.print("Id: ");
                    updateEnity(scanner.nextInt());
                }
                case 5 -> printAllTasks();
                case 6 -> deleteAllTasks();
                case 7 -> {
                    System.out.print("Id: ");
                    printById(scanner.nextInt());
                }
                case 8 -> {
                    System.out.print("Id: ");
                    deleteById(scanner.nextInt());
                }
                case 9 -> System.out.println(historyManager.getHistory());
                default -> System.out.println("Такой операции нет!");
            }
        }
    }

    public String[] readTaskDetails() {
        scanner.nextLine();
        System.out.print("Название: ");
        String name = scanner.nextLine();
        System.out.print("Описание: ");
        String description = scanner.nextLine();
        return new String[] { name, description };
    }

    /*Create*/
    @Override
    public void createTask(Task task) {
        tasks.put(id, task);
        id++;
    }

    @Override
    public void createEpic(Epic epic) {
        epics.put(id, epic);
        id++;
    }

    @Override
    public void createSubTask(SubTask subTask) {
        subTasks.put(id, subTask);
        id++;

        checkingStatusAndSubTasksEpics();
    }

    /*Update*/
    @Override
    public void updateEnity(int id){
        if(tasks.containsKey(id)) {
            String[] taskDetails = readTaskDetails();
            updateTask(new Task(id, taskDetails[0], taskDetails[1], Status.NEW));
        } else if (epics.containsKey(id)) {
            String[] taskDetails = readTaskDetails();
            updateEpic(new Epic(id, taskDetails[0], taskDetails[1], Status.NEW, new ArrayList<>()));
        } else if (subTasks.containsKey(id)) {
            String[] taskDetails = readTaskDetails();
            System.out.print("Эпик: ");
            int epicId = scanner.nextInt();
            updateSubTask(new SubTask(id, taskDetails[0], taskDetails[1], Status.NEW, epicId));
        } else {
            System.out.println("Задачи с таким id нет!");
        }
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.id, task);
    }

    @Override
    public void updateEpic(Epic epic) {
        epics.put(epic.id, epic);
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        subTasks.put(subTask.id, subTask);

        checkingStatusAndSubTasksEpics();
    }

    /*Get Tasks*/
    @Override
    public ArrayList<Task> getTasks(){
        ArrayList<Task> arrayTasks = new ArrayList<>();

        for(int id: tasks.keySet()) {
            Task task = tasks.get(id);
            arrayTasks.add(task);
        }

        return arrayTasks;
    }

    @Override
    public ArrayList<Epic> getEpics(){
        ArrayList<Epic> arrayEpics = new ArrayList<>();

        for(int id: epics.keySet()) {
            Epic epic = epics.get(id);
            arrayEpics.add(epic);
        }

        return arrayEpics;
    }

    @Override
    public ArrayList<SubTask> getSubTasks(){
        ArrayList<SubTask> arraySubTask = new ArrayList<>();

        for(int id: epics.keySet()) {
            SubTask subTask = subTasks.get(id);
            arraySubTask.add(subTask);
        }

        return arraySubTask;
    }

    /*Get by id*/
    @Override
    public Task getTask(int id){
        Task task = tasks.get(id);
        historyManager.add(task);

        return task;
    }

    @Override
    public Epic getEpic(int id){
        Epic epic = epics.get(id);
        historyManager.add(epic);

        return epic;
    }

    @Override
    public SubTask getSubTask(int id){
        SubTask subTask = subTasks.get(id);
        historyManager.add(subTask);

        return subTask;
    }

    /*Delete*/
    @Override
    public void deleteAllTasks() {
        tasks.clear();
        subTasks.clear();
        epics.clear();
    }

    @Override
    public void deleteById(int id) {
        if (tasks.remove(id)==null && subTasks.remove(id)==null && epics.remove(id)==null) {
            System.out.println("Такого индентификатора нет!");
        }
    }

    /*Print*/
    @Override
    public void printAllTasks() {
        System.out.println("Задачи: ");
        for (Integer indexTask : tasks.keySet()) {
            System.out.println(tasks.get(indexTask));
        }

        System.out.println("Эпики: ");
        for (Integer indexEpic : epics.keySet()) {
            System.out.println(epics.get(indexEpic));
        }

        System.out.println("Подзадачи: ");
        for (Integer indexSubTask : subTasks.keySet()) {
            System.out.println(subTasks.get(indexSubTask));
        }
    }

    @Override
    public void printById(int id) {
        if (tasks.containsKey(id)) {
            System.out.println(getTask(id));
        } else if (epics.containsKey(id)) {
            System.out.println(getEpic(id));
        } else if (subTasks.containsKey(id)) {
            System.out.println(getEpic(id));
        } else {
            System.out.println("Задачи с таким id нет!");
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
