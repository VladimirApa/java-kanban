import java.util.HashMap;
import java.util.Map;

public class Task {
    private String name;
    private String title;
    private Status stat;
    private int id;


    public Task(String name, int id,  Status stat, String title) {// общий конструктор
        this.name = name;
        this.id = id;
        this.stat = stat;
        this.title = title;
    }

    protected Task(String name, String title) { // конструктор для Subtask/Epic
        this.name = name;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Status getStat() {
        return stat;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", stat=" + stat +
                ", id=" + id +
                '}';
    }
}
