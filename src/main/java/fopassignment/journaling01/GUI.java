package fopassignment.journaling01;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
    
    // 1. Create the "Digital Pipe" writer
    private PipedOutputStream commandInputWriter; 

    @Override
    public void start(Stage primaryStage) {
        
        setupStreams(); // Setup the piping before showing UI

        // --- UI SETUP (Same as before) ---
        Label titleLabel = new Label("XXX JOURNALING");
        titleLabel.setStyle("-fx-text-fill: white;");
        titleLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        
        consoleOutput = new TextArea();
        consoleOutput.setEditable(false);
        consoleOutput.setWrapText(true);
        consoleOutput.setStyle("-fx-control-inner-background: black; -fx-text-fill: #00FF00; -fx-font-family: 'Courier New'; -fx-font-size: 14px;");
        consoleOutput.setPrefHeight(400);

        inputField = new TextField();
        inputField.setPromptText("Type here...");
        inputField.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-font-family: 'Courier New';");
        
        // --- KEY LOGIC CHANGE ---
        inputField.setOnAction(e -> {
            String command = inputField.getText();
            inputField.clear();
            
            // Echo the command to screen so user sees what they typed
            consoleOutput.appendText("> " + command + "\n");
            
            // Send the text into the Pipe (feeding the Scanner)
            sendToScanner(command); 
        });

        VBox centerLayout = new VBox(10);
        centerLayout.getChildren().addAll(titleLabel, consoleOutput, inputField);
        centerLayout.setPadding(new Insets(20));
        centerLayout.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(centerLayout);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #000000, #434343);");

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("XXX Journaling System");
        primaryStage.setScene(scene);
        // Force kill all threads (including the background scanner) when window closes
        primaryStage.setOnCloseRequest(event -> {
        System.exit(0); // This forces the JVM to stop completely
        });
        primaryStage.show();

        // --- START LEGACY APP IN NEW THREAD ---
        // Crucial: Must be in a new Thread or the GUI freezes!
        new Thread(() -> {
            try {
                Main.main(new String[]{});
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // 2. Setup System.in and System.out redirection
    private void setupStreams() {
        // Output Redirect (Console -> GUI)
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) {
                Platform.runLater(() -> consoleOutput.appendText(String.valueOf((char) b)));
            }
            @Override
            public void write(byte[] b, int off, int len) {
                String str = new String(b, off, len);
                Platform.runLater(() -> consoleOutput.appendText(str));
            }
        };
        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));

        // Input Redirect (GUI -> System.in)
        try {
            commandInputWriter = new PipedOutputStream();
            PipedInputStream in = new PipedInputStream(commandInputWriter);
            System.setIn(in); // Trick System.in to read from our pipe
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 3. Helper to write text to the Pipe
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