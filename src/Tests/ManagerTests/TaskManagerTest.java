package ManagerTests;

import Body.Interface.TaskManager;

import Model.Enum.*;
import Model.Type.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
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
        taskManager.createTask(new Task(0, "Имя", "Описание", Status.NEW, Duration.ofSeconds(100), LocalDate.now()));
        assertFalse(taskManager.getTasks().isEmpty());
    }

    @Test
    void createEpicTest() {
        taskManager.createEpic(new Epic(0, "Имя", "Описание", Status.NEW, new ArrayList<>(), Duration.ofSeconds(100), LocalDate.now()));
        assertFalse(taskManager.getEpics().isEmpty());
    }

    @Test
    void createSubTaskTest() {
        taskManager.createEpic(new Epic(0, "Имя", "Описание", Status.NEW, new ArrayList<>(), Duration.ofSeconds(100), LocalDate.now()));
        taskManager.createSubTask(new SubTask(0, "Имя", "Описание", Status.NEW, 0, Duration.ofSeconds(100), LocalDate.now()));
        assertFalse(taskManager.getSubTasks().isEmpty());
    }

    @Test
    void epicShouldHaveSubTask() {
        taskManager.createEpic(new Epic(0, "Имя", "Описание", Status.NEW, new ArrayList<>(), Duration.ofSeconds(100), LocalDate.now()));
        taskManager.createSubTask(new SubTask(0, "Имя", "Описание", Status.NEW, 0, Duration.ofSeconds(100), LocalDate.now()));
        assertFalse(taskManager.getEpics().getFirst().getSubTasksId().isEmpty());
    }

    @Test
    void updateTaskTest() {
        taskManager.createTask(new Task(0, "Имя", "Описание", Status.NEW, Duration.ofSeconds(100), LocalDate.now()));
        taskManager.updateTask(new Task(0, "Имя2", "Описание", Status.NEW, Duration.ofSeconds(100), LocalDate.now()));
        assertEquals("Имя2", taskManager.getTask(0).getName());
    }

    @Test
    void updateEpicTest() {
        taskManager.createEpic(new Epic(0, "Имя", "Описание", Status.NEW, new ArrayList<>(), Duration.ofSeconds(100), LocalDate.now()));
        taskManager.updateEpic(new Epic(0, "Имя2", "Описание", Status.NEW, new ArrayList<>(), Duration.ofSeconds(100), LocalDate.now()));
        assertEquals("Имя2", taskManager.getEpic(0).getName());
    }

    @Test
    void updateSubTaskTest() {
        taskManager.createEpic(new Epic(0, "Имя", "Описание", Status.NEW, new ArrayList<>(), Duration.ofSeconds(100), LocalDate.now()));
        taskManager.createSubTask(new SubTask(1, "Имя", "Описание", Status.NEW, 0, Duration.ofSeconds(100), LocalDate.now()));
        taskManager.updateSubTask(new SubTask(1, "Имя2", "Описание", Status.NEW, 0, Duration.ofSeconds(100), LocalDate.now()));
        assertEquals("Имя2", taskManager.getSubTask(1).getName());
    }
}