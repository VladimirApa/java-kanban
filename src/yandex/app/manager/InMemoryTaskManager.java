package yandex.app.manager;

import yandex.app.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, SubTask> taskSub;
    private final HashMap<Integer, Epic> tasksEpic;
    private final HistoryManager historyManager = Managers.getDefaultHistory();
    private int id = 1;


    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        taskSub = new HashMap<>();
        tasksEpic = new HashMap<>();
    }

    @Override
    public void addTask(Task task) { // добавить задачи
        task.setId(id++);
        tasks.put(task.getId(), task);
    }

    @Override
    public void addSubTask(SubTask subtask) {
        if (!tasksEpic.containsKey(subtask.getEpicId())) {
            return;
        }
        Epic epicTask = tasksEpic.get(subtask.getEpicId());
        subtask.setId(id++);
        taskSub.put(subtask.getId(), subtask);
        epicTask.getSubTaskIds().add(subtask.getId());
        calculateEpicStatus(epicTask);
    }

    @Override
    public void addEpic(Epic epic) {
        epic.setId(id++);
        tasksEpic.put(epic.getId(), epic);

    }

    @Override
    public void updateTask(Task task) { // Обновить задачу Таск
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateEpic(Epic epic) { //Обновить задачу эпик
        Epic saved = tasksEpic.get(epic.getId());
        if (saved == null) {
            return;
        }
        saved.setName(epic.getName());
        saved.setDescription(epic.getName());
    }

    @Override
    public void updateSubTask(SubTask subTask) { // Обновить задачу Саб таск
        Epic epic = tasksEpic.get(subTask.getEpicId());
        if (epic == null) {
            return;
        }
        taskSub.put(subTask.getId(), subTask);
        calculateEpicStatus(epic);
    }

    private void calculateEpicStatus(Epic epic) { //Обновление статуса
        int newBiba = 0; // Новый
        int doneBoba = 0; // Уже повидавший
        ArrayList<SubTask> sabList = new ArrayList<>();
        for (Integer id : epic.getSubTaskIds()) {
            sabList.add(taskSub.get(id));
        }
        for (SubTask subTask : sabList) {
            if (subTask.getStatus() == TaskStatus.NEW) {
                newBiba++;
            } else if (subTask.getStatus() == TaskStatus.DONE) {
                doneBoba++;
            }
        }
        if (taskSub.isEmpty() || newBiba == taskSub.size()) {
            epic.setStatus(TaskStatus.NEW);
        } else if (doneBoba == taskSub.size()) {
            epic.setStatus(TaskStatus.DONE);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    @Override
    public Task allSearchID(int id) { // ОБЩИЙ МЕТОД ПО ПОИСКУ ID
        Task result = tasks.get(id);
        SubTask resultSub = taskSub.get(id);
        Epic resultEpic = tasksEpic.get(id);
        if (result != null) {
            return result;
        } else if (resultSub != null) {
            return resultSub;
        } else if (resultEpic != null) {
            return resultEpic;
        }
        return null;
    }

    @Override
    public Task getTaskById(int id) {// Поиск задачи по айди в yandex.app.Model.Task
        Task task = tasks.get(id);
        historyManager.addTaskHistory(task);
        return tasks.get(id) != null ? tasks.get(id) : null;

    }

    @Override
    public SubTask getSubTaskById(int id) {// Поиск задачи по айди в yandex.app.Model.Task
        SubTask subTask = taskSub.get(id);
        historyManager.addTaskHistory(subTask);
        return taskSub.get(id) != null ? taskSub.get(id) : null;
    }

    @Override
    public Epic getEpicById(int id) {// Поиск задачи по айди в yandex.app.Model.Task
        Epic epic = tasksEpic.get(id);
        historyManager.addTaskHistory(epic);
        return tasksEpic.get(id) != null ? tasksEpic.get(id) : null;
    }

    @Override
    public boolean removeTask(int id) { // удалить по айди yandex.app.Model.Task
        Task task = tasks.remove(id);
        if (task != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void removeSubTask(int id) { // удалить по айди yandex.app.Model.SubTask
        final SubTask subTask = taskSub.remove(id);
        if (subTask == null) {
            return;
        }
        final Epic epicTask = tasksEpic.get(subTask.getEpicId());
        epicTask.removeSubTaskIds(id);
        updateEpic(epicTask);
    }

    @Override
    public void removeEpic(int id) { // удалить по айди yandex.app.Model.Epic
        final Epic epic = tasksEpic.remove(id);
        for (Integer subtaskId : epic.getSubTaskIds()) {
            taskSub.remove(subtaskId);
        }
    }

    @Override
    public ArrayList<Task> getTasks() { //Для вывода
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getEpics() { //Для вывода
        return new ArrayList<>(tasksEpic.values());
    }

    @Override
    public ArrayList<SubTask> getSubTasks() { //Для вывода
        return new ArrayList<>(taskSub.values());
    }


    @Override
    public void clearTask() { //full clear Task
        tasks.clear();
    }

    @Override
    public void clearSubTask() { //Удалить сабтакски
        taskSub.clear();
        for (Epic epic : tasksEpic.values()) {
            epic.getSubTaskIds().clear();
            calculateEpicStatus(epic);
        }
    }

    @Override
    public void clearEpic() { // Удалить епики + сабтакски
        tasksEpic.clear();
        taskSub.clear();
    }

    @Override
    public ArrayList<SubTask> getEpicSab(int epicId) {
        ArrayList<SubTask> sabList = new ArrayList<>();
        Epic epics = tasksEpic.get(epicId);
        if (epics != null) {
            for (int sabId : epics.getSubTaskIds()) {
                SubTask subTask = taskSub.get(sabId);
                sabList.add(subTask);
            }
        }
        return sabList;
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

}
