package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NewCustomer extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8;
    JPasswordField t9;
    JButton b1, b2;

    NewCustomer() {
        setLocation(600, 200);
        setSize(700, 560);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));

        JLabel title = new JLabel("New Customer");
        title.setBounds(180, 10, 220, 26);
        title.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(title);

        l1 = new JLabel("Customer Name");
        l1.setBounds(100, 70, 120, 20);
        p.add(l1);

        t1 = new JTextField();
        t1.setBounds(240, 70, 200, 20);
        p.add(t1);

        l2 = new JLabel("Meter No");
        l2.setBounds(100, 110, 120, 20);
        p.add(l2);

        t2 = new JTextField();
        t2.setBounds(240, 110, 200, 20);
        p.add(t2);

        l3 = new JLabel("Address");
        l3.setBounds(100, 150, 120, 20);
        p.add(l3);

        t3 = new JTextField();
        t3.setBounds(240, 150, 200, 20);
        p.add(t3);

        l5 = new JLabel("City");
        l5.setBounds(100, 190, 120, 20);
        p.add(l5);

        t5 = new JTextField();
        t5.setBounds(240, 190, 200, 20);
        p.add(t5);

        l4 = new JLabel("State");
        l4.setBounds(100, 230, 120, 20);
        p.add(l4);

        t4 = new JTextField();
        t4.setBounds(240, 230, 200, 20);
        p.add(t4);

        l6 = new JLabel("Email");
        l6.setBounds(100, 270, 120, 20);
        p.add(l6);

        t6 = new JTextField();
        t6.setBounds(240, 270, 200, 20);
        p.add(t6);

        l7 = new JLabel("Phone Number");
        l7.setBounds(100, 310, 120, 20);
        p.add(l7);

        t7 = new JTextField();
        t7.setBounds(240, 310, 200, 20);
        p.add(t7);

        l9 = new JLabel("Username");
        l9.setBounds(100, 350, 120, 20);
        p.add(l9);

        t8 = new JTextField();
        t8.setBounds(240, 350, 200, 20);
        p.add(t8);

        l10 = new JLabel("Password");
        l10.setBounds(100, 390, 120, 20);
        p.add(l10);

        t9 = new JPasswordField();
        t9.setBounds(240, 390, 200, 20);
        p.add(t9);

        b1 = new JButton("Create");
        b1.setBounds(120, 450, 100, 25);
        b2 = new JButton("Cancel");
        b2.setBounds(250, 450, 100, 25);

        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);

        p.add(b1);
        p.add(b2);

        setLayout(new BorderLayout());
        add(p, "Center");

        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i3 = ic1.getImage().getScaledInstance(150, 300, Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i3);
        l8 = new JLabel(ic2);

        add(l8, "West");

        getContentPane().setBackground(Color.WHITE);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            String name = t1.getText().trim();
            String meter = t2.getText().trim();
            String address = t3.getText().trim();
            String city = t5.getText().trim();
            String state = t4.getText().trim();
            String email = t6.getText().trim();
            String phone = t7.getText().trim();
            String username = t8.getText().trim();
            String password = new String(t9.getPassword()).trim();

            if (name.isEmpty() || meter.isEmpty() || address.isEmpty() || city.isEmpty()
                    || state.isEmpty() || email.isEmpty() || phone.isEmpty()
                    || username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields");
                return;
            }

            String checkMeterQuery = "select * from customer where meter = ?";
            String checkUsernameQuery = "select * from login where username = ?";
            String customerQuery = "insert into customer values (?, ?, ?, ?, ?, ?, ?)";
            String loginQuery = "insert into login (meter_no, username, name, password, user) values (?, ?, ?, ?, ?)";

            try {
                Conn c1 = new Conn();

                if (c1.c == null) {
                    JOptionPane.showMessageDialog(null, "Database connection failed");
                    return;
                }

                PreparedStatement checkMeterPs = c1.c.prepareStatement(checkMeterQuery);
                checkMeterPs.setString(1, meter);
                ResultSet meterRs = checkMeterPs.executeQuery();

                if (meterRs.next()) {
                    JOptionPane.showMessageDialog(null, "This meter number already exists");
                    return;
                }

                PreparedStatement checkUsernamePs = c1.c.prepareStatement(checkUsernameQuery);
                checkUsernamePs.setString(1, username);
                ResultSet usernameRs = checkUsernamePs.executeQuery();

                if (usernameRs.next()) {
                    JOptionPane.showMessageDialog(null, "This username already exists");
                    return;
                }

                PreparedStatement customerPs = c1.c.prepareStatement(customerQuery);
                customerPs.setString(1, name);
                customerPs.setString(2, meter);
                customerPs.setString(3, address);
                customerPs.setString(4, city);
                customerPs.setString(5, state);
                customerPs.setString(6, email);
                customerPs.setString(7, phone);
                customerPs.executeUpdate();

                PreparedStatement loginPs = c1.c.prepareStatement(loginQuery);
                loginPs.setString(1, meter);
                loginPs.setString(2, username);
                loginPs.setString(3, name);
                loginPs.setString(4, password);
                loginPs.setString(5, "Customer");
                loginPs.executeUpdate();

                JOptionPane.showMessageDialog(null, "Customer Account Created Successfully");
                this.setVisible(false);
                new MeterInfo(meter).setVisible(true);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Customer could not be added");
            }

        } else if (ae.getSource() == b2) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NewCustomer().setVisible(true);
    }
}