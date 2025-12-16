package fopassignment.journaling01;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

public class GUI extends Application {

    private TextArea consoleOutput;
    private TextField inputField;
    private PipedOutputStream commandInputWriter; 

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("XXX Journaling System");

        //Title Page 
        Label mainTitle = new Label("XXX JOURNALING");
        mainTitle.setStyle("-fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, white, 10, 0, 0, 0);");
        mainTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 36));

        Label subTitle = new Label("Welcome to your personal journal system.");
        subTitle.setStyle("-fx-text-fill: #AAAAAA;");
        subTitle.setFont(Font.font("Georgia", 14));

        Button startButton = new Button("START SYSTEM");
        startButton.setStyle("-fx-background-color: #white; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16px; -fx-cursor: hand;");
        startButton.setPadding(new Insets(10, 30, 10, 30));

        //Switch to Main Page
        startButton.setOnAction(e -> {
            showMainJournal(primaryStage);
        });

        VBox titleLayout = new VBox(20); 
        titleLayout.getChildren().addAll(mainTitle, subTitle, startButton);
        titleLayout.setAlignment(Pos.CENTER);
        titleLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #000000, #222222);");

        Scene titleScene = new Scene(titleLayout, 600, 500);
        primaryStage.setScene(titleScene);

        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }

    private void showMainJournal(Stage stage) {
        
        //Setup the Console Output Area
        consoleOutput = new TextArea();
        consoleOutput.setEditable(false);
        consoleOutput.setWrapText(true);
        consoleOutput.setStyle("-fx-control-inner-background: black; -fx-text-fill: #00FF00; -fx-font-family: 'Courier New'; -fx-font-size: 14px;");
        consoleOutput.setPrefHeight(400);

        //Setup Input Field
        inputField = new TextField();
        inputField.setPromptText("Type here...");
        inputField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-family: 'Courier New';");

        //Setup Enter Button
        Button enterButton = new Button("ENTER");
        enterButton.setStyle("-fx-background-color: #555; -fx-text-fill: white; -fx-font-family: 'Courier New'; -fx-font-weight: bold;");
        enterButton.setMinWidth(80);

        //Logic for sending commands
        Runnable handleInput = () -> {
            String command = inputField.getText();
            inputField.clear();
            consoleOutput.appendText("> " + command + "\n");
            sendToScanner(command); 
        };

        inputField.setOnAction(e -> handleInput.run());
        enterButton.setOnAction(e -> handleInput.run());

        //Layout (HBox for Input, VBox for everything)
        HBox inputContainer = new HBox(10);
        inputContainer.getChildren().addAll(inputField, enterButton);
        inputContainer.setAlignment(Pos.CENTER);
        HBox.setHgrow(inputField, Priority.ALWAYS);

        Label headerLabel = new Label("XXX JOURNALING");
        headerLabel.setStyle("-fx-text-fill: white;");
        headerLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 24));

        VBox centerLayout = new VBox(10);
        centerLayout.getChildren().addAll(headerLabel, consoleOutput, inputContainer);
        centerLayout.setPadding(new Insets(20));
        centerLayout.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(centerLayout);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #000000, #434343);");

        Scene mainScene = new Scene(root, 600, 500);
        stage.setScene(mainScene);

        setupStreams();
        startLegacyApp();
    }

    private void startLegacyApp() {
        new Thread(() -> {
            try {
                Main.main(new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void setupStreams() {
        //Output Redirect
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) {
                Platform.runLater(() -> {
                    if (consoleOutput != null) consoleOutput.appendText(String.valueOf((char) b));
                });
            }
            @Override
            public void write(byte[] b, int off, int len) {
                String str = new String(b, off, len);
                Platform.runLater(() -> {
                    if (consoleOutput != null) consoleOutput.appendText(str);
                });
            }
        };
        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));

        //Input Redirect
        try {
            commandInputWriter = new PipedOutputStream();
            PipedInputStream in = new PipedInputStream(commandInputWriter);
            System.setIn(in); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendToScanner(String text) {
        try {
            if (commandInputWriter != null) {
                commandInputWriter.write((text + "\n").getBytes());
                commandInputWriter.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}