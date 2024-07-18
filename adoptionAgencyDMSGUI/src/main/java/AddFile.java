import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
/**
 * AddFile -- The AddFile class reads a .db(database) file with the children's information.
 */
public class AddFile {
    JFrame frame = new JFrame("Add File");
    private JPanel mainPanel;
    private JButton selectFileButton;
    private JButton mainMenuButton;
    private JButton exitButton;
    private static String selectedFilePath;

    public AddFile() {
        frame.setSize(500,300);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        selectFileButton.addActionListener(new ActionListener() {
            /**
             * This method reads the selected file and uploads the information to a table within the GUI if it is valid.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == selectFileButton) {
                    try{
                        JFileChooser fileChooser = new JFileChooser();
                        int result = fileChooser.showOpenDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            selectedFilePath = selectedFile.getAbsolutePath();
                            JOptionPane.showMessageDialog(frame, "File uploaded successfully. ");
                            frame.dispose();
                            PrintChildren printChildren = new PrintChildren(selectedFilePath);
                            printChildren.frame.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(frame, "There was an error uploading file.");
                        }
                    }catch (NumberFormatException e1){
                        JOptionPane.showMessageDialog(frame, "There was an error uploading file.");
                    } catch (Exception e2) {
                        JOptionPane.showMessageDialog(frame, "There was an error uploading the file.");
                    }

                }
            }

        });

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mainMenuButton) {
                    frame.dispose();
                    Menu menu = new Menu();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exitButton) {
                    System.exit(0);
                }
            }
        });
    }

    public static String getSelectedFilePath() {
        return selectedFilePath;
    }

}
