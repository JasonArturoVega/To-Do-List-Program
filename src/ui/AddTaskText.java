package ui;

import java.awt.*;

public class AddTaskText extends TextField {

    public AddTaskText(int positionX, int positionY, int sizeX, int sizeY, Font textFont)
    {
        this.positionX = positionX;
        this.positionY = positionY;

        this.sizeX = sizeX;
        this.sizeY = sizeY;

        setFont(textFont);
        setText("");
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
