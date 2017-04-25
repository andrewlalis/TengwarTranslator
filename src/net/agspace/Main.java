package net.agspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

/**
 * @author Andrew Lalis
 * Class from which program starts.
 */
public class Main {

    public static final String TITLE = "Tengwar Typewriter";
    public static final String ICON_PATH = "resources/icon.png";

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
                saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
                saveItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        window.onSaveClicked();
                    }
                });
                fileMenu.add(saveItem);
                //Import Item.
                JMenuItem importItem = new JMenuItem("Import");
                importItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        window.onImportClicked();
                    }
                });
                fileMenu.add(importItem);
                //Exit Item.
                JMenuItem exitItem = new JMenuItem("Exit");
                exitItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        f.dispose();
                    }
                });
                fileMenu.add(exitItem);
            menuBar.add(fileMenu);
            //Edit menu.
            JMenu editMenu = new JMenu("Edit");
                //Live checkbox
                JCheckBoxMenuItem liveCheckBox = new JCheckBoxMenuItem("Live");
                liveCheckBox.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
                liveCheckBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        window.onLiveToggled(e);
                    }
                });
                editMenu.add(liveCheckBox);
            menuBar.add(editMenu);
            //About Menu.
            JMenu aboutMenu = new JMenu("About");
                //Tengwar Item.
                JMenuItem tengwarItem = new JMenuItem("Tengwar");
                tengwarItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        window.onTengwarAboutClicked();
                    }
                });
                aboutMenu.add(tengwarItem);
                //English Mode.
                JMenuItem englishModeItem = new JMenuItem("English Mode");
                englishModeItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        window.onEnglishModeAboutClicked();
                    }
                });
                aboutMenu.add(englishModeItem);
                //About the author.
                JMenuItem aboutMeItem = new JMenuItem("About the Author");
                aboutMeItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        window.onAboutMeClicked();
                    }
                });
                aboutMenu.add(aboutMeItem);
            menuBar.add(aboutMenu);


        f.setJMenuBar(menuBar);
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
