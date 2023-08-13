package models;

import com.tms.todo.validation.Validator;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

public final class ToDoList {
    private ArrayList<Task> arrayList = new ArrayList<>();
    public boolean appRunning = true;
    private File file = new File(System.getProperty("user.home")+"\\IdeaProjects\\ToDoListTMS\\src\\com\\tms\\todo\\list\\resources", "tasks.txt");

    private void addTask(Task task){
        arrayList.add(task);
    }
    public ToDoList(){
        createFile();
    }
    public void createFile() {
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deserializingListOfTasksFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            arrayList = (ArrayList<Task>) objectInputStream.readObject();
            Optional<Task> taskWithMaxTaskId = arrayList.stream()
                    .max(Comparator.comparingInt(Task::getId));
            Task.counter = taskWithMaxTaskId.get().getId();;
            System.out.println(arrayList);
        } catch (FileNotFoundException e) {
            System.out.println("We can't find input file :(");
        } catch (IOException e) {
            System.out.println("We has some problems with file access :(");
        } catch (ClassNotFoundException e) {
            System.out.println("We has problems with deserialization :(");
        }
    }


    public void serializingListOfTasksFromFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(arrayList);
        } catch (FileNotFoundException e) {
            System.out.println("Can't find input file :");
        } catch (IOException e) {
            System.out.println("Has some problems with file access :(");
        }
    }


    public void editTasksName(int id, String newName) throws IOException {
        if (Validator.validateId(id, arrayList)) {
            for (Task task : arrayList) {
                if (task.getId() == id) {
                    task.setName(newName);
                    task.setEditTime(LocalDateTime.now());
                    FileWriter writer = new FileWriter("tasks.txt", true);
                    writer.write("Task " + task.getId() + " name changed to " + task.getName() + " at " + task.getEditTime() + "\n");
                    writer.close();
                }
            }
        }
    }

    public void cancelTask(int id) throws IOException {
        if (Validator.validateId(id, arrayList)) {
            for (Task task : arrayList) {
                if (task.getId() == id) {
                    task.setStatus(ExecutionStatus.CANCELED);
                    task.setEditTime(LocalDateTime.now());
                    FileWriter writer = new FileWriter("tasks.txt", true);
                    writer.write("Task " + task.getId() + " canceled at " + task.getEditTime() + "\n");
                    writer.close();
                }
            }
        }
    }

    public void finishTask(int id) throws IOException {
        if (Validator.validateId(id, arrayList)) {
            for (Task task : arrayList) {
                if (task.getId() == id) {
                    task.setStatus(ExecutionStatus.FINISHED);
                    task.setEndTime(LocalDateTime.now());
                    FileWriter writer = new FileWriter("tasks.txt", true);
                    writer.write("Task " + task.getId() + " finished at " + task.getEndTime() + "\n");
                    writer.close();
                }
            }
        }
    }

    public void editTaskDescription(int id, String newDescription) throws IOException {
        if (Validator.validateId(id, arrayList)) {
            for (Task task : arrayList) {
                if (task.getId() == id) {
                    task.setDescription(newDescription);
                    task.setEditTime(LocalDateTime.now());
                    FileWriter writer = new FileWriter("tasks.txt", true);
                    writer.write("Task " + task.getId() + " description changed to " + task.getDescription() + " at " + task.getEditTime() + "\n");
                    writer.close();
                }
            }
        }
    }

    public void deleteTask(int id) throws IOException {
        if (Validator.validateId(id, arrayList)) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (arrayList.get(i).getId() == id) {
                    Task task = arrayList.remove(i);
                    FileWriter writer = new FileWriter("tasks.txt", true);
                    writer.write("Task " + task.getId() + " deleted at " + LocalDateTime.now() + "\n");
                    writer.close();
                    break;
                }
            }
        }
    }

        public void printShortInfo(){
        arrayList.forEach(task -> System.out.println("Id: "+task.getId()+" Name: "+task.getName()+"\n"));
        }

        public void searchTaskById(int id){
            if (Validator.validateId(id, arrayList)){
        arrayList.stream().filter(task -> task.getId() == id)
                .forEach(task -> System.out.println(task.toString()));


    }}

    public void showAllActiveTasks(){
        arrayList.stream().filter(task -> task.status == ExecutionStatus.ACTIVE).forEach(task -> System.out.println(task.toString()));
    }

    public void filterByStatus(ExecutionStatus status){
        arrayList.stream().filter(task -> task.getStatus() == status).forEach(task -> System.out.println(task.toString()));
    }

    public void filterByFinishing(LocalDateTime finishTime){
        arrayList.stream().filter(task -> task.getEndTime().isAfter(finishTime)).forEach(task -> System.out.println(task.toString()));
    }

    public void searchTaskByName(String name){
        if (Validator.validateName(name, arrayList)){
        arrayList.stream().filter(task -> task.getName().contains(name)).forEach(task -> System.out.println(task.toString()));
    }}


    }


