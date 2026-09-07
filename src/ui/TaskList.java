package ui;

import javax.swing.*;
import java.awt.*;

public class TaskList extends JList {

    public TaskList(int positionX, int positionY, int sizeX, int sizeY, Font listFont, DefaultListModel listModel)
    {
        super(listModel);

        this.positionX = positionX;
        this.positionY = positionY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        setFont(listFont);

        setFocusable(false);
    }
    private int sizeX;
    private int sizeY;

    private int positionX;
    private int positionY;

    public void setPositionAndSize()
    {
        setBounds(positionX, positionY, sizeX, sizeY);
    }
}
