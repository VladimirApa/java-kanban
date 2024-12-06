package yandex.app.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yandex.app.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    private InMemoryTaskManager taskManager;

    @BeforeEach
    void beforeEach() {
        taskManager = new InMemoryTaskManager();
    }

    @Test
    void addTaskTest() {
        Task task = new Task("Один", "One");
        taskManager.addTask(task);

        List<Task> tasks = taskManager.getTasks();

        assertEquals(1, tasks.size());
        assertEquals(task.getId(), tasks.get(0).getId());
    }

    @Test
    void addEpicTest() {
        Epic epic = new Epic("Один епик", "One Epic");
        taskManager.addEpic(epic);

        List<Epic> epics = taskManager.getEpics();
        assertEquals(1, epics.size());
        assertEquals(epic.getId(), epics.get(0).getId());

    }

    @Test
    void addSubTaskTest() {
        Epic epic = new Epic("Один епик", "One Epic");
        taskManager.addEpic(epic);

        SubTask subtask = new SubTask("Один сабтаск", "One SubTask", epic.getId());
        taskManager.addSubTask(subtask);

        List<SubTask> subtasks = taskManager.getSubTasks();
        assertEquals(1, subtasks.size());
        assertEquals(subtask.getId(), subtasks.get(0).getId());
    }

    @Test
    void TaskByIdTest() {
        Task task1 = new Task("Один", "One");
        taskManager.addTask(task1);

        Task task = taskManager.getTasks().get(0);
        assertNotNull(task);
        assertEquals(task.getId(), task.getId());

    }

    @Test
    void EpicByIdTest() {
        Epic epic = new Epic("Один епик", "One Epic");
        taskManager.addEpic(epic);

        Epic retrievedEpic = taskManager.getEpics().get(0);
        assertNotNull(retrievedEpic);
        assertEquals(epic.getId(), retrievedEpic.getId());
    }

    @Test
    void subTaskByIdTest() {
        Epic epic = new Epic("Один епик", "One Epic");
        taskManager.addEpic(epic);

        SubTask subtask = new SubTask("Один сабтаск", "One SubTask", epic.getId());
        taskManager.addSubTask(subtask);

        SubTask subTasks = taskManager.getSubTasks().get(0);
        assertNotNull(subTasks);
        assertEquals(subtask.getId(), subTasks.getId());
    }

    @Test
    void conflictTest() {
        Task task = new Task("Один", "One");
        task.setId(1);
        taskManager.addTask(task);

        Task tasks = new Task("Три", "three");
        taskManager.addTask(tasks);

        List<Task> allTasks = taskManager.getTasks();
        assertEquals(2, allTasks.size());
        assertEquals(1, task.getId());
        assertNotEquals(1, tasks.getId());
        for (Task task1 : allTasks) {
            if (task != task1) {
                assertNotEquals(task.getId(), task1.getId());
            }
        }
    }

    @Test
    void addingTaskWithChangesTest() {
        // Создаем задачу с определенными полями
        Task task = new Task("Один", "One");
        task.setId(1);

        String name = task.getName();
        String description = task.getDescription();
        TaskStatus status = task.getStatus();

        taskManager.addTask(task);
        Task addedTask = taskManager.getTasks().getLast();

        assertEquals(name, addedTask.getName(), "Название");
        assertEquals(description, addedTask.getDescription(), "Описание");
        assertEquals(status, addedTask.getStatus(), "Статус");
        assertEquals(1, addedTask.getId(), "id");
    }

    @Test
    void removeTaskIdTest() {
        Task task = new Task("Один", "One");//Должен быть пустым после вызова метода
        taskManager.addTask(task);
        taskManager.removeTask(task.getId());

        List<Task> tasks = taskManager.getTasks();
        assertEquals(0, tasks.size());
    }

    @Test
    void removeEpicIdTest() {
        Epic epic = new Epic("Один Эпик", "one epic");
        taskManager.addEpic(epic);

        SubTask subtask = new SubTask("Один сабтаск", "one subtask", epic.getId());
        taskManager.addSubTask(subtask);

        taskManager.removeEpic(epic.getId());

        List<Epic> epics = taskManager.getEpics();
        assertEquals(0, epics.size());
        assertEquals(0, taskManager.getSubTasks().size());
    }

    @Test
    void removeSubTaskIdTest() {
        Epic epic = new Epic("Один Эпик", "one epic");
        taskManager.addEpic(epic);

        SubTask subtask = new SubTask("Один сабтаск", "one subtask", epic.getId());
        taskManager.addSubTask(subtask);

        taskManager.clearSubTask();

        List<SubTask> subtasks = taskManager.getSubTasks();
        assertEquals(0, subtasks.size());
        assertEquals(0, epic.getSubTaskIds().size());
    }
}