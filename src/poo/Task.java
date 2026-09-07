package poo;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {

    public Task(String taskTitle, LocalDate createdAt, int hourCreated, int minuteCreated)
    {
        this.taskTitle = taskTitle;
        this.createdAt = createdAt;

        this.hourCreated = hourCreated;
        this.minuteCreated = minuteCreated;

        taskCompleted = false;
    }

    private int idTask;
    private final String taskTitle;

    private boolean taskCompleted;
    private final LocalDate createdAt;
    private final int hourCreated;
    private final int minuteCreated;

    public void markTaskAsCompleted()
    {
        taskCompleted = true;
    }

    public String getTaskTitle(){return taskTitle;}
    public int getTaskId(){return idTask;}
    public String getDate(){return createdAt.toString();}
    public int getHourCreated(){return hourCreated;}
    public int getMinuteCreated(){return minuteCreated;}
    public boolean getTaskCompleted(){return taskCompleted;}

    public void setTaskId(int id){idTask = id;}

    //Methods to compare if two tasks are the same
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Task other = (Task) obj;
        return taskTitle.equalsIgnoreCase(other.taskTitle);
    }

    @Override
    public int hashCode() {
        return taskTitle.toLowerCase().hashCode();
    }
}
