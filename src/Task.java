import java.util.HashMap;
import java.util.Map;

public class Task {
    private String name;
    private String title;
    private Status stat;
    private int id;

    public Task(String name, int id,  Status stat, String title) {
        this.name = name;
        this.id = id;
        this.stat = stat;
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


}
