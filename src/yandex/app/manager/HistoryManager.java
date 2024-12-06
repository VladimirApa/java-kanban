package yandex.app.manager;

import yandex.app.model.*;

import java.util.List;


public interface HistoryManager {

    List<Task> getHistory();

    void addTaskHistory(Task task);


}
