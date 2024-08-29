import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TaskManager {

    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, SubTask> taskSub = new HashMap<>();
    private HashMap<Integer, Epic> tasksEpic = new HashMap<>();
    static int id = 1;


    public void addTask(Task task) { // добавить задачи
        task.setId(id++);
        tasks.put(task.getId(), task);
    }

    public void addSubtask(SubTask subtask) {
        subtask.setId(id++);
        taskSub.put(subtask.getId(), subtask);
    }

    public void addEpic(Epic epic) {
        epic.setId(id++);
        tasksEpic.put(epic.getId(), epic);
    }

    public Object search(int id, Object obj) { //ЭКСПЕРЕМЕНТ ЧТОБЫ СВЯЗТЬ 3 Класса
        if (obj instanceof Task) {
            Task task = (Task) obj;
            return search1(id, task);
        } else if (obj instanceof SubTask) {
            SubTask subtask = (SubTask) obj;
            return search2(id, subtask);
        } else if (obj instanceof Epic) {
            Epic epic = (Epic) obj;
            return search3(id, epic);
        }
        return null;
    }
    public static Object search22(int id) { // ОБЩИЙ МЕТОД ПО ПОИСКУ ID

       Objects task1 = search33(id);
        SubTask subtask = SubTask.search(id);
        Epic epic = Epic.search(id);

        if (task1 != null) {
            return task;
        } else if (subtask != null) {
            return subtask;
        } else if (epic != null) {
            return epic;
        }
        return null;
    }

    public Task search1(int idToSearch, Task task) {// Поиск задачи по айди в Task
        for (Integer tasks : tasks.keySet()) {
            if (task.getId() == idToSearch) {
                return task;
            }
        }
        return null;
    }

    public SubTask search2(int idToSearch, SubTask subTask) { // Поиск задачи по айди в SubTask
        for (Integer subtask : taskSub.keySet()) {
            if (subTask.getId() == idToSearch) {
                return subTask;
            }
        }
        return null;
    }

    public Epic search3(int idToSearch, Epic epic) { // Поиск задачи по айди в Epic
        for (Integer subtask : tasksEpic.keySet()) {
            if (epic.getId() == idToSearch) {
                return epic;
            }
        }
        return null;
    }

    /*public Task search33(int idToSearch) {
        return tasks.get(idToSearch);
    }*/

    public boolean removeTask(int id) { // удалить по айди
        Task task = tasks.remove(id);
        if (task != null) {
            return true;
        } else {
            return false;
        }
    }

    public void printTasks() { // Вывести все задачи
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            System.out.printf("%d - %s%n", entry.getKey(), entry.getValue());
        }
    }
    public void printTaskSub() { // Вывести все задачи
        for (Map.Entry<Integer, SubTask> entry : taskSub.entrySet()) {
            System.out.printf("%d - %s%n", entry.getKey(), entry.getValue());
        }
    }
    public void printEpic() { // Вывести все задачи
        for (Map.Entry<Integer, Epic> entry : tasksEpic.entrySet()) {
            System.out.printf("%d - %s%n", entry.getKey(), entry.getValue());
        }
    }

    public void clearTask() {
        tasks.clear();
    }

    public void clearSubTask() {
        taskSub.clear();
    }

    public void clearEpic() {
        tasksEpic.clear();
        taskSub.clear();
    }
}
