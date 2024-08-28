package ManagerTests;

import Body.Interface.TaskManager;

import Model.Enum.*;
import Model.Type.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public abstract class TaskManagerTest<T extends TaskManager> {
    protected T taskManager;

    abstract T createTaskManager();

    @BeforeEach
    void setUp() {
        taskManager = createTaskManager();
    }

    @Test
    void createTaskTest() {
        taskManager.createTask(new Task(0, "Имя", "Описание", Status.NEW));
        assertFalse(taskManager.getTasks().isEmpty());
    }

    @Test
    void createEpicTest() {
        taskManager.createEpic(new Epic(0, "Имя", "Описание", Status.NEW, new ArrayList<>()));
        assertFalse(taskManager.getEpics().isEmpty());
    }

    @Test
    void createSubTaskTest() {
        taskManager.createEpic(new Epic(0, "Имя", "Описание", Status.NEW, new ArrayList<>()));
        taskManager.createSubTask(new SubTask(0, "Имя", "Описание", Status.NEW, 0));
        assertFalse(taskManager.getSubTasks().isEmpty());
    }

    @Test
    void epicShouldHaveSubTask() {
        taskManager.createEpic(new Epic(0, "Имя", "Описание", Status.NEW, new ArrayList<>()));
        taskManager.createSubTask(new SubTask(0, "Имя", "Описание", Status.NEW, 0));
        assertFalse(taskManager.getEpics().getFirst().subTasksId.isEmpty());
    }

    @Test
    void updateTaskTest() {
        taskManager.createTask(new Task(0, "Имя", "Описание", Status.NEW));
        taskManager.updateTask(new Task(0, "Имя2", "Описание", Status.NEW));
        assertEquals("Имя2", taskManager.getTask(0).name);
    }

    @Test
    void updateEpicTest() {
        taskManager.createEpic(new Epic(0, "Имя", "Описание", Status.NEW, new ArrayList<>()));
        taskManager.updateEpic(new Epic(0, "Имя2", "Описание", Status.NEW, new ArrayList<>()));
        assertEquals("Имя2", taskManager.getEpic(0).name);
    }

    @Test
    void updateSubTaskTest() {
        taskManager.createEpic(new Epic(0, "Имя", "Описание", Status.NEW, new ArrayList<>()));
        taskManager.createSubTask(new SubTask(1, "Имя", "Описание", Status.NEW, 0));
        taskManager.updateSubTask(new SubTask(1, "Имя2", "Описание", Status.NEW, 0));
        assertEquals("Имя2", taskManager.getSubTask(1).name);
    }
}