package yandex.app.model;

import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private TaskStatus status;
    private int id;


    public Task(String name, String description) {// общий конструктор
        this.name = name;
        this.description = description;
        this.status = TaskStatus.NEW;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStat() {
        return status;
    }

    public void setStat(TaskStatus status) {
        this.status = status;
    }

    public int getId() { //id
        return id;
    }

    public void setId(int id) { // id
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", title='" + description + '\'' +
                ", stat=" + status +
                ", id=" + id +
                '}';
    }
}