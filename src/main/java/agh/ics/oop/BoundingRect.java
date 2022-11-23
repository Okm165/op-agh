package agh.ics.oop;

public class BoundingRect {
    public Vector2d lowerLeftCorner;
    public Vector2d upperRightCorner;
    public BoundingRect(Vector2d lowerLeft, Vector2d upperRight){
        this.lowerLeftCorner = lowerLeft;
        this.upperRightCorner = upperRight;
    }
}
