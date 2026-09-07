package ui;

import mainExecution.TaskManager;
import poo.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class ButtonUi extends JButton {

    public ButtonUi(int positionX, int positionY, int sizeX, int sizeY, TypeOfButton buttonFuncion, Color buttonColor, Font buttonFont)
    {
        this.positionX = positionX;
        this.positionY = positionY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        this.buttonFuncion = buttonFuncion;

        //Set text and font
        setTextInButton();
        setFont(buttonFont);

        //Color of the button
        setBackground(buttonColor);

        setFocusable(false);
    }

    public enum TypeOfButton{ADD_TASK, REMOVE_TASK, MARK_AS_COMPLETED};
    private final TypeOfButton buttonFuncion;

    private int sizeX;
    private int sizeY;

    private int positionX;
    private int positionY;

    public void setPositionAndSize()
    {
        setBounds(positionX, positionY, sizeX, sizeY);
    }

    private void setTextInButton()
    {
        switch (buttonFuncion)
        {
            case ADD_TASK:
                setText("Add Task");
                break;

            case REMOVE_TASK:
                //Format to create lines (\n) on buttons in Java
                setText("<html>Remove<br>Selected</html>");
                break;
            case MARK_AS_COMPLETED:
                setText("<html>Task<br>Completed</html>");
                break;
        }
    }

    public void setButtonEvent(ActionListener action)
    {
        this.addActionListener(action);
    }

    public void activateEventOnMouse(TextField textReceived, DefaultListModel<String> listModelReceived,
                                     TaskList taskListReceived, TaskManager taskManagerReceived)
    {
        switch (buttonFuncion)
        {
            case ADD_TASK:

                //Replace | for - to evade errors while saving in the file
                String title = textReceived.getText().replace("|", "-").trim();

                //Cant add tasks if text is empty
                if(title.isEmpty())
                    return;

                //Create the task
                LocalDateTime currentTime = LocalDateTime.now();

                Task taskToAdd = new Task(title, currentTime.toLocalDate(),
                                          currentTime.getHour(), currentTime.getMinute());

                //Add task to the list
                taskManagerReceived.addTask(taskToAdd);

                String minTaskInText;
                if(taskToAdd.getMinuteCreated() >= 10)
                    minTaskInText = String.valueOf(taskToAdd.getMinuteCreated());
                else
                    minTaskInText = "0" + taskToAdd.getMinuteCreated();

                listModelReceived.addElement("-" + taskToAdd.getTaskTitle() + " (" + taskToAdd.getDate() + " | " +
                                             taskToAdd.getHourCreated() + ":" + minTaskInText + ")");
                textReceived.setText("");
                break;

            case REMOVE_TASK:

                //Get index of the task selected
                int selectedTaskIndex = taskListReceived.getSelectedIndex();

                if(selectedTaskIndex <= -1)
                    return;

                //Remove task from both lists
                taskManagerReceived.removeTask(selectedTaskIndex);
                listModelReceived.removeElementAt(selectedTaskIndex);

                break;
            case MARK_AS_COMPLETED:

                //Get index of the task selected and save original text
                int completedTaskIndex = taskListReceived.getSelectedIndex();

                if(completedTaskIndex <= -1)
                    return;

                String savedTaskText = listModelReceived.getElementAt(completedTaskIndex);

                //You can NOT mark a completed task more than one
                if(savedTaskText.endsWith("Completed"))
                    return;

                //Mark task as completed
                taskManagerReceived.markAsCompleted(completedTaskIndex);

                //Change text of the text list
                listModelReceived.set(completedTaskIndex, savedTaskText + " Completed");
                break;
        }
    }
}
