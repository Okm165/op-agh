package agh.ics.oop;

public interface IMapElement {
    /**
     * Get position of map element.
     *
     * @return Vector2d position.
     */
    Vector2d getPosition();
    /**
     * Check if map element is at position.
     *
     * @param position The position to be checked.
     * @return True if the object is at position False otherwise.
     */
    boolean isAt(Vector2d position);
    /**
     * Return string representation of a map element.
     *
     * @return String representation.
     */
    String toString();
}
