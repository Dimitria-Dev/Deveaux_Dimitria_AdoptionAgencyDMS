/* Dimitria Deveaux
 * CEN 3024 - Software Development I
 * July 10th, 2024
 * RemoveChild.java
 *  This class allows a user to remove a child by their ID number or by their name
 */
import DBHelper.Children;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

public class RemoveChild {
    JFrame frame = new JFrame("Remove Child");
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel parentPanel;
    private JButton removeChildByIDButton;
    private JButton removeChildByNameButton;
    private JPanel removeByNamePanel;
    private JPanel removeByIdPanel;
    private JTextField childID;
    private JButton removeIDButton;
    private JPanel menuPanel;
    private JButton mainMenuButton;
    private JButton exitButton;
    private JTextField childNameTextField;
    private JButton removeByNameButton;

    private String databaseFilePath;

    public RemoveChild() {
        this.databaseFilePath = AddFile.getSelectedFilePath();
        frame.setSize(500,300);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        /* method: removeChildByIDButton
         * parameter: ActionListener
         * return: none
         * purpose: to switch card panel option to allow user to remove a child by their ID
         * */
        removeChildByIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(removeByIdPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });

        /* method: removeChildByNameButton
         * parameter: ActionListener
         * return: none
         * purpose: to switch card panel option to allow user to remove a child by their name
         * */
        removeChildByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(removeByNamePanel);
                parentPanel.repaint();
                parentPanel.revalidate();
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
         * purpose: to exit the system
         * */
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == exitButton) {
                    System.exit(0);
                }
            }
        });

        /* method: removeByID
         * parameter: int id
         * return: none
         * purpose: to remove a child from the DMS by their ID number
         * */
        removeIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Children children = new Children();
                    children.setDATABASE_NAME(databaseFilePath);
                    int id = Integer.parseInt(childID.getText());
                    String childToRemove = String.valueOf(children.getExecuteResult("SELECT * FROM `Children` WHERE `child_id` = '" + id + "'"));

                    if(!childToRemove.contains(String.valueOf(id))){
                        JOptionPane.showMessageDialog(frame, "Child with the id " + id + " does not exists. Please try again.");
                        frame.dispose();
                        RemoveChild newFrame = new RemoveChild();
                        newFrame.frame.setVisible(true);
                    } else {
                        children.delete("child_id", String.valueOf(id));
                        JOptionPane.showMessageDialog(frame, "Child with ID " + id + " removed successfully!");
                        frame.dispose();
                        RemoveChild newFrame = new RemoveChild();
                        newFrame.frame.setVisible(true);
                    }
                }catch (NumberFormatException nfe){
                    JOptionPane.showMessageDialog(frame, "Please enter a valid ID number");
                } catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(frame, "Child not found. Please try again.");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
                }
            }
        });

        /* method: removeByName
         * parameter: String name
         * return: none
         * purpose: to remove a child from DMS by their first name
         * */
        removeByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Children children = new Children();
                    children.setDATABASE_NAME(databaseFilePath);
                    String name = childNameTextField.getText();
                    String nameToRemove = String.valueOf(children.getExecuteResult("SELECT * FROM `Children` WHERE `first_name` = '" + name + "'"));

                    if(!nameToRemove.contains(name)){
                        JOptionPane.showMessageDialog(frame, "Child with name " + name + " not found");
                    } else {
                        children.execute("DELETE FROM `Children` WHERE `first_name` = '" + name + "'");
                        JOptionPane.showMessageDialog(frame, "Child with name " + name + " removed successfully!");
                        frame.dispose();
                        RemoveChild newFrame = new RemoveChild();
                        newFrame.frame.setVisible(true);
                    }
                } catch (InputMismatchException ime){
                    JOptionPane.showMessageDialog(frame, "Please enter a valid name for the child");
                } catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(frame, "Child not found. Please try again.");
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
                }
            }
        });
    }
}
