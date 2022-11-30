package agh.ics.oop;

import org.jetbrains.annotations.NotNull;

public interface IPositionChangeObserver extends Comparable {
    /**
     * Change object position in datasets.
     *
     * @param oldPosition oldPosition
     * @param newPosition newPosition
     */
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);
    @Override
    default int compareTo(@NotNull Object o) {
        return this.hashCode() - o.hashCode();
    }
}
