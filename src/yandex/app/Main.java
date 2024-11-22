package yandex.app;

import yandex.app.model.Epic;
import yandex.app.model.SubTask;
import yandex.app.model.Task;
import yandex.app.manager.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        System.out.println("Делай делай делай!");
        System.out.println("А когда не делали?");

        Task t1 = new Task("Начать ремонт", "Развестись с женой");
        Task t2 = new Task("Найти бригадира", "Довести бригадира");
        taskManager.addTask(t1);
        taskManager.addTask(t2);
        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }
        // ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ

        Epic e11 = taskManager.addEpic(new Epic("Концерт", "Научится кидать артистов на бабки."));

        SubTask s1 = taskManager.addSubTask(new SubTask("Найти актеров", "Кинуть актеров", e11.getId()));
        SubTask s2 = taskManager.addSubTask(new SubTask("Собрать деньги", "Забрать все себе", e11.getId()));
        e11.addNewSubTask(s1.getId());
        e11.addNewSubTask(s2.getId());

        for (Epic epic : taskManager.getEpics()) {
            System.out.println("\n\n" + epic);
        }
        for (SubTask subTask : taskManager.getSubTasks()) {
            System.out.println(subTask);
        }
        // ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ ОБНОВЛЕНИЕ ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ

        SubTask s500 = taskManager.addSubTask(new SubTask("Продавать", "Кукурузу", e11.getId()));
        System.out.println();
        System.out.println("Смотрим обновились ли наши идеи: ");
        for (SubTask subTask : taskManager.getSubTasks()) {
            System.out.println(subTask);
        }
        System.out.println();

        // ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ УДАЛИТЬ САБТАКС ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ

        taskManager.removeSubTask(4);
        System.out.println();
        System.out.println("Должны остатся 2 задачи ");
        for (SubTask subTask : taskManager.getSubTasks()) {
            System.out.println(subTask);
        }
        // ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ УДАЛИТЬ САБТАКС ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ

        System.out.println();
        System.out.println("вывод обновленных епиков - ");
        for (Epic epic : taskManager.getEpics()) {
            System.out.println(epic);
        }
        System.out.println();
        System.out.println("вывод обновленных сабтасков - ");
        for (SubTask subTask : taskManager.getSubTasks()) {
            System.out.println(subTask);
        }
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

