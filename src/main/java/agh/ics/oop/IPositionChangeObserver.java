package agh.ics.oop;

import org.jetbrains.annotations.NotNull;

public interface IPositionChangeObserver extends Comparable<IPositionChangeObserver> {
    /**
     * Change object position in datasets.
     *
     * @param oldPosition oldPosition
     * @param newPosition newPosition
     */
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
    @Override
    default int compareTo(@NotNull IPositionChangeObserver o) {
        return this.hashCode() - o.hashCode();
    }
}
