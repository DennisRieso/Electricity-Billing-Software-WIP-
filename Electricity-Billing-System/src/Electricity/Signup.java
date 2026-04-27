package Electricity;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Signup extends JFrame implements ActionListener {
    JPanel p1;

    JTextField usernameField, nameField, meterField, addressField, cityField, stateField, emailField, phoneField;
    JPasswordField passwordField;

    JLabel meterLabel, addressLabel, cityLabel, stateLabel, emailLabel, phoneLabel, accountTypeLabel;

    JButton createButton, backButton;

    String accountType;

    Signup() {
        this("Customer");
    }

    Signup(String accountType) {
        this.accountType = accountType;

        setBounds(600, 200, 760, 560);

        p1 = new JPanel();
        p1.setBounds(30, 30, 700, 480);
        p1.setLayout(null);
        p1.setBackground(Color.WHITE);
        p1.setForeground(new Color(34, 139, 34));
        p1.setBorder(new TitledBorder(
                new LineBorder(new Color(173, 216, 230), 2),
                "Create Account",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                null,
                new Color(173, 216, 230)
        ));
        add(p1);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.DARK_GRAY);
        usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        usernameLabel.setBounds(80, 50, 120, 20);
        p1.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(230, 50, 170, 20);
        p1.add(usernameField);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setForeground(Color.DARK_GRAY);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        nameLabel.setBounds(80, 90, 120, 20);
        p1.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(230, 90, 170, 20);
        p1.add(nameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.DARK_GRAY);
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        passwordLabel.setBounds(80, 130, 120, 20);
        p1.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(230, 130, 170, 20);
        p1.add(passwordField);

        JLabel accountLabel = new JLabel("Account Type");
        accountLabel.setForeground(Color.DARK_GRAY);
        accountLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        accountLabel.setBounds(80, 170, 140, 20);
        p1.add(accountLabel);

        accountTypeLabel = new JLabel(accountType);
        accountTypeLabel.setForeground(Color.DARK_GRAY);
        accountTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        accountTypeLabel.setBounds(230, 170, 170, 20);
        p1.add(accountTypeLabel);

        meterLabel = new JLabel("Meter Number");
        meterLabel.setForeground(Color.DARK_GRAY);
        meterLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        meterLabel.setBounds(80, 210, 140, 20);
        p1.add(meterLabel);

        meterField = new JTextField();
        meterField.setBounds(230, 210, 170, 20);
        p1.add(meterField);

        addressLabel = new JLabel("Address");
        addressLabel.setForeground(Color.DARK_GRAY);
        addressLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        addressLabel.setBounds(80, 250, 140, 20);
        p1.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(230, 250, 170, 20);
        p1.add(addressField);

        cityLabel = new JLabel("City");
        cityLabel.setForeground(Color.DARK_GRAY);
        cityLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        cityLabel.setBounds(80, 290, 140, 20);
        p1.add(cityLabel);

        cityField = new JTextField();
        cityField.setBounds(230, 290, 170, 20);
        p1.add(cityField);

        stateLabel = new JLabel("State");
        stateLabel.setForeground(Color.DARK_GRAY);
        stateLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        stateLabel.setBounds(80, 330, 140, 20);
        p1.add(stateLabel);

        stateField = new JTextField();
        stateField.setBounds(230, 330, 170, 20);
        p1.add(stateField);

        emailLabel = new JLabel("Email");
        emailLabel.setForeground(Color.DARK_GRAY);
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        emailLabel.setBounds(80, 370, 140, 20);
        p1.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(230, 370, 170, 20);
        p1.add(emailField);

        phoneLabel = new JLabel("Phone");
        phoneLabel.setForeground(Color.DARK_GRAY);
        phoneLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        phoneLabel.setBounds(80, 410, 140, 20);
        p1.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(230, 410, 170, 20);
        p1.add(phoneField);

        if (accountType.equals("Admin")) {
            meterLabel.setVisible(false);
            meterField.setVisible(false);

            addressLabel.setVisible(false);
            addressField.setVisible(false);

            cityLabel.setVisible(false);
            cityField.setVisible(false);

            stateLabel.setVisible(false);
            stateField.setVisible(false);

            emailLabel.setVisible(false);
            emailField.setVisible(false);

            phoneLabel.setVisible(false);
            phoneField.setVisible(false);
        }

        createButton = new JButton("Create");
        createButton.setBackground(Color.BLACK);
        createButton.setForeground(Color.WHITE);
        createButton.setBounds(120, 455, 120, 30);
        createButton.addActionListener(this);
        p1.add(createButton);

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(270, 455, 120, 30);
        backButton.addActionListener(this);
        p1.add(backButton);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i2 = i1.getImage().getScaledInstance(240, 240, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        imageLabel.setBounds(430, 90, 240, 240);
        p1.add(imageLabel);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == createButton) {
            String username = usernameField.getText().trim();
            String name = nameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String user = accountType;

            if (username.isEmpty() || name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in username, name and password");
                return;
            }

            try {
                Conn c = new Conn();

                if (c.c == null) {
                    JOptionPane.showMessageDialog(null, "Database connection failed");
                    return;
                }

                String checkUsernameQuery = "select * from login where username = ?";
                PreparedStatement checkUsernamePs = c.c.prepareStatement(checkUsernameQuery);
                checkUsernamePs.setString(1, username);
                ResultSet usernameRs = checkUsernamePs.executeQuery();

                if (usernameRs.next()) {
                    JOptionPane.showMessageDialog(null, "This username already exists");
                    return;
                }

                if (user.equals("Customer")) {
                    String meter = meterField.getText().trim();
                    String address = addressField.getText().trim();
                    String city = cityField.getText().trim();
                    String state = stateField.getText().trim();
                    String email = emailField.getText().trim();
                    String phone = phoneField.getText().trim();

                    if (meter.isEmpty() || address.isEmpty() || city.isEmpty()
                            || state.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all customer fields");
                        return;
                    }

                    String checkMeterQuery = "select * from customer where meter = ?";
                    PreparedStatement checkMeterPs = c.c.prepareStatement(checkMeterQuery);
                    checkMeterPs.setString(1, meter);
                    ResultSet meterRs = checkMeterPs.executeQuery();

                    if (meterRs.next()) {
                        JOptionPane.showMessageDialog(null, "This meter number already exists");
                        return;
                    }

                    String customerQuery = "insert into customer values (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement customerPs = c.c.prepareStatement(customerQuery);
                    customerPs.setString(1, name);
                    customerPs.setString(2, meter);
                    customerPs.setString(3, address);
                    customerPs.setString(4, city);
                    customerPs.setString(5, state);
                    customerPs.setString(6, email);
                    customerPs.setString(7, phone);
                    customerPs.executeUpdate();

                    String loginQuery = "insert into login (meter_no, username, name, password, user) values (?, ?, ?, ?, ?)";
                    PreparedStatement loginPs = c.c.prepareStatement(loginQuery);
                    loginPs.setString(1, meter);
                    loginPs.setString(2, username);
                    loginPs.setString(3, name);
                    loginPs.setString(4, password);
                    loginPs.setString(5, "Customer");
                    loginPs.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Customer Account Created Successfully");
                    this.setVisible(false);
                    new Login().setVisible(true);

                } else if (user.equals("Admin")) {
                    String query = "insert into login (meter_no, username, name, password, user) values (?, ?, ?, ?, ?)";

                    PreparedStatement ps = c.c.prepareStatement(query);
                    ps.setString(1, "");
                    ps.setString(2, username);
                    ps.setString(3, name);
                    ps.setString(4, password);
                    ps.setString(5, "Admin");
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Admin Account Created Successfully");
                    this.setVisible(false);
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Account could not be created");
            }

        } else if (ae.getSource() == backButton) {
            this.setVisible(false);

            if (accountType.equals("Customer")) {
                new Login().setVisible(true);
            }
        }
    }

    public static void main(String[] args) {
        new Signup("Customer").setVisible(true);
    }
}