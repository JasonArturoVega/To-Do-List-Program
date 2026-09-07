package mainExecution;

import poo.Task;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    //Name of the file where tasks are saved
    private final static String FILE_NAME = "Tasks.txt";

    public static void SaveTasks(List<Task> listOfTasks)
    {
        //The code tries to write a file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME)))
        {
            for (Task task: listOfTasks)
            {
                //Save tasks in format: Title / Date / Completed
                String line = task.getTaskTitle() + "|" +
                              task.getDate() + "|" + task.getHourCreated() + "|" + task.getMinuteCreated() + "|" +
                              task.getTaskCompleted();

                //Write the task
                writer.write(line);
                writer.newLine();
            }
        }
        //This is called when an error happens while trying to write in the file
        catch (IOException exception)
        {
            System.out.println("Error while saving tasks: " + exception.getMessage());
        }
    }

    public static List<Task> LoadTasks()
    {
        List<Task> tasksLoaded = new ArrayList<>();
        File file =  new File(FILE_NAME);

        if(!file.exists())
            return tasksLoaded; //Returns an empty list if the file does NOT exist

        //The code tries to read the file
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME)))
        {
            //Execute the code until an empty line is found
            String line;
            while ((line = reader.readLine()) != null)
            {
                //Get the line and saved in parts to reconstruct the task
                String[] partsOfTheTask = line.split("\\|");

                //Only get the line in five parts
                if(partsOfTheTask.length < 5) continue;

                String taskTitle = partsOfTheTask[0];
                LocalDate taskCreatedAt = LocalDate.parse(partsOfTheTask[1]);
                int hourTaskCreated = Integer.parseInt(partsOfTheTask[2]);
                int minuteTaskCreated = Integer.parseInt(partsOfTheTask[3]);
                boolean taskCompleted = Boolean.parseBoolean(partsOfTheTask[4]);

                //Reconstruct
                Task taskReconstructed = new Task(taskTitle, taskCreatedAt, hourTaskCreated, minuteTaskCreated);
                if(taskCompleted)
                    taskReconstructed.markTaskAsCompleted();

                tasksLoaded.add(taskReconstructed);

            }
        }

        //This is called when an error happens while trying to read in the file
        catch (IOException exception)
        {
            System.out.println("Error while loading tasks: " + exception.getMessage());
        }

        return tasksLoaded;
    }
}
