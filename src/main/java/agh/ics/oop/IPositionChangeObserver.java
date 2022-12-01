package agh.ics.oop;

import org.jetbrains.annotations.NotNull;

public interface IPositionChangeObserver extends Comparable<IPositionChangeObserver> {
    /**
     * Change object position in datasets.
     *
     * @param oldPosition oldPosition
     * @param newPosition newPosition
     */
    default void positionChanged(Vector2d oldPosition, Vector2d newPosition) {}

    default void orientationChanged(MapDirection oldOrientation, MapDirection newOrientation) {}

    @Override
    default int compareTo(@NotNull IPositionChangeObserver o) {
        return this.hashCode() - o.hashCode();
    }
}
