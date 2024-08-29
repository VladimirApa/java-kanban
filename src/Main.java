public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        System.out.println("Поехали!");
        taskManager.addSubtask(new SubTask("1","Pety"));
        taskManager.addSubtask(new SubTask("2","LOl"));
        taskManager.addSubtask(new SubTask("3","D21"));
        taskManager.addSubtask(new SubTask("4","dsada1111"));
        taskManager.addSubtask(new SubTask("5","2332"));
        taskManager.addSubtask(new SubTask("6","123asdas"));
        taskManager.printTaskSub();
        System.out.println();
        System.out.println();
      taskManager.search(1,"");

    }
}
