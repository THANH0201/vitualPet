package model;

import utils.Direction;
import view.PetView;

import static view.PetView.CELL_SIZE;

public class PetModel {
    private double pixelX;
    private double pixelY;
    private static final int GRID_SIZE = 10;

    public PetModel(int gridX, int gridY) {
        this.pixelX = gridX * PetView.CELL_SIZE;
        this.pixelY = gridY * PetView.CELL_SIZE;
    }

    public double getPixelX() {
        return pixelX;
    }

    public double getPixelY() {
        return pixelY;
    }

    public void moveByPixels(double dx, double dy) {
        this.pixelX += dx;
        this.pixelY += dy;
    }

    public static int getGridSize() {
        return GRID_SIZE;
    }

    public void setPixelPosition(double targetX, double targetY) {
        this.pixelX = targetX;
        this.pixelY = targetY;
    }
    public int getX() {
        return (int)(pixelX / CELL_SIZE); //grid
    }

    public int getY() {
        return (int)(pixelY / CELL_SIZE);
    }
}
