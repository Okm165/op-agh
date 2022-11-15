package agh.ics.oop;

public interface IPositionChangeObserver {
    /**
     * Change object position in datasets.
     *
     * @param oldPosition oldPosition
     * @param newPosition newPosition
     */
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
}
