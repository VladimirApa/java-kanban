package yandex.app.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yandex.app.model.*;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    TaskManager taskManager;
    HistoryManager historyManager;


    @BeforeEach
    void BeforeEach() {
        taskManager = Managers.getDefault();
        historyManager = Managers.getDefaultHistory();

        Task task = new Task("Один", "One");
        taskManager.addTask(task);
        historyManager.addTaskHistory(task);
    }

    @Test
    void addHistoryTest() {
        Task task1 = new Task("Один Таск", "One Task");
        Epic epic = new Epic("Один Епик", "One Epic");
        SubTask subTask = new SubTask("Один сабтаск", "One SubTask", 1);

        historyManager.addTaskHistory(task1);
        historyManager.addTaskHistory(epic);
        historyManager.addTaskHistory(subTask);

        assertEquals(4, historyManager.getHistory().size());
        assertTrue(historyManager.getHistory().contains(task1));
        assertTrue(historyManager.getHistory().contains(epic));
        assertTrue(historyManager.getHistory().contains(subTask));
    }

    @Test
    void deletingTheFirstTaskBeforeTheListTest() {

        Task taskNew = new Task("Новый таск", "Task New ");
        for (int i = 0; i < 10; i++) {
            historyManager.addTaskHistory(new Task("Задача " + i, "Описание"));
        }
        historyManager.addTaskHistory(taskNew);

        assertEquals(10, historyManager.getHistory().size());
        assertEquals(taskNew, historyManager.getHistory().get(9));

    }
}