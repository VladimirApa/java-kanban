package yandex.app.manager;

import yandex.app.model.Epic;
import yandex.app.model.TaskStatus;
import yandex.app.model.SubTask;
import yandex.app.model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, SubTask> taskSub;
    private final HashMap<Integer, Epic> tasksEpic;
    private int id = 1;


    public TaskManager() {
        tasks = new HashMap<>();
        taskSub = new HashMap<>();
        tasksEpic = new HashMap<>();
    }

    public Task addTask(Task task) { // добавить задачи
        task.setId(id++);
        tasks.put(task.getId(), task);
        return task;
    }

    public SubTask addSubTask(SubTask subtask) {
        subtask.setId(id++);
        taskSub.put(subtask.getId(), subtask);
        Epic epicTask = tasksEpic.get(subtask.getEpicId());
        epicTask.addNewSubTask(subtask.getId());
        calculateEpicStatus(tasksEpic.get(subtask.getEpicId()));
        return subtask;
    }

    public Epic addEpic(Epic epic) {
        epic.setId(id++);
        tasksEpic.put(epic.getId(), epic);
        return epic;
    }

    public void updateTask(Task task) { // Обновить задачу Таск
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) { //Обновить задачу эпик
        Epic saved = tasksEpic.get(epic.getId());
        if (saved == null) {
            return;
        }
        saved.setName(epic.getName());
        saved.setDescription(epic.getName());
    }

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

    public Task searchTask(int id) {// Поиск задачи по айди в yandex.app.Model.Task
        return tasks.get(id);
    }

    public SubTask searchSub(int id) {// Поиск задачи по айди в yandex.app.Model.Task
        return taskSub.get(id);
    }

    public Epic searchEpic(int id) {// Поиск задачи по айди в yandex.app.Model.Task
        return tasksEpic.get(id);
    }

    public boolean removeTask(int id) { // удалить по айди yandex.app.Model.Task
        Task task = tasks.remove(id);
        if (task != null) {
            return true;
        } else {
            return false;
        }
    }

    public void removeSubTask(int id) { // удалить по айди yandex.app.Model.SubTask
        final SubTask subTask = taskSub.remove(id);
        if (subTask == null) {
            return;
        }
        final Epic epicTask = tasksEpic.get(subTask.getEpicId());
        epicTask.removeSubTaskIds(id);
        updateEpic(epicTask);
    }

    public void removeEpic(int id) { // удалить по айди yandex.app.Model.Epic
        final Epic epic = tasksEpic.remove(id);
        for (Integer subtaskId : epic.getSubTaskIds()) {
            taskSub.remove(subtaskId);
        }
    }

    public ArrayList<Task> getTasks() { //Для вывода
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getEpics() { //Для вывода
        return new ArrayList<>(tasksEpic.values());
    }

    public ArrayList<SubTask> getSubTasks() { //Для вывода
        return new ArrayList<>(taskSub.values());
    }

    public void clearTask() { //full clear Task
        tasks.clear();
    }

    public void clearSubTask() { //Удалить сабтакски
        taskSub.clear();
        for (Epic epic : tasksEpic.values()) {
            epic.getSubTaskIds().clear();
            calculateEpicStatus(epic);
        }
    }

    public void clearEpic() { // Удалить епики + сабтакски
        tasksEpic.clear();
        taskSub.clear();
    }
}