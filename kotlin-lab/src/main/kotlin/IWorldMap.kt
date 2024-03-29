/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 */
interface IWorldMap {
    /**
     * Indicate if any object can move to the given position.
     *
     * @param position The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
    fun canMoveTo(position: Vector2d): Boolean

    /**
     * Place an animal on the map.
     *
     * @param animal The animal to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the map is already occupied.
     */
    fun place(animal: Animal, position: Vector2d)

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    fun isOccupied(position: Vector2d): Boolean

    /**
     * Return an object at a given position.
     *
     * @param position The position of the object.
     * @return Object or null if the position is not occupied.
     */
    fun objectAt(position: Vector2d): Animal?
}