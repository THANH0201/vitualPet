package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import controller.PetController;

import java.util.Objects;

public class PetView extends Application {
    public static final int CELL_SIZE = 50;
    private Canvas canvas;
    private GraphicsContext gc;
    private PetController controller;
    private Image petImage;

    @Override
    public void init() {
        this.controller = new PetController(this);
    }

    @Override
    public void start(Stage primaryStage) {
        //this.petImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/view/dino.png")));
        this.petImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("dino.png")));
        int gridSize = controller.getGridSize();
        int canvasSize = gridSize * CELL_SIZE;
        canvas = new Canvas(canvasSize, canvasSize);
        gc = canvas.getGraphicsContext2D();

        //mouse move
        canvas.setOnMouseClicked(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            controller.movePetToward(mouseX, mouseY);
        });
        canvas.setOnMouseExited(event -> {
            controller.stopMoving();
        });

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, canvasSize, canvasSize);
        primaryStage.setTitle("Virtual Pet");
        primaryStage.setScene(scene);
        primaryStage.show();
        updateCanvas(controller.getPlayerX(), controller.getPlayerY());
    }
    public void updateCanvas(double playerX, double playerY) {
        int gridSize = controller.getGridSize();
        int canvasSize = gridSize * CELL_SIZE;
        gc.clearRect(0, 0, canvasSize, canvasSize);
        gc.drawImage(petImage, playerX, playerY, CELL_SIZE, CELL_SIZE);
    }

}