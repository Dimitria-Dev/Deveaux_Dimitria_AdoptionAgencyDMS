/* Dimitria Deveaux
 * CEN 3024 - Software Development I
 * July 10th, 2024
 * AdoptionStatus.java
 *  This class allows a user to check the adoption status of a child and also update the adoption status of a child
 */
import DBHelper.Children;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class AdoptionStatus extends Children{
    JFrame frame = new JFrame("AdoptionStatus");
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton checkAdoptionStatusButton;
    private JButton updateAdoptionStatusButton;
    private JPanel parentPanel;
    private JPanel checkAdoptionStatusPanel;
    private JPanel updateAdoptionStatusPanel;
    private JPanel adoptionStatusOptionsPanel;
    private JTextField checkStatusTextField;
    private JButton cSearchButton;
    private JTextField updateStatustextField1;
    private JButton uSearchButton;
    private JComboBox adoptionStatusOptions;
    private JPanel menuPanel;
    private JButton mainMenuButton;
    private JButton exitButton;
    private JPanel options;
    private JComboBox adoptionStatusComboBox;
    private JButton updateButton;
    private JButton update;

    private String databaseFilePath;

    public AdoptionStatus() {
        this.databaseFilePath = AddFile.getSelectedFilePath();
        frame.setSize(500,300);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        /* method: checkAdoptionStatusButton
         * parameter: ActionListener
         * return: none
         * purpose: to switch card panel option to allow user to check an adoption status
         * */
        checkAdoptionStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(checkAdoptionStatusPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });

        /* method: updateAdoptionStatus
         * parameter: ActionListener
         * return: none
         * purpose: to switch card panel option to allow user to update a child's adoption status
         * */
        updateAdoptionStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(updateAdoptionStatusPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
                options.setVisible(false);
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
                if(e.getSource() == mainMenuButton) {
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
                if(e.getSource() == exitButton) {
                    System.exit(0);
                }
            }
        });

        /* method: cSearchButton
         * parameter: ActionListener
         * return: none
         * purpose: to search for a child in the DMS by their ID number to check their adoption status
         * */
        cSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Children children = new Children();
                    children.setDATABASE_NAME(databaseFilePath);
                    int id = Integer.parseInt(checkStatusTextField.getText());
                    String childIdSearch = children.searchChildById(id);

                    if(!childIdSearch.contains(String.valueOf(id))){
                        JOptionPane.showMessageDialog(frame, "Child does not exist");
                    }else {
                        JOptionPane.showMessageDialog(frame, childIdSearch);
                        frame.dispose();
                        AdoptionStatus adoptionStatus = new AdoptionStatus();
                        adoptionStatus.frame.setVisible(true);
                    }
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                } catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(frame, "Child not found. Please try again.");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
                }
            }
        });

        /* method: uSearchButton
         * parameter: ActionListener
         * return: none
         * purpose: to search for a child in the DMS by their ID number to update their adoption status
         * */
        uSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Children children = new Children();
                    children.setDATABASE_NAME(databaseFilePath);
                    int id = Integer.parseInt(updateStatustextField1.getText());
                    String childIdSearch = String.valueOf(children.getExecuteResult("SELECT `child_id` FROM `Children` WHERE `child_id` =" + id));

                    if(!childIdSearch.contains(String.valueOf(id))){
                        JOptionPane.showMessageDialog(frame, "There was an error finding Child ID " + id + ". Please try again.");

                    } else{
                        options.setVisible(true);
                        initializeAdoptionStatusBox(childIdSearch);
                    }
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                } catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(frame, "Child not found. Please try again.");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
                }
            }
        });

        /* method: updateButton
         * parameter: ActionListener
         * return: none
         * purpose: to update the child's adoption status based on the selected child ID number
         * */
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Children children = new Children();
                    children.setDATABASE_NAME(databaseFilePath);
                    String updateStatus = adoptionStatusComboBox.getSelectedItem().toString();
                    int id = Integer.parseInt(updateStatustextField1.getText());
                    String searchChildById = String.valueOf(children.getExecuteResult("SELECT `child_id` FROM `Children` WHERE `child_id` = " + id));

                    if(!searchChildById.contains(String.valueOf(id))){
                        JOptionPane.showMessageDialog(frame, "Child does not exist");
                    } else {
                        children.update(adoption_status, updateStatus, child_id, String.valueOf(id));
                        JOptionPane.showMessageDialog(frame, "Child adoption status updated successfully.");
                        frame.dispose();
                        AdoptionStatus adoptionStatus = new AdoptionStatus();
                        adoptionStatus.frame.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID number.");
                } catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(frame, "Please enter an ID number.");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
                }
            }
        });
    }
    /* method: initializeAdoptionStatus
     * parameter: String status
     * return: none
     * purpose: to set the options for the adoption status combo box
     * */
    private void initializeAdoptionStatusBox(String status) {
        DefaultComboBoxModel<String> adoptionStatusModel = new DefaultComboBoxModel<>();
        adoptionStatusModel.addElement("Available For Adoption");
        adoptionStatusModel.addElement("Adoption In Progress");
        adoptionStatusModel.addElement("Adoption Finalized");
        adoptionStatusModel.addElement("Adoption Status Unknown");
        adoptionStatusComboBox.setModel(adoptionStatusModel);
        adoptionStatusComboBox.setSelectedItem(status);
    }
}
