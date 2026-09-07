package mainExecution;

import poo.Task;
import ui.AddTaskText;
import ui.ButtonUi;
import ui.TaskList;
import ui.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.List;

public class UseToDoProgram {

    static void main(String[] args) {

        //Manager of tasks
        TaskManager taskManager = new TaskManager();

        //Main window to have everything
        Window mainWindow = new Window("List of tasks", 500, 700);
        mainWindow.setPositionAndSize();

        //Add the event of saving the list of tasks when the window close
        mainWindow.addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e) {
                FileManager.SaveTasks(taskManager.getTaskList());
            }
        });
        //Close program when closing the window
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Text to write tasks
        Font textForTasksFont = new Font("Elephant", Font.BOLD, 30);
        AddTaskText textOnScreen = new AddTaskText(80, 10, 320, 50, textForTasksFont);

        textOnScreen.setPositionAndSize();
        mainWindow.add(textOnScreen);

        //List of tasks
        Font taskListFont = new Font("Californian FB", Font.BOLD, 18);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        TaskList taskList = new TaskList(45, 80, 400, 500, taskListFont, listModel);

        taskList.setPositionAndSize();
        mainWindow.add(taskList);

        //Load saved tasks if possible
        List<Task> loadedTasks = FileManager.LoadTasks();

        for (Task loadedTask : loadedTasks)
        {
            taskManager.addTask(loadedTask);

            String minTaskInText;
            if(loadedTask.getMinuteCreated() >= 10)
                minTaskInText = String.valueOf(loadedTask.getMinuteCreated());
            else
                minTaskInText = "0" + loadedTask.getMinuteCreated();

            String textTaskToList = "-" + loadedTask.getTaskTitle() + " (" + loadedTask.getDate() + " | " +
                                    loadedTask.getHourCreated() + ":" + minTaskInText + ")";

            if(loadedTask.getTaskCompleted())
                listModel.addElement(textTaskToList + " Completed");
            else
                listModel.addElement(textTaskToList);
        }

        //Button to create tasks
        Font buttonFont = new Font("Californian FB", Font.BOLD, 20);
        ButtonUi addTaskButton = new ButtonUi(185, 600, 120, 50,
                                               ButtonUi.TypeOfButton.ADD_TASK, Color.yellow, buttonFont);

        addTaskButton.setButtonEvent(e -> addTaskButton.activateEventOnMouse(textOnScreen, listModel, taskList, taskManager));

        addTaskButton.setPositionAndSize();
        mainWindow.add(addTaskButton);

        //Button to remove task
        ButtonUi removeTaskButton = new ButtonUi(45, 600, 120, 50,
                                                 ButtonUi.TypeOfButton.REMOVE_TASK, Color.red, buttonFont);

        removeTaskButton.setButtonEvent(e -> removeTaskButton.activateEventOnMouse(textOnScreen, listModel, taskList, taskManager));
        removeTaskButton.setPositionAndSize();
        mainWindow.add(removeTaskButton);

        //Button to mark task at completed
        ButtonUi completedTaskButton = new ButtonUi(325, 600, 120, 50,
                                                     ButtonUi.TypeOfButton.MARK_AS_COMPLETED, Color.green, buttonFont);
        completedTaskButton.setButtonEvent(e -> completedTaskButton.activateEventOnMouse(textOnScreen, listModel, taskList, taskManager));
        completedTaskButton.setPositionAndSize();
        mainWindow.add(completedTaskButton);

        mainWindow.setVisible(true);
    }
}
