import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Menu -- The menu class displays options for the user to perform a task
 *
 * @author dimitriadeveaux
 */
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

        addFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == addFileButton) {
                    frame.dispose();
                    AddFile addFile = new AddFile();
                }
            }
        });

        adoptionStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == adoptionStatusButton) {
                    frame.dispose();
                    AdoptionStatus adoptionStatus = new AdoptionStatus();
                }
            }
        });

        removeChildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == removeChildButton) {
                    frame.dispose();
                    RemoveChild removeChild = new RemoveChild();
                }
            }
        });

        printChildrenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == printChildrenButton) {
                    AddFile addFile = new AddFile();
                    frame.dispose();
                    String databaseFilePath = AddFile.getSelectedFilePath();
                    PrintChildren printChildren = new PrintChildren(databaseFilePath);
                }
            }
        });

        updateInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == updateInformationButton) {
                    frame.dispose();
                    UpdateInformation updateInformation = new UpdateInformation();
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
    }

}
