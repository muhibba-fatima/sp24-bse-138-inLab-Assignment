package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;

public class LoginApp extends Application {

    private HashMap<String, String> userCredentials = new HashMap<>();
    private static final String CREDENTIALS_FILE = "login_credentials.txt";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        loadCredentialsFromFile();
        addSampleCredentialsToFile();
        showLoginForm(primaryStage);
    }

    private void showLoginForm(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        ImageView imageView = new ImageView(new Image("file:C:/Users/Admin/Downloads/Login.jpg"));
        imageView.setFitHeight(110);
        imageView.setFitWidth(200);
        grid.add(imageView, 0, 0, 2, 1);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);
        
        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red;");
        grid.add(errorLabel, 1, 4);

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                Stage newStage = new Stage();
                showWelcomePage(newStage, username);
            } else {
                errorLabel.setText("Incorrect username or password.");
            }
        });

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> System.exit(0));

        grid.add(loginButton, 0, 3);
        grid.add(exitButton, 1, 3);

        Scene scene = new Scene(grid, 400, 300);
        stage.setTitle("Login Form");
        stage.setScene(scene);
        stage.show();
    }

    private void loadCredentialsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CREDENTIALS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userCredentials.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addSampleCredentialsToFile() {
        File credentialsFile = new File(CREDENTIALS_FILE);

        if (!credentialsFile.exists()) {
            try {
                credentialsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(credentialsFile))) {
            if (reader.readLine() == null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(credentialsFile, true))) {
                    writer.write("user1,password1\n");
                    writer.write("user2,password2\n");
                    writer.write("user3,password3\n");
                    writer.write("user4,password4\n");
                    writer.write("user5,password5\n");
                    writer.write("user6,password6\n");
                    writer.write("user7,password7\n");
                    writer.write("user8,password8\n");
                    writer.write("user9,password9\n");
                    writer.write("user10,password10\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showWelcomePage(Stage stage, String username) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        Label welcomeLabel = new Label("Welcome, " + username + "!");
        grid.add(welcomeLabel, 0, 0);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> System.exit(0));

        grid.add(exitButton, 0, 1);
        Scene scene = new Scene(grid, 300, 200);
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();
    }
}


