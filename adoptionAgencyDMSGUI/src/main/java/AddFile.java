/* Dimitria Deveaux
 * CEN 3024 - Software Development I
 * July 10th, 2024
 * AddFile.java
 *  This class allows a user to add a .db (database) file with the children's information to the database
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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

        /* method: selectFileButton
         * parameter: ActionListener
         * return: none
         * purpose: to allow a user to select a .db file and upload it to the DMS
         * */
        selectFileButton.addActionListener(new ActionListener() {
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

        /* method: mainMenuButton
         * parameter: ActionListener
         * return: none
         * purpose: to return user back to the main menu
         * */
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mainMenuButton) {
                    frame.dispose();
                    Menu menu = new Menu();
                }
            }
        });

        /* method: exitButton
         * parameter: ActionListener
         * return: none
         * purpose: to exit system
         * */
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exitButton) {
                    System.exit(0);
                }
            }
        });
    }

    /* method: getSelectedFilePath
     * parameter: none
     * return: selectedFilePath
     * purpose: to return the user selected file
     * */

    public static String getSelectedFilePath() {
        return selectedFilePath;
    }

}
