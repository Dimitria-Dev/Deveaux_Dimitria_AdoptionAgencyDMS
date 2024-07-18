import DBHelper.Children;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
/**
 * RemoveChild -- The remove child class, removes a child by their first name or their ID.
 *
 * @author dimitriadeveaux
 */
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
    private JButton removeByIdButton;
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

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == mainMenuButton) {
                    frame.dispose();
                    Menu menu = new Menu();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == exitButton) {
                    System.exit(0);
                }
            }
        });

        removeChildByIDButton.addActionListener(new ActionListener() {
            /**
             * This method switches the card panel option to remove a child by their ID.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(removeByIdPanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });

        removeChildByNameButton.addActionListener(new ActionListener() {
            /**
             * This method switches the card panel to remove a child by their first name.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.removeAll();
                parentPanel.add(removeByNamePanel);
                parentPanel.repaint();
                parentPanel.revalidate();
            }
        });

        removeByIdButton.addActionListener(new ActionListener() {
            /**
             * This method validates if a child is in the database based on their ID. If the child is found, they will be removed.
             *
             * @param e the event to be processed
             */
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

        removeByNameButton.addActionListener(new ActionListener() {
            /**
             * This method validates if a child is in the database based on their first name. If the child is found, they
             * will be removed.
             *
             * @param e the event to be processed
             */
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
