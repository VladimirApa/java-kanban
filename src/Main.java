public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager.addTask(new Task("Задача 1", 1,Status.NEW,"fff"));
        //TaskManager.addTask(new Task("Задача 2", 2));
        TaskManager.printTasks();
        TaskManager.removeTask(1);
        TaskManager.printTasks();
    }
}
