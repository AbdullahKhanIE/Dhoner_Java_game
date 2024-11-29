package com.example.gameFucked;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class PlayerSquare {

    private double x; // X-coordinate of the square
    private double y; // Y-coordinate of the square
    private final double size; // Size of the square
    private final double speed; // Movement speed of the square

    // Flags for movement
    private boolean moveUp = false;
    private boolean moveDown = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;

    public PlayerSquare(double startX, double startY, double size, double speed) {
        this.x = startX;
        this.y = startY;
        this.size = size;
        this.speed = speed;
    }

    public double getY() {
        return y;
    }
    public double getX() {
        return x;
    }
    public double getSize() {
        return size;
    }

    public void handleKeyPress(KeyCode code){
        switch (code) {
            case W:
                moveUp = true;
                break;
            case A:
                moveLeft = true;
                break;
            case D:
                moveRight = true;
                break;
            case S:
                moveDown = true;
                break;
        }
    }

    public void handleKeyRelease(KeyCode code){
        switch (code) {
            case W:
                moveUp = false;
                break;
            case A:
                moveLeft = false;
                break;
            case D:
                moveRight = false;
                break;
            case S:
                moveDown = false;
                break;
        }
    }

    public void update(double BoxWidth, double BoxHeight) {
        if (moveUp) {
            y -= speed;
        }
        if (moveDown){
            y += speed;
        }
        if (moveLeft){
            x -= speed;
        }
        if (moveRight){
            x += speed;
        }

        // Boundary checks to prevent the square from leaving the canvas
        x = Math.max(0, Math.min(x, BoxWidth - size));
        y = Math.max(0, Math.min(y, BoxHeight - size));
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLANCHEDALMOND); // Set square color
        gc.fillRect(x, y, size, size); // Draw the square
    }

}
