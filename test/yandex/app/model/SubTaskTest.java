package yandex.app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yandex.app.manager.Managers;
import yandex.app.manager.TaskManager;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskTest {
    static TaskManager taskManager;

    @BeforeEach
    void beforeEach() {
        taskManager = Managers.getDefault();
    }

    @Test
    void sabtaskIsNotEpicTest() {
        Epic epic = new Epic("Задача ", "номер один");
        taskManager.addEpic(epic);

        SubTask task1 = new SubTask("Один", "one", 1);

        task1.setId(task1.getId());
        assertNotEquals(task1.getId(), task1.getEpicId());

    }

}