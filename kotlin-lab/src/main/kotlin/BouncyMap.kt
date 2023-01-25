class BouncyMap(private val width: Int, private val height: Int): IWorldMap {
    private val animals: HashMap<Vector2d, Animal> = HashMap()

    override fun canMoveTo(position: Vector2d): Boolean {
        return position.follows(Vector2d(0,0)) && position.precedes(Vector2d(width, height))
    }

    override fun place(animal: Animal, position: Vector2d) {
        if (isOccupied(position)) {
            var newPos = animals.randomFreePostion(Vector2d(width, height))
            if (newPos != null) {
                animal.position = newPos
                animals[newPos] = animal

            } else {
                newPos = animals.randomPosition()
                if (newPos != null) {
                    animal.position = newPos
                    animals[newPos] = animal
                }
            }

        } else {
            animal.position = position
            animals[position] = animal
        }
    }

    override fun isOccupied(position: Vector2d): Boolean {
        return objectAt(position) != null
    }

    override fun objectAt(position: Vector2d): Animal? {
        return animals[position]
    }
}