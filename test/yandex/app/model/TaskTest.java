package yandex.app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import yandex.app.manager.InMemoryHistoryManager;
import yandex.app.manager.InMemoryTaskManager;
import yandex.app.manager.Managers;
import yandex.app.manager.TaskManager;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    TaskManager taskManager = Managers.getDefault();

    @Test
    void taskIsEqualToEachOtherTest() {
        Task task1 = new Task("Поели", "Можно и поспать");
        Task task2 = new Task("Поспали", "Можно и поесть");
        assertEquals(task1, task2);
    }

    // Хотел сравнить две новые задачи, но я не нашел решения, решил что могу сравнить на отрицание.
    // доработаю (как придет осознание)
    @Test
    void heirTaskIsEqualToEachOtherTest() {
        taskManager.addTask(new Task("Цифра один", "цифра два"));
        taskManager.addTask(new Task("кофе", "Американо"));
        ArrayList<Task> tasks = taskManager.getTasks();
        assertNotEquals(tasks.get(0).getName(), tasks.get(1).getName());

    }

}