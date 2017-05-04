package net.agspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * @author Andrew Lalis
 * Class from which program starts.
 */
public class Main {

    private static final String TITLE = "Tengwar Typewriter";
    private static final String ICON_PATH = "resources/icon.png";

    public static void main(String[] args){
        JFrame f = new JFrame(TITLE);
        Window window = new Window();
        //Set application icon.
        f.setIconImage(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource(ICON_PATH)));
        //Set the main panel as the content pane.
        f.setContentPane(window.getMainPanel());
        //Create a menu bar.
        JMenuBar menuBar = new JMenuBar();
            //File Menu.
            JMenu fileMenu = new JMenu("File");
                //Save Item.
                JMenuItem saveItem = new JMenuItem("Save");
                saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
                saveItem.addActionListener(e -> window.onSaveClicked());
                fileMenu.add(saveItem);
                //Import Item.
                JMenuItem importItem = new JMenuItem("Import");
                importItem.addActionListener(e -> window.onImportClicked());
                fileMenu.add(importItem);
                //Exit Item.
                JMenuItem exitItem = new JMenuItem("Exit");
                exitItem.addActionListener(e -> f.dispose());
                fileMenu.add(exitItem);
            menuBar.add(fileMenu);
            //Edit menu.
            JMenu editMenu = new JMenu("Edit");
                //Live checkbox
                JCheckBoxMenuItem liveCheckBox = new JCheckBoxMenuItem("Live");
                liveCheckBox.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
                liveCheckBox.addActionListener(e -> window.onLiveToggled(e));
                editMenu.add(liveCheckBox);
            menuBar.add(editMenu);
            //About Menu.
            JMenu aboutMenu = new JMenu("About");
                //Tengwar Item.
                JMenuItem tengwarItem = new JMenuItem("Tengwar");
                tengwarItem.addActionListener(e -> window.onTengwarAboutClicked());
                aboutMenu.add(tengwarItem);
                //English Mode.
                JMenuItem englishModeItem = new JMenuItem("English Mode");
                englishModeItem.addActionListener(e -> window.onEnglishModeAboutClicked());
                aboutMenu.add(englishModeItem);
                //About the author.
                JMenuItem aboutMeItem = new JMenuItem("About the Author");
                aboutMeItem.addActionListener(e -> window.onAboutMeClicked());
                aboutMenu.add(aboutMeItem);
            menuBar.add(aboutMenu);


        f.setJMenuBar(menuBar);
        f.pack();
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        f.setVisible(true);
    }
}
