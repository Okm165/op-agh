fun Map<Vector2d, Any>.randomPosition(): Vector2d? {
    return this.toList().randomOrNull()?.first
}

fun Map<Vector2d, Any>.randomFreePostion(mapSize: Vector2d): Vector2d? {
    val xs = (0..mapSize.x).flatMap { value -> (0..mapSize.y).map { value } }.toList()
    val ys = (0..mapSize.x).flatMap { (0..mapSize.y).map { it } }.toList()
    val points = xs.zip(ys).map { Vector2d(it.first, it.second) }
    return points.find { !this.containsKey(it) }
}