package models;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.lang.System.*;

public class InfoPrinter {

    public static void menu(){
        out.println("Enter the option: ");
        out.println("""
                1) Create a new task.
                2) Edit the task.
                3) Cancel the task.
                4) Finish the task.
                5) Delete the task.
                6) Show description of the task.
                7) Show all active tasks.
                8) Filter tasks by status.
                9) Find tasks by name.
                10) Show statistics.
                11) Exit the program. \s
                """);
    }
    public static void start(ToDoList list) throws IOException {
        while (list.appRunning){

        var name ="";
        var description ="";
        Scanner scanner = new Scanner(in);
        switch (scanner.nextInt()){
            case 1 -> {
                out.println("Has your task description? (Y/N?)");
                switch (scanner.nextLine().toUpperCase()){
                    case "Y" -> {
                        out.println("Enter name:");
                        name = scanner.nextLine();
                        out.println("Enter description: ");
                        description = scanner.nextLine();
                        list.addTask(new Task(name,description));
                    }
                    case "N" ->{name = scanner.nextLine();
                            list.addTask(new Task(name,description));}
                    default -> out.println("Invalid input");
                }
            }
            case 2 ->{
                out.println("""
                        What do you want to edit?
                        1) Description
                        2) Name""");
                switch (scanner.nextInt()){
                    case 1 -> {
                        out.println("Enter task's id: ");
                        var id = scanner.nextInt();
                        out.println("Enter new description: ");
                        description =scanner.nextLine();
                        list.editTaskDescription(id, description);
                    }
                    case 2 -> {
                        out.println("Enter task's id: ");
                        var id = scanner.nextInt();
                        out.println("Enter new name");
                        name = scanner.nextLine();
                        list.editTasksName(id, name);
                    }
                    default -> out.println("Invalid input");

                }

                }
            case 3 -> {
                out.println("Enter the id of canceling task");
                list.cancelTask(scanner.nextInt());
            }
            case 4 -> {
                out.println("Enter the id of finishing task");
                list.finishTask(scanner.nextInt());
            }
            case 5 -> {
                out.println("Enter the id of deleting task");
                list.deleteTask(scanner.nextInt());
            }
            case 6 -> {
                list.printShortInfo();
            }
            case 7 ->{
                list.showAllActiveTasks();
            }
            case 8 -> {
                ExecutionStatus status = null;
                switch (scanner.nextLine().toUpperCase()){
                    case "ACTIVE" -> status = ExecutionStatus.ACTIVE;
                    case "CANCELED" -> status = ExecutionStatus.CANCELED;
                    case "FINISHED" -> status = ExecutionStatus.FINISHED;
                    default -> {
                        out.println("Invalid input");
                    }
                }
                list.filterByStatus(status);
            }
            case 9 ->{
                list.searchTaskByName(scanner.nextLine());
            }
            case 10 -> {
                out.println("Enter the time");
                list.filterByFinishing(LocalDateTime.parse(scanner.nextLine(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            }
            case 11 ->{
                list.appRunning = false;
            }}}



        }
    }



