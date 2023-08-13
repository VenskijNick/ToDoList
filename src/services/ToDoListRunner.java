package services;

import models.InfoPrinter;
import models.Task;
import models.ToDoList;

public class ToDoListRunner {
    public static void main(String[] args) {
        var list = new ToDoList();
        InfoPrinter.menu();
      //  InfoPrinter.start(list);
        var task1 = new Task("123", "IDK");
        var task2 = new Task("567", "IND");
        list.addTask(task1);
        list.addTask(task2);
        list.printShortInfo();
        list.searchTaskByName("123 ");
        list.editTasksName(1, "hello");
        list.printShortInfo();


    }
}
