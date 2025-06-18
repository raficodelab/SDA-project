package simpleloginapp;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;

public class SimpleLoginApp {
    private static HashMap<String, String> userDatabase = new HashMap<>();
    private static final String FILE_NAME = "users.txt";

    public static void main(String[] args) {
        loadUsers();

        JFrame frame = new JFrame("Login App");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        frame.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(100, 20, 165, 25);
        frame.add(userText);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(10, 50, 80, 25);
        frame.add(passLabel);

        JPasswordField passText = new JPasswordField();
        passText.setBounds(100, 50, 165, 25);
        frame.add(passText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 90, 80, 25);
        frame.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(180, 90, 100, 25);
        frame.add(registerButton);

        JLabel message = new JLabel("");
        message.setBounds(10, 120, 250, 25);
        frame.add(message);

        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = String.valueOf(passText.getPassword());
            if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
                message.setText("Login successful!");
            } else {
                message.setText("Invalid login!");
            }
        });

        registerButton.addActionListener(e -> {
            String username = userText.getText();
            String password = String.valueOf(passText.getPassword());
            if (!userDatabase.containsKey(username)) {
                userDatabase.put(username, password);
                saveUser(username, password);
                message.setText("Registered successfully!");
            } else {
                message.setText("Username already exists.");
            }
        });

        frame.setVisible(true);
    }

    private static void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userDatabase.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("User file not found. Starting fresh.");
        }
    }

    private static void saveUser(String username, String password) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(username + "," + password + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to user file.");
        }
    }
}
