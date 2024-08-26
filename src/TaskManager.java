import java.util.HashMap;
import java.util.Map;

public class TaskManager {

    static HashMap<Integer, Task> tasks = new HashMap<>();





    public static void addTask(Task task) { // добавить задачи
        tasks.put(task.getId(), task);
    }

    public static boolean removeTask(int id) { // удалить все задачи
        Task task = tasks.remove(id);
        if (task != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void printTasks() { // Вывести все задачи
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            System.out.printf("%d - %s%n", entry.getKey(), entry.getValue().getName());
        }
    }

}
