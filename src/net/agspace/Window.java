package net.agspace;

import net.agspace.translate.Translator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Andrew's Computer on 22-Apr-17.
 */
public class Window {

    private JPanel mainPanel;
    private JPanel tengwarPanel;
    private JPanel inputPanel;
    private JLabel inputPanelLabel;
    private JPanel inputPanelTop;
    private JTextArea inputTextArea;
    private JPanel tengwarPanelTop;
    private JTextArea tengwarTextArea;
    private JLabel tengwarPanelLabel;
    private JButton toTengwarButton;
    private JButton toEnglishButton;
    private JPanel translateButtonPanel;
    private JCheckBox liveCheckBox;
    private JButton clearButton;
    private JButton importButton;

    private boolean isLive = false;

    public Window() {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font tengwar_annatar = null;
        try {
            tengwar_annatar = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getClassLoader().getResourceAsStream("resources/tngan.ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ge.registerFont(tengwar_annatar);

        //Explicit translation from english to tengwar.
        toTengwarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tengwarTextArea.setText(Translator.translateToTengwar(inputTextArea.getText()));
            }
        });
        //Toggle live translation, and activation/deactivation of other buttons.
        liveCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (liveCheckBox.isSelected()){
                    //Deactivate buttons, set live to true.
                    toTengwarButton.setEnabled(false);
                    toEnglishButton.setEnabled(false);
                    isLive = true;
                } else {
                    //Activate buttons, set live to false.
                    toTengwarButton.setEnabled(true);
                    toEnglishButton.setEnabled(true);
                    isLive = false;
                }
            }
        });
        //Clear both text areas.
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tengwarTextArea.setText(null);
                inputTextArea.setText(null);
            }
        });
        //Import text from a file.
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()){
                    if (Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
                        try {
                            Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=lsJLLEwUYZM"));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private void createUIComponents() {
        mainPanel = new JPanel();
        this.inputTextArea = new JTextArea();
        inputTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onEnglishTextChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onEnglishTextChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onEnglishTextChanged();
            }
        });
        this.tengwarTextArea = new JTextArea();
        this.toTengwarButton = new JButton();
        this.toEnglishButton = new JButton();
        this.liveCheckBox = new JCheckBox();
        this.clearButton = new JButton();
        this.importButton = new JButton();
        this.inputTextArea.requestFocus();
    }

    public JPanel getMainPanel(){
        return this.mainPanel;
    }

    private void onEnglishTextChanged(){
        if (isLive)
            tengwarTextArea.setText(Translator.translateToTengwar(inputTextArea.getText()));
    }

}
