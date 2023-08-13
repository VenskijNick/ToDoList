package com.tms.todo.validation;

import models.Task;

import java.util.ArrayList;

import static java.lang.System.*;

public class Validator {
    public static boolean validateName (String name, ArrayList<Task> arrayList) {
        if (name.length() > 25) {
            out.println("Invalid name");
            return false;
        } else {

            return true;
        }
    }
    public static boolean validateDescription (String description) {
        if (description.length() < 100) {
            return true;
        } else {
            out.println("Invalid description");
            return false;
        }
    }
    public static boolean validateId (int id, ArrayList<Task> toDoList) {
        if (toDoList.stream().noneMatch(task -> task.getId() == id)) {
            out.println("This number does not exist.");
            return false;
        } else {
            return true;
        }
    }
}
