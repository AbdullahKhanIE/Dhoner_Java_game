package com.example.gameFucked;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

import javax.swing.*;
import java.util.ArrayList;


public class MainGame extends Application {
    public static int PointCounter = 100;
    double screenWidth = javafx.stage.Screen.getPrimary().getBounds().getWidth()-5;
    double screenHeight = javafx.stage.Screen.getPrimary().getBounds().getHeight()-10;
    private PlayerSquare player;
    private ArrayList<RandomShape> randomShapes;


    @Override
    public void start(Stage stage) {
        stage.setTitle("Simple RECT Game");

        // Create root group and canvas
        Group root = new Group();
        Scene scene = new Scene(root, screenWidth, screenHeight); // Scene size
        stage.setScene(scene);
        stage.setResizable(false);

        // Create a canvas to draw the game
        Canvas canvas = new Canvas(screenWidth, screenHeight); // Canvas size
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.fillRect(0, 0, screenWidth, screenHeight);
        root.getChildren().add(canvas);

        player = new PlayerSquare(50, 50, 50, 5);
        randomShapes = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            randomShapes.add(new RandomShape(Math.random() * (screenWidth - 15), Math.random() * (screenHeight - 15)));
        }

        scene.setOnKeyPressed(event -> player.handleKeyPress(event.getCode()));
        scene.setOnKeyReleased(event -> player.handleKeyRelease(event.getCode()));

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Update game state
                player.update(canvas.getWidth(), canvas.getHeight());

                // Stop the game if PointCounter is 0 or less
                if (PointCounter <= 0) {
                    stop();  // Stops the game loop
                    gc.setFill(Color.WHITE);
                    gc.setFont(Font.font(48)); // Larger font for game over message
                    gc.setTextAlign(TextAlignment.CENTER);
                    gc.fillText("GAME OVER", canvas.getWidth() / 2, canvas.getHeight() / 2);
                } else {
                    // Render everything
                    render(gc, canvas.getWidth(), canvas.getHeight());
                }
            }
        };
        gameLoop.start(); // Start the game loop

        stage.show();
    }

    private void render(GraphicsContext gc, double canvasWidth, double canvasHeight) {
        // Clear the canvas with red
        gc.setFill(Color.RED);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
        // Render the player square
        player.render(gc);
        for (RandomShape shape : randomShapes) {
            shape.render(gc);
            shape.update(player);
        }
        // Display the PointCounter
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(24)); // Set font size for score
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("Points: " + PointCounter, 10, 30); // Display points in the top-left corner

    }
    public static void main(String[] args) {
        launch(args);
    }
}
