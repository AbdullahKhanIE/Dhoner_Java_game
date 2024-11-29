package com.example.gameFucked;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import static com.example.gameFucked.MainGame.PointCounter;

public class RandomShape {

    private double Shapex; // X-coordinate of the square
    private double Shapey; // Y-coordinate of the square
    private final double size = 30 + (Math.random() * (80 - 30 + 1)); // Size of the square


    public RandomShape (double startX, double startY) {
        this.Shapex = startX;
        this.Shapey = startY;
    }

    public void update(PlayerSquare P) {
        if (P.getX() < Shapex + size && P.getX() + P.getSize() > Shapex &&
                P.getY() < Shapey + size && P.getY() + P.getSize() > Shapey) {
            // If collision happens, increment the points
            PointCounter--;
            System.out.println(PointCounter);
        }
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUEVIOLET); // Set square color
        gc.fillRect(Shapex, Shapey, size, size); // Draw the square
    }
}
