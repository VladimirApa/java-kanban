package yandex.app;

import yandex.app.manager.*;
import yandex.app.model.*;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        System.out.println("Делай делай делай!");
        System.out.println("А когда не делали?");
        printAllTasks(taskManager);
        Task t1 = new Task("Начать ремонт", "Развестись с женой");
        Task t2 = new Task("Найти бригадира", "Довести бригадира");
        taskManager.addTask(t1);
        taskManager.addTask(t2);
        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }
        // ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ

        taskManager.addEpic(new Epic("Концерт", "Научится кидать артистов на бабки."));

        taskManager.addSubTask(new SubTask("Найти актеров", "Кинуть актеров", 3));
        taskManager.addSubTask(new SubTask("Собрать деньги", "Забрать все себе", 4));

        for (Epic epic : taskManager.getEpics()) {
            System.out.println("\n\n" + epic);
        }
        for (SubTask subTask : taskManager.getSubTasks()) {
            System.out.println(subTask);
        }
        // ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ ОБНОВЛЕНИЕ ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ
        taskManager.addSubTask(new SubTask("Продавать", "Кукурузу", 5));
        System.out.println();
        System.out.println("Смотрим обновились ли наши идеи: ");
        for (SubTask subTask : taskManager.getSubTasks()) {
            System.out.println(subTask);
        }
        System.out.println();

        // ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ УДАЛИТЬ САБТАКС ඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞඞ

        taskManager.removeSubTask(4);
        System.out.println();
        System.out.println("Оставшиеся задачи ");
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

    private static void printAllTasks(TaskManager manager) {
        manager.addTask(new Task("Почитать манхву ", "Solo Leveling"));
        manager.addTask(new Task("Почитать манхву ", "Всеведуйщий читатель"));
        manager.addEpic(new Epic("Работа", "Опять работать?"));
        manager.addSubTask(new SubTask("Посмотреть аниме: ", "Убийца гоблинов", 2));
        manager.addSubTask(new SubTask("Посмотреть аниме: ", "Код Гиас", 2));
        manager.addEpic(new Epic("Заниматься спортом", "Чтобы тело не затекало :D"));
        manager.addSubTask(new SubTask("Посмотреть аниме: ", "КРД", 3));
        manager.addSubTask(new SubTask("Посмотреть аниме: ", "Что то новое?", 3));


        System.out.println("Задачи: ");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики: ");
        for (Task epic : manager.getEpics()) {
            System.out.println(epic);

            for (Task task : manager.getEpicSab(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи: ");
        for (Task subtask : manager.getSubTasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}

