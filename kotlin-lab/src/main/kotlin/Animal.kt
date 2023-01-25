class Animal (
    position: Vector2d,
    orientation: MapDirection = MapDirection.NORTH,
    private val map: IWorldMap
) {
    var position: Vector2d = position

    private var orientation: MapDirection = orientation
        get() = field


    fun isAt(position: Vector2d): Boolean {
        return this.position == position
    }

    override fun toString(): String {
        return when (orientation) {
            MapDirection.NORTH -> "▲"
            MapDirection.EAST -> "►"
            MapDirection.SOUTH -> "▼"
            MapDirection.WEST -> "◄"
        }
    }

    fun move(direction: MoveDirection) {
        var newPos: Vector2d = position
        var newOrientation = orientation
        when (direction) {
            MoveDirection.RIGHT -> newOrientation = orientation.next()
            MoveDirection.LEFT -> newOrientation = orientation.previous()
            MoveDirection.FORWARD -> newPos = position + orientation.toUnitVector()
            MoveDirection.BACKWARD -> newPos = position - orientation.toUnitVector()
        }
        if (newPos != position) {
            map.place(this, newPos)
        }
        if (orientation != newOrientation) {
            orientation = newOrientation
        }
    }
}