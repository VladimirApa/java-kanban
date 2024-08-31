package server;

import Model.Epic;
import Model.Status;
import Model.SubTask;
import Model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, SubTask> taskSub;
    private final HashMap<Integer, Epic> tasksEpic;
    int id = 1;

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
        /*if (epic.getSabTaskIds().isEmpty()) {
            epic.setStat(Status.NEW);
            return;
        }*/
        int newBiba = 0; // Новый
        int doneBoba = 0; // Уже повидавший
        ArrayList<SubTask> sabList = new ArrayList<>();
        for (Integer id : epic.getSabTaskIds()) {
            sabList.add(taskSub.get(id));
        }
        for (SubTask subTask : sabList) {
            if (subTask.getStat() == Status.NEW) {
                newBiba++;
            } else if (subTask.getStat() == Status.DONE) {
                doneBoba++;
            }
        }
        if (taskSub.isEmpty() || newBiba == taskSub.size()) {
            epic.setStat(Status.NEW);
        } else if (doneBoba == taskSub.size()) {
            epic.setStat(Status.DONE);
        } else {
            epic.setStat(Status.IN_PROGRESS);
        }
    }

    public Object allSearchID(int id) { // ОБЩИЙ МЕТОД ПО ПОИСКУ ID
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

    public Task searchTask(int id) {// Поиск задачи по айди в Model.Task
        return tasks.get(id);
    }

    public SubTask searchSub(int id) {// Поиск задачи по айди в Model.Task
        return taskSub.get(id);
    }

    public Epic searchEpic(int id) {// Поиск задачи по айди в Model.Task
        return tasksEpic.get(id);
    }

    public boolean removeTask(int id) { // удалить по айди Model.Task
        Task task = tasks.remove(id);
        if (task != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removeSubTask(int id) { // удалить по айди Model.SubTask
        SubTask subTask = taskSub.remove(id);
        if (subTask != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean removeEpic(int id) { // удалить по айди Model.Epic
        Epic epic = tasksEpic.remove(id);
        if (epic != null) {
            return true;
        } else {
            return false;
        }
    }

    public HashMap<Integer, Task> getTasks() { //Для вывода
        return tasks;
    }

    public HashMap<Integer, Epic> getEpics() { //Для вывода
        return tasksEpic;
    }

    public HashMap<Integer, SubTask> getSubTasks() { //Для вывода
        return taskSub;
    }

    public void clearTask() { //Удалить таск
        tasks.clear();
    }

    public void clearSubTask() { //Удалить сабтакски
        taskSub.clear();
        for (Epic epic : tasksEpic.values()) {
            epic.getSabTaskIds().clear();
            updateTask(epic);
        }
    }

    public void clearEpic() { // Удалить епики + сабтакски
        tasksEpic.clear();
        taskSub.clear();
    }
}