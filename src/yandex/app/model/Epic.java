package yandex.app.model;

import java.util.ArrayList;

public class Epic extends yandex.app.model.Task {

    private final ArrayList<Integer> subTaskIds = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public ArrayList<Integer> getSubTaskIds() {
        return subTaskIds;
    }

    public void addNewSubTask(int subTask) {
        subTaskIds.add(subTask);
    }

    public void clearSubTasks() { //full clear sub
        subTaskIds.clear();
    }

    public void removeSubTaskIds(int id) {
        subTaskIds.remove(id);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "sabTaskIds=" + subTaskIds +
                '}';
    }


}