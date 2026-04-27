package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddMeterInfo extends JFrame implements ActionListener {
    JTextField meterField;
    JButton nextButton, cancelButton;

    AddMeterInfo() {
        setTitle("Add Meter Info");
        setBounds(650, 300, 450, 220);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel title = new JLabel("Add Meter Information");
        title.setFont(new Font("Tahoma", Font.PLAIN, 22));
        title.setBounds(90, 20, 280, 30);
        add(title);

        JLabel meterLabel = new JLabel("Meter Number");
        meterLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        meterLabel.setBounds(60, 80, 120, 25);
        add(meterLabel);

        meterField = new JTextField();
        meterField.setBounds(190, 80, 180, 25);
        add(meterField);

        nextButton = new JButton("Next");
        nextButton.setBounds(90, 130, 100, 30);
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(this);
        add(nextButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(230, 130, 100, 30);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        add(cancelButton);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == nextButton) {
            String meter = meterField.getText().trim();

            if (meter.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter meter number");
                return;
            }

            try {
                Conn c = new Conn();

                if (c.c == null) {
                    JOptionPane.showMessageDialog(null, "Database connection failed");
                    return;
                }

                String checkCustomerQuery = "select * from customer where meter = ?";
                PreparedStatement checkCustomerPs = c.c.prepareStatement(checkCustomerQuery);
                checkCustomerPs.setString(1, meter);
                ResultSet customerRs = checkCustomerPs.executeQuery();

                if (!customerRs.next()) {
                    JOptionPane.showMessageDialog(null, "No customer found with this meter number");
                    return;
                }

                String checkMeterInfoQuery = "select * from meter_info where meter_number = ?";
                PreparedStatement checkMeterInfoPs = c.c.prepareStatement(checkMeterInfoQuery);
                checkMeterInfoPs.setString(1, meter);
                ResultSet meterInfoRs = checkMeterInfoPs.executeQuery();

                if (meterInfoRs.next()) {
                    JOptionPane.showMessageDialog(null, "Meter information already exists for this meter number");
                    return;
                }

                this.setVisible(false);
                new MeterInfo(meter).setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Could not open meter information form");
            }

        } else if (ae.getSource() == cancelButton) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddMeterInfo().setVisible(true);
    }
}