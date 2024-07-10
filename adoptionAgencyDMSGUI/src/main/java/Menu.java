/* Dimitria Deveaux
 * CEN 3024 - Software Development I
 * July 10th, 2024
 * Menu.java
 *  This class holds the main menu a user will use to navigate each task
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu {
    JFrame frame = new JFrame("Menu");
    private JPanel mainPanel;
    private JPanel headerPanel;
    private JPanel buttonPanel;
    private JButton addChildButton;
    private JButton addFileButton;
    private JButton removeChildButton;
    private JButton adoptionStatusButton;
    private JButton printChildrenButton;
    private JButton updateInformationButton;
    private JButton exitButton;

    public Menu() {

        frame.setSize(500,300);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        /* method: addChildButton
         * parameter: ActionListener
         * return: none
         * purpose: to switch to add a child frame
         * */
        addChildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == addChildButton) {
                    frame.dispose();
                    String dbFilePath = AddFile.getSelectedFilePath();
                    AddChild addChild = new AddChild();
                }
            }
        });

        /* method: addFileButton
         * parameter: ActionListener
         * return: none
         * purpose: to switch to add file frame
         * */
        addFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == addFileButton) {
                    frame.dispose();
                    AddFile addFile = new AddFile();
                }
            }
        });

        /* method: adoptionStatusButton
         * parameter: ActionListener
         * return: none
         * purpose: to switch to the adoption status task frame
         * */
        adoptionStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == adoptionStatusButton) {
                    frame.dispose();
                    AdoptionStatus adoptionStatus = new AdoptionStatus();
                }
            }
        });

        /* method: removeChildButton
         * parameter: ActionListener
         * return: none
         * purpose: to switch to remove child frame
         * */
        removeChildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == removeChildButton) {
                    frame.dispose();
                    RemoveChild removeChild = new RemoveChild();
                }
            }
        });

        /* method: printChildrenButton
         * parameter: ActionListener
         * return: none
         * purpose: to switch to the print children frame
         * */
        printChildrenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == printChildrenButton) {
                    AddFile addFile = new AddFile();
                    frame.dispose();
                    String databaseFilePath = AddFile.getSelectedFilePath();
                    //String databaseFilePath = addFile.getSelectedFilePath();
                    PrintChildren printChildren = new PrintChildren(databaseFilePath);
                }
            }
        });

        /* method: updateInformationButton
         * parameter: ActionListener
         * return: none
         * purpose: to switch to the update information frame
         * */
        updateInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == updateInformationButton) {
                    frame.dispose();
                    UpdateInformation updateInformation = new UpdateInformation();
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
                if(e.getSource() == exitButton) {
                    System.exit(0);
                }
            }
        });
    }

}
