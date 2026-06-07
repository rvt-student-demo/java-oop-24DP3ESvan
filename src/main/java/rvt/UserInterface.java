package rvt;

import java.util.Scanner;

public class UserInterface {

    private TodoList todoList;
    private Scanner scanner;

    public UserInterface(TodoList todoList, Scanner scanner) {
        this.todoList = todoList;
        this.scanner = scanner;
    }

    public void start() {

        System.out.println("List Application");

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "1":
                case "add":
                    addTask();
                    break;

                case "2":
                case "list":
                    todoList.print();
                    break;

                case "3":
                case "remove":
                    removeTask();
                    break;

                case "0":
                case "exit":
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Unknown command.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("Choose command:");
        System.out.println("1 - Add task");
        System.out.println("2 - Show tasks");
        System.out.println("3 - Remove task");
        System.out.println("0 - Exit");
        System.out.print(">> ");
    }

    private void addTask() {
        System.out.print("Enter task description: ");
        String task = scanner.nextLine();
        todoList.add(task);
    }

    private void removeTask() {
        System.out.print("Enter task ID to remove: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());
            todoList.remove(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }
}
