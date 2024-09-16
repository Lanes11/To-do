package ManagerTests;

import Body.Manager.InMemoryHistoryManager;
import Model.Enum.Status;
import Model.Type.Epic;
import Model.Type.SubTask;
import Model.Type.Task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HistoryManagerTest {
    private final InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    @Test
    void addTest() {
        Task task = new Task(0, "Задача", "Описание задачи", Status.NEW);
        Epic epic = new Epic(1, "Эпик", "Описание эпика", Status.NEW, new ArrayList<>());
        SubTask subTask = new SubTask(2, "Подзадача", "Описание подзадачи", Status.NEW, 1);

        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(subTask);

        assertEquals(task, historyManager.getHistory().get(0), "Задача не добавилась в историю");
        assertEquals(epic, historyManager.getHistory().get(1), "Задача не добавилась в историю");
        assertEquals(subTask, historyManager.getHistory().get(2), "Задача не добавилась в историю");
    }

    @Test
    void removeTest() {
        historyManager.add(new Task(0, "Задача", "Описание задачи", Status.NEW));
        historyManager.remove(0);
        assertTrue(historyManager.getHistory().isEmpty(), "Задача не удалилась из истории");
    }

    @Test
    void getHistoryTest() {
        Task task = new Task(0, "Задача", "Описание задачи", Status.NEW);
        historyManager.add(task);
        assertEquals(task, historyManager.getHistory().getFirst());

        historyManager.remove(0);

        assertTrue(historyManager.getHistory().isEmpty());
    }
}