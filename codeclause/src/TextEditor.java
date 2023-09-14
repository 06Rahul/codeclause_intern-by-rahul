import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class TextEditor extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private TextArea textArea = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
    private MenuBar menuBar = new MenuBar();
    private Menu file1 = new Menu();
    private MenuItem openfile = new MenuItem();
    private MenuItem savefile = new MenuItem();
    private MenuItem close = new MenuItem();

    public TextEditor() {
        this.setSize(900, 300);
        this.setTitle("TEXTEDITOR");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.textArea.setFont(new Font("Century Gothic", Font.BOLD, 20));
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(textArea);
        this.setMenuBar(this.menuBar);
        this.menuBar.add(this.file1);
        this.file1.setLabel("File");
        this.openfile.setLabel("Open");
        this.openfile.addActionListener(this);
        this.openfile.setShortcut(new MenuShortcut(KeyEvent.VK_O, false));
        this.savefile.setLabel("Save"); // Add this line
        this.savefile.addActionListener(this); // Add this line
        this.file1.add(this.openfile);
        this.file1.add(this.savefile); // Add this line
        this.close.setLabel("Close");
        this.close.setShortcut(new MenuShortcut(KeyEvent.VK_F4, false));
        this.file1.add(this.close);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.close) {
            this.dispose();
        } else if (e.getSource() == this.openfile) {
            JFileChooser open = new JFileChooser();
            int option = open.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                this.textArea.setText("");
                try {
                    Scanner scr = new Scanner(open.getSelectedFile());
                    while (scr.hasNext())
                        this.textArea.append(scr.nextLine() + "\n");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else if (e.getSource() == this.savefile) { // Handle Save action
            // Add code to save the contents of the textArea to a file
            // You can use JFileChooser again to specify the save location.
        }
    }

    public static void main(String[] args) {
        TextEditor app = new TextEditor();
        app.setVisible(true);
    }
}
