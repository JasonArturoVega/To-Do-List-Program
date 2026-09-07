package ui;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(String titleOnTop, int sizeX, int sizeY)
    {
        this.titleOnTop = titleOnTop;

        this.sizeX = sizeX;
        this.sizeY = sizeY;

        setLayout(null);
    }

    private String titleOnTop;

    private int sizeX;
    private int sizeY;

    public void setPositionAndSize()
    {
        //Width and height
        setSize(sizeX, sizeY);

        //No fullscreen
        setExtendedState(Frame.NORMAL);

        //Screen right in the middle of the screen
        setLocationRelativeTo(null);
    }

    public void setWindowTitle()
    {
        setTitle(titleOnTop);
    }
}
