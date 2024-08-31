import Model.Epic;
import Model.Status;
import Model.SubTask;
import Model.Task;
import server.TaskManager;

import static Model.Status.IN_PROGRESS;
import static Model.Status.NEW;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        System.out.println("Делай делай делай!");
        System.out.println("А когда не делали!");

        Task t1 = new Task("Начать ремонт", NEW, "Развестись с женой");
        Task t2 = new Task("Найти бригадира", NEW, "Довести бригадира");
        taskManager.addTask(t1);
        taskManager.addTask(t2);
        for (Task task : taskManager.getTasks().values()) {
            System.out.println(task);
        }
        // ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ

        Epic e11 = taskManager.addEpic(new Epic("Концерт", NEW, "Научится кидать артистов на бабки."));

        SubTask s1 = taskManager.addSubTask(new SubTask("Найти актеров", NEW, "Кинуть актеров", e11.getId()));
        SubTask s2 = taskManager.addSubTask(new SubTask("Собрать деньги", NEW, "Забрать все себе", e11.getId()));
        e11.addNewSubTask(s1.getId());
        e11.addNewSubTask(s2.getId());

        for (Epic epic : taskManager.getEpics().values()) {
            System.out.println("\n\n" + epic);
        }
        for (SubTask subTask : taskManager.getSubTasks().values()) {
            System.out.println(subTask);
        }
        // ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ ОБНОВЛЕНИЕ ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ

        SubTask s500 = taskManager.addSubTask(new SubTask("Продавать", IN_PROGRESS, "Кукурузу", e11.getId()));
        System.out.println();
        System.out.println("Смотрим обновились ли наши идеи: ");
        for (SubTask subTask : taskManager.getSubTasks().values()) {
            System.out.println(subTask);
        }
        System.out.println();

        // ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ УДАЛИТЬ САБТАКС ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ

        taskManager.removeSubTask(6);
        System.out.println();
        System.out.println("Должны остатся 2 задачи ");
        for (SubTask subTask : taskManager.getSubTasks().values()) {
            System.out.println(subTask);
        }
        // ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ УДАЛИТЬ САБТАКС ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ

        System.out.println();
        System.out.println("вывод обновленных епиков - " + taskManager.getEpics());

        System.out.println();
        System.out.println("вывод обновленных сабтасков - " + taskManager.getSubTasks());

        // ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ общий метод по поиску ID ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ

        System.out.println();
        System.out.println("поиск по айди: " + taskManager.allSearchID(2));
        System.out.println();

        // ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ Проверка удаления ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ

        taskManager.clearTask();
        taskManager.clearEpic();
        System.out.println("Task говорит - " + taskManager.getTasks());
        System.out.println("Epic говорит - " + taskManager.getEpics());
        System.out.println("СабТаск говорит - " + taskManager.getSubTasks());
        // ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ Проверка удаления ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ


    }
}

