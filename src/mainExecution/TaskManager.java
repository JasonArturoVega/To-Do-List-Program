package mainExecution;
import poo.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    public TaskManager()
    {
        listOfTasks = new ArrayList<>();
    }

    private List<Task> listOfTasks;

    public void addTask(Task taskToAdd)
    {
        //Cant add a task that already exist
        if(listOfTasks.contains(taskToAdd))
            return;

        //Add new task
        listOfTasks.add(taskToAdd);

        //Set the id of the task depending on the index in the list
        taskToAdd.setTaskId(listOfTasks.indexOf(taskToAdd));
    }

    public void removeTask(int taskToRemove)
    {
        listOfTasks.remove(taskToRemove);
    }

    public void markAsCompleted(int taskToComplete)
    {
        Task taskCompleted = listOfTasks.get(taskToComplete);
        taskCompleted.markTaskAsCompleted();
    }

    public List<Task> getTaskList(){return listOfTasks;}
}
