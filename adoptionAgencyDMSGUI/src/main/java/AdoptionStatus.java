import DBHelper.Children;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * AdoptionStatus -- The AdoptionStatus class allows a child's adoption status to be updated or checked.
 *
 * @author dimitriadeveaux
 */
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
    private JButton checkStatusButton;
    private JTextField updateStatustextField1;
    private JButton updateStatusSearchButton;
    private JComboBox adoptionStatusOptions;
    private JPanel menuPanel;
    private JButton mainMenuButton;
    private JButton exitButton;
    private JPanel options;
    private JComboBox adoptionStatusComboBox;
    private JButton changeAdoptionStatusButton;
    private JButton update;
    private String databaseFilePath;

    public AdoptionStatus() {
        this.databaseFilePath = AddFile.getSelectedFilePath();
        frame.setSize(500,300);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        checkAdoptionStatusButton.addActionListener(new ActionListener() {
            /**
             * This method switches to the adoption status panel where a child's adoption status can be checked.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(checkAdoptionStatusPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });

        updateAdoptionStatusButton.addActionListener(new ActionListener() {
            /**
             * This method switches to the update adoption status panel where a child's adoption status can be updated.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(updateAdoptionStatusPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
                options.setVisible(false);
            }
        });

        mainMenuButton.addActionListener(new ActionListener() {
            /**
             * This method goes to the main menu frame
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == mainMenuButton) {
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
                if(e.getSource() == exitButton) {
                    System.exit(0);
                }
            }
        });

        checkStatusButton.addActionListener(new ActionListener() {
            /**
             * This method validates if a child is in the database based on their ID to display their adoption status.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Children children = new Children();
                    children.setDATABASE_NAME(databaseFilePath);
                    int id = Integer.parseInt(checkStatusTextField.getText());
                    String childIdSearch = children.adoptionStatusChildId(id);

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

        updateStatusSearchButton.addActionListener(new ActionListener() {
            /**
             * This method validates if a child is in the database based on their ID to update their adoption status.
             * @param e the event to be processed
             */
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

        changeAdoptionStatusButton.addActionListener(new ActionListener() {
            /**
             * This method allows a child's adoption status to be updated if their ID number was found.
             * @param e the event to be processed
             */
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

    /**
     * This method is used to set the adoption status options.
     * @param status This is the adoption status chosen
     */
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
