package yandex.app.Model;

public class SubTask extends Task {
    private final int epicId;

    public SubTask(String name, Status status, String description, int epicId) {
        super(name, status, description);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }


}