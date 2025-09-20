package controller;

import javafx.animation.AnimationTimer;
import model.PetModel;
import utils.Direction;
import view.PetView;

public class PetController {
    private PetModel player;
    private PetView gui;
    private double targetX = -1;
    private double targetY = -1;
    private boolean isMoving = false;


    public PetController(PetView gui) {
        this.player = new PetModel(0, 0);
        this.gui = gui;

    }

    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (targetX < 0 || targetY < 0 ) return;
            moveStepTowardTarget();
        }
    };

    public void movePetToward(double mouseX, double mouseY) {
        int canvasSize = getGridSize() * PetView.CELL_SIZE;

        targetX = Math.max(0, Math.min(mouseX, canvasSize - PetView.CELL_SIZE));
        targetY = Math.max(0, Math.min(mouseY, canvasSize - PetView.CELL_SIZE));

        if (!isMoving) {
            isMoving = true;
            timer.start();
        }
    }

    private void moveStepTowardTarget() {
        double petX = player.getPixelX();
        double petY = player.getPixelY();

        double dx = targetX - petX;
        double dy = targetY - petY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance <=1) {
            player.setPixelPosition(targetX, targetY);
            gui.updateCanvas(player.getPixelX(), player.getPixelY());
            timer.stop();
            isMoving = false;
            return;
        }

        double step = 5;//speed
        double moveX = (dx / distance) * step;
        double moveY = (dy / distance) * step;

        player.moveByPixels(moveX, moveY);
        gui.updateCanvas(player.getPixelX(), player.getPixelY());
    }
    public void stopMoving() {
        timer.stop();
        isMoving = false;
        targetX = -1;
        targetY = -1;
    }


    public double getPlayerX() {
        return player.getPixelX();
    }

    public double getPlayerY() {
        return player.getPixelY();
    }

    public int getGridSize() {
        return player.getGridSize();
    }
}
