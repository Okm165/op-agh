import kotlin.math.max
import kotlin.math.min

class Vector2d(var x: Int, var y: Int) {

    operator fun plus(other: Vector2d): Vector2d {
        return Vector2d(this.x + other.x, this.y + other.y)
    }

    operator fun minus(other: Vector2d): Vector2d {
        return Vector2d(this.x - other.x, this.y - other.y)
    }

    operator fun plusAssign(other: Vector2d) {
        this.x += other.x
        this.y += other.y
    }

    operator fun minusAssign(other: Vector2d) {
        this.x -= other.x
        this.y -= other.y
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as Vector2d
        if (x != other.x || y != other.y) return false
        return true
    }
    override fun toString(): String {
        return "[$x, $y]"
    }

    fun precedes(other: Vector2d): Boolean {
        return x <= other.x && y <= other.y
    }

    fun follows(other: Vector2d): Boolean {
        return x >= other.x && y >= other.y
    }

    fun upperRight(other: Vector2d): Vector2d {
        return Vector2d(max(x, other.x), max(y, other.y))
    }

    fun lowerLeft(other: Vector2d): Vector2d {
        return Vector2d(min(x, other.x), min(y, other.y))
    }

    fun unaryMinus(): Vector2d {
        return Vector2d(Math.negateExact(x), Math.negateExact(y))
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}

fun MapDirection.toUnitVector(): Vector2d {
    return when (this) {
        MapDirection.NORTH -> Vector2d(0, 1)
        MapDirection.EAST -> Vector2d(1, 0)
        MapDirection.SOUTH -> Vector2d(0, -1)
        MapDirection.WEST -> Vector2d(-1, 0)
    }
}