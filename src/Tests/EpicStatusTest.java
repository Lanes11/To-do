import Model.Enum.Status;

import Body.Manager.InMemoryTaskManager;
import Model.Type.Epic;
import Model.Type.SubTask;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EpicStatusTest {
    InMemoryTaskManager taskManager = new InMemoryTaskManager();

    @BeforeEach
    void setUp() {
        taskManager.createEpic(new Epic(taskManager.id, "Имя", "Описание", Status.NEW, new ArrayList<>()));
    }

    @AfterEach
    void tearDown() {
        taskManager.deleteAllTasks();
    }

    @Test
    void statusShouldBeNewIfListOfSubtasksIsEmpty() {
        assertEquals(Status.NEW, taskManager.getEpic(0).getStatus());
    }

    @Test
    void statusShouldBeNewIfListOfSubtasksIsAllNew() {
        taskManager.createSubTask(new SubTask(taskManager.id, "Имя", "Описание", Status.NEW, 0, Duration.ofSeconds(100), LocalDateTime.now()));
        taskManager.createSubTask(new SubTask(taskManager.id, "Имя", "Описание", Status.NEW, 0, Duration.ofSeconds(100), LocalDateTime.now()));
        taskManager.createSubTask(new SubTask(taskManager.id, "Имя", "Описание", Status.NEW, 0, Duration.ofSeconds(100), LocalDateTime.now()));

        assertEquals(Status.NEW, taskManager.getEpic(0).getStatus());
    }

    @Test
    void statusShouldBeDoneIfListOfSubtasksIsAllDone() {
        taskManager.createSubTask(new SubTask(taskManager.id, "Имя", "Описание", Status.DONE, 0, Duration.ofSeconds(100), LocalDateTime.now()));
        taskManager.createSubTask(new SubTask(taskManager.id, "Имя", "Описание", Status.DONE, 0, Duration.ofSeconds(100), LocalDateTime.now()));
        taskManager.createSubTask(new SubTask(taskManager.id, "Имя", "Описание", Status.DONE, 0, Duration.ofSeconds(100), LocalDateTime.now()));

        assertEquals(Status.DONE, taskManager.getEpic(0).getStatus());
    }

    @Test
    void statusShouldBeIn_progressIfListOfSubtasksIsAllIn_progress() {
        taskManager.createSubTask(new SubTask(taskManager.id, "Имя", "Описание", Status.IN_PROGRESS, 0, Duration.ofSeconds(100), LocalDateTime.now()));
        taskManager.createSubTask(new SubTask(taskManager.id, "Имя", "Описание", Status.IN_PROGRESS, 0, Duration.ofSeconds(100), LocalDateTime.now()));
        taskManager.createSubTask(new SubTask(taskManager.id, "Имя", "Описание", Status.IN_PROGRESS, 0, Duration.ofSeconds(100), LocalDateTime.now()));

        assertEquals(Status.IN_PROGRESS, taskManager.getEpic(0).getStatus());
    }

    @Test
    void statusShouldBeIn_progressIfListOfSubtasksIsAllNewAndDone() {
        taskManager.createSubTask(new SubTask(taskManager.id, "Имя", "Описание", Status.DONE, 0, Duration.ofSeconds(100), LocalDateTime.now()));
        taskManager.createSubTask(new SubTask(taskManager.id, "Имя", "Описание", Status.NEW, 0, Duration.ofSeconds(100), LocalDateTime.now()));
        taskManager.createSubTask(new SubTask(taskManager.id, "Имя", "Описание", Status.NEW, 0, Duration.ofSeconds(100), LocalDateTime.now()));

        assertEquals(Status.IN_PROGRESS, taskManager.getEpic(0).getStatus());
    }
}
