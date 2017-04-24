package net.agspace;

import net.agspace.translate.Translator;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Andrew's Computer on 22-Apr-17.
 */
public class Window {

    public static final List<String> OPEN_FILE_EXTENSIONS = new ArrayList<String>();

    static {
         OPEN_FILE_EXTENSIONS.add("txt");
    }

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
    private JButton saveButton;

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
                onImportClicked();
            }
        });
        //Save text to a file.
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSaveClicked();
            }
        });
        //Translate tengwar to english.
        toEnglishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        this.saveButton = new JButton();
    }

    public JPanel getMainPanel(){
        return this.mainPanel;
    }

    private void onEnglishTextChanged(){
        if (isLive)
            tengwarTextArea.setText(Translator.translateToTengwar(inputTextArea.getText()));
    }

    /**
     * What to do if the user clicks the 'import' button.
     */
    private void onImportClicked(){
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.documents"));
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory())
                    return true;
                String extension = Utils.getExtension(f);
                if (extension != null)
                    return OPEN_FILE_EXTENSIONS.contains(extension.toLowerCase());
                else
                    return false;
            }

            @Override
            public String getDescription() {
                return "Plain text files.";
            }
        });
        int result = fileChooser.showOpenDialog(this.mainPanel);
        if (result == JFileChooser.APPROVE_OPTION){
            try (BufferedReader br = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))){
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null){
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                inputTextArea.setText(sb.toString());
                tengwarTextArea.setText(Translator.translateToTengwar(inputTextArea.getText()));
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * What to do when the user wants to save their document.
     */
    private void onSaveClicked(){
        JFileChooser fileChooser = new JFileChooser(System.getProperty("user.documents"));
        int result = fileChooser.showSaveDialog(this.mainPanel);
        if (result == JFileChooser.APPROVE_OPTION){
            try (PrintStream ps = new PrintStream(fileChooser.getSelectedFile())){
                ps.println(tengwarTextArea.getText());
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
