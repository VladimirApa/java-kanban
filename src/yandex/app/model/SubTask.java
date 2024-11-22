package yandex.app.model;

public class SubTask extends yandex.app.model.Task {
    private final int epicId;

    public SubTask(String name, String description, int epicId) {
        super(name, description);
        this.epicId = epicId;
    }

    public int getEpicId() { // id
        return epicId;
    }


}