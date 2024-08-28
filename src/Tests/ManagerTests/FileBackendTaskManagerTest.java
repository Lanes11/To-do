package ManagerTests;

import Body.Manager.FileBackendTaskManager;

import Model.Enum.*;
import Model.Type.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileBackendTaskManagerTest extends TaskManagerTest<FileBackendTaskManager> {

    @Override
    FileBackendTaskManager createTaskManager() {
        return new FileBackendTaskManager();
    }

    @Test
    void toSaveTest(){
        taskManager.createTask(new Task(0, "Задача", "Описание задачи", Status.NEW));
        taskManager.createEpic(new Epic(1, "Эпик", "Описание эпика", Status.NEW, new ArrayList<>()));
        taskManager.createSubTask(new SubTask(2, "Подзадача", "Описание подзадачи", Status.NEW, 1));

        try{
            List<String> lines = Files.readAllLines(Paths.get("Save.csv"));
            assertEquals(lines.getFirst(), "id,type,name,description,status,epicId", "Шапка файла save.csv не та");
            assertEquals(lines.get(1), "0,TASK,Задача,Описание задачи,NEW,", "Неправильно сохранилась задача");
            assertEquals(lines.get(2), "1,EPIC,Эпик,Описание эпика,NEW,", "Неправильно сохранился эпик");
            assertEquals(lines.get(3), "2,SUBTASK,Подзадача,Описание подзадачи,NEW,1", "Неправильно сохранилась подзадача");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}