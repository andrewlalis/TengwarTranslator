package net.agspace;

import sun.font.TrueTypeFont;
import sun.plugin.dom.exception.InvalidStateException;

import javax.swing.*;
import java.awt.*;

/**
 * @author Andrew Lalis
 * Class from which program starts.
 */
public class Main {

    public static final String TITLE = "Tengwar Typewriter";

    public static void main(String[] args){
        JFrame f = new JFrame(TITLE);
        f.setContentPane(new Window().getMainPanel());
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        f.setVisible(true);
    }
}
