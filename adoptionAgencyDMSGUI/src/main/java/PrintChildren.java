import DBHelper.Children;
import DBHelper.DBHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PrintChildren extends Children {
    JFrame frame = new JFrame("PrintChildren");
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JButton mainMenuButton;
    private JTable showTable;



    public PrintChildren(String filePath) {
        setDATABASE_NAME(filePath);

        frame.setSize(800,400);
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        createTable();

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == mainMenuButton) {
                    frame.dispose();
                    Menu menu = new Menu();
                }
            }
        });
    }

    private void createTable() {
        DefaultTableModel model = new DefaultTableModel(
                null,
                new Object[]{"Child ID", "First Name", "Last Name", "Age", "Gender", "Birthday", "Interest", "Allergies", "Adoption Status"}
        );
        showTable.setModel(model);
        TableColumnModel columnModel = showTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(150);
        columnModel.getColumn(2).setPreferredWidth(150);
        columnModel.getColumn(3).setPreferredWidth(75);
        columnModel.getColumn(4).setPreferredWidth(75);
        columnModel.getColumn(5).setPreferredWidth(150);
        columnModel.getColumn(6).setPreferredWidth(150);
        columnModel.getColumn(7).setPreferredWidth(150);
        columnModel.getColumn(8).setPreferredWidth(200);


        ArrayList<ArrayList<Object>> data = select(null, null, null, null, null);
        for (ArrayList<Object> row : data) {
            model.addRow(row.toArray());
        }
    }
}

