import DBHelper.Children;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * UpdateInformation -- The UpdateInformation class updates a child's allergy or hobby
 *
 * @author dimitriadeveaux
 */
public class UpdateInformation extends Children {
    JFrame frame = new JFrame("Update Information");
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel parentPanel;
    private JButton updateAllergiesButton;
    private JButton updateInterestButton;
    private JPanel updateAllergiesPanel;
    private JPanel updateInterestPanel;
    private JTextField childIdtextField;
    private JButton searchButton;
    private JTextField childIDtextField2;
    private JButton interestSearchButton;
    private JPanel menuPanel;
    private JButton mainMenuButton;
    private JButton exitButton;
    private JButton exitFromUpdateInterestButton;
    private JButton exitFromUpdateAllergyButton;
    private JPanel allergiesUpdatePanel;
    private JTextField allergyTextField;
    private JButton updateButton;
    private JPanel updateInterest;
    private JButton changeInterestButton;
    private JTextField InterestTextField1;
    private String databaseFilePath;

    public UpdateInformation() {
        this.databaseFilePath = AddFile.getSelectedFilePath();
        frame.setSize(500,300);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        allergiesUpdatePanel.setVisible(false);
        updateInterest.setVisible(false);

        mainMenuButton.addActionListener(new ActionListener() {
            /**
             * This method goes back to the main menu
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == exitFromUpdateInterestButton) {
                    frame.dispose();
                    Menu menu = new Menu();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            /**
             * This method exits the program
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == exitFromUpdateInterestButton) {
                    frame.dispose();
                    System.exit(0);
                }
            }
        });

        updateAllergiesButton.addActionListener(new ActionListener() {
            /**
             * This method switches the card panel option to allow a child's allergy to be updated
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(updateAllergiesPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });

        exitFromUpdateAllergyButton.addActionListener(new ActionListener() {
            /**
             * This method exits to the main menu if a user is in the middle of updating an allergy.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == exitFromUpdateAllergyButton) {
                    frame.dispose();
                    Menu menu = new Menu();
                }
            }
        });


        searchButton.addActionListener(new ActionListener() {
            /**
             * This method validates if a child is in the database based on their ID number. If the child is found, their
             * allergy can be updated.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == searchButton) {
                    try {
                        Children children = new Children();
                        children.setDATABASE_NAME(databaseFilePath);
                        int id = Integer.parseInt(childIdtextField.getText());
                        String childFound = String.valueOf(children.getExecuteResult("SELECT `child_id` FROM `Children` WHERE `child_id` = " + id));

                        if(!childFound.contains(String.valueOf(id))) {
                            JOptionPane.showMessageDialog(frame, "There was an error finding Child with ID " + id + ". Please try again.");
                        } else {
                            allergiesUpdatePanel.setVisible(true);
                        }
                    } catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                    } catch (NullPointerException ex){
                        JOptionPane.showMessageDialog(frame, "Please enter a valid ID number.");
                    } catch (Exception ex){
                        JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
                    }
                }

            }
        });

        updateButton.addActionListener(new ActionListener() {
            /**
             * This method allows a child allergy to be updated if they are in the database based on their ID.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Children children = new Children();
                    children.setDATABASE_NAME(databaseFilePath);
                    String updateAllergy = allergyTextField.getText();

                    int id = Integer.parseInt(childIdtextField.getText());
                    String childIdSearch = String.valueOf(children.getExecuteResult("SELECT `child_id` FROM `Children` WHERE `child_id` = " + id));

                    if(!childIdSearch.contains(String.valueOf(id))) {
                        JOptionPane.showMessageDialog(frame, "There was an error finding Child ID " + id + ". Please try again.");
                    } else {
                        children.update(allergies,updateAllergy,child_id, String.valueOf(id));
                        JOptionPane.showMessageDialog(frame, "Child Allergy Updated Successfully.");
                        frame.dispose();
                        UpdateInformation updateInformation = new UpdateInformation();
                        updateInformation.frame.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID number.");
                } catch (NullPointerException npe){
                    JOptionPane.showMessageDialog(frame, "Please enter a valid  child ID.");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
                }
            }
        });

        updateInterestButton.addActionListener(new ActionListener() {
            /**
             * This method switches the card panel option allow  a child's interest to be updated.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(updateInterestPanel);
                parentPanel.repaint();
                parentPanel.revalidate();

            }
        });

        exitFromUpdateInterestButton.addActionListener(new ActionListener() {
            /**
             * This method exits to the main menu if a user is in the middle of updating an interest.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == exitFromUpdateInterestButton) {
                    frame.dispose();
                    Menu menu = new Menu();
                }
            }
        });

        interestSearchButton.addActionListener(new ActionListener() {
            /**
             * This method validates if a child is in the database based on their ID number. If the child is found, their
             * interest can be updated.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Children children = new Children();
                    children.setDATABASE_NAME(databaseFilePath);
                    int id = Integer.parseInt(childIDtextField2.getText());
                    String childIdSearch = String.valueOf(children.getExecuteResult("SELECT `child_id` FROM `Children` WHERE `child_id` = " + id));

                    if(!childIdSearch.contains(String.valueOf(id))) {
                        JOptionPane.showMessageDialog(frame, "There was an error finding Child ID " + id + ". Please try again.");
                    } else {
                        updateInterest.setVisible(true);
                    }
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                } catch (NullPointerException npe){
                    JOptionPane.showMessageDialog(frame, "Please enter a valid  child ID.");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
                }
            }
        });

        changeInterestButton.addActionListener(new ActionListener() {
            /**
             * This method allows a child's interest to be changed if they are found in the database based on their ID.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Children children = new Children();
                    children.setDATABASE_NAME(databaseFilePath);
                    String updateInterest = InterestTextField1.getText();
                    int id = Integer.parseInt(childIDtextField2.getText());
                    String childIdSearch = String.valueOf(children.getExecuteResult("SELECT `child_id` FROM `Children` WHERE `child_id` = " + id));

                    if(!childIdSearch.contains(String.valueOf(id))){
                        JOptionPane.showMessageDialog(frame, "There was an error finding Child ID " + id + ". Please try again.");
                    } else {
                        children.update(interest, updateInterest, child_id, String.valueOf(id));
                        frame.dispose();
                        UpdateInformation updateInformation = new UpdateInformation();
                        updateInformation.frame.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID number.");
                } catch (NullPointerException npe){
                    JOptionPane.showMessageDialog(frame, "Please enter a valid  child ID.");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
                }
            }
        });
    }
}
