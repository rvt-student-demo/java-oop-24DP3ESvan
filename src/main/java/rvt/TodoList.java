package rvt;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;

public class TodoList {

    private ArrayList<String> tasks;
    private final String filePath = "todo.csv";

    public TodoList() {
        this.tasks = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    tasks.add(parts[0].trim() + "," + parts[1].trim());
                }
            }

        } catch (IOException e) {
            System.out.println("File not found, new file will be created.");
        }
    }

    private int getLastId() {
        if (tasks.isEmpty()) {
            return 0;
        }

        String last = tasks.get(tasks.size() - 1);
        String[] parts = last.split(",", 2);
        return Integer.parseInt(parts[0]);
    }

    public void add(String task) {

        if (!checkEventString(task)) {
            System.out.println("Invalid task (min 3 symbols, letters only)");
            return;
        }

        int newId = getLastId() + 1;
        tasks.add(newId + "," + task);
        updateFile();
    }

    public void print() {
        for (String task : tasks) {
            String[] parts = task.split(",", 2);
            System.out.println(parts[0] + ": " + parts[1]);
        }
    }

    public void remove(int id) {

        Iterator<String> iterator = tasks.iterator();

        while (iterator.hasNext()) {
            String task = iterator.next();
            String[] parts = task.split(",", 2);

            if (Integer.parseInt(parts[0]) == id) {
                iterator.remove();
                updateFile();
                return;
            }
        }

        System.out.println("Task not found.");
    }

    private boolean updateFile() {

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {

            writer.write("ID, Task");
            writer.newLine();

            for (String task : tasks) {
                writer.write(task.replace(",", ", "));
                writer.newLine();
            }

            return true;

        } catch (IOException e) {
            System.out.println("Error updating file.");
            return false;
        }
    }

    public boolean checkEventString(String value) {
        return value != null &&
                Pattern.matches("^[a-zA-Z 0-9]{3,}$", value);
    }
}
