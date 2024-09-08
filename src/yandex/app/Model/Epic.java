package yandex.app.Model;

import java.util.ArrayList;

public class Epic extends Task {

    ArrayList<Integer> sabTaskIds = new ArrayList<>();

    public Epic(String name, Status status, String description) {
        super(name, status, description);
    }

    public ArrayList<Integer> getSabTaskIds() {
        return sabTaskIds;
    }

    public void addNewSubTask(int subTask) {
        sabTaskIds.add(subTask);
    }

    public void removeallTast() {
        sabTaskIds.clear();
    }

    @Override
    public String toString() {
        return "Epic{" +
                "sabTaskIds=" + sabTaskIds +
                '}';
    }


}