package yandex.app.manager;

import yandex.app.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int numberTask = 10;
    private final List<Task> history = new ArrayList<>();

    @Override
    public List<Task> getHistory() {
        return history;
    }

    @Override
    public void addTaskHistory(Task task) { //последние 10 задач
        while (history.size() == numberTask) {
            history.removeFirst();
        }
        history.add(task);
    }

}

