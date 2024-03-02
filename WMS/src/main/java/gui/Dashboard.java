package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Represents a dashboard dialog.
 */
public class Dashboard extends JDialog{
    private JButton CLIENTSOPERATIONSButton;
    private JButton PRODUCTOPERATIONSButton;
    private JButton ORDEROPERATIONSButton;
    private JPanel Dashboard;

    public Dashboard(JFrame parent){
        super(parent);
        setContentPane(Dashboard);
        setMinimumSize(new Dimension(950,800));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        CLIENTSOPERATIONSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientOperations clientOperations=new ClientOperations(null);
            }
        });

        PRODUCTOPERATIONSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductOperations productOperations=new ProductOperations(null);
            }
        });

        ORDEROPERATIONSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               OrdersOperations ordersOperations=new OrdersOperations(null);
            }
        });
        setVisible(true);
    }
}
