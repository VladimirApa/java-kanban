package yandex.app.manager;

import yandex.app.model.Epic;
import yandex.app.model.SubTask;
import yandex.app.model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    void addTask(Task task);

    void addSubTask(SubTask subtask);

    void addEpic(Epic epic);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubTask(SubTask subTask);

    void calculateEpicStatus(Epic epic);

    Task allSearchID(int id);

    Task searchTask(int id);

    SubTask searchSub(int id);

    Epic searchEpic(int id);

    boolean removeTask(int id);

    void removeSubTask(int id);

    void removeEpic(int id);

    ArrayList<Task> getTasks();

    ArrayList<Epic> getEpics();

    ArrayList<SubTask> getSubTasks();

    ArrayList<SubTask> getEpicSab(int epicId);

    List<Task> getHistory();

    void clearTask();

    void clearSubTask();

    void clearEpic();
}
