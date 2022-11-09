package agh.ics.oop;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    @Override
    public String toString() {
        return switch (this) {
            case NORTH -> "NORTH";
            case SOUTH -> "SOUTH";
            case WEST -> "WEST";
            case EAST -> "EAST";
        };
    }

    public MapDirection next() {
        return switch (this) {
            case NORTH -> MapDirection.EAST;
            case EAST -> MapDirection.SOUTH;
            case SOUTH -> MapDirection.WEST;
            case WEST -> MapDirection.NORTH;
        };
    }

    public MapDirection previous() {
        return switch (this) {
            case NORTH -> MapDirection.WEST;
            case WEST -> MapDirection.SOUTH;
            case SOUTH -> MapDirection.EAST;
            case EAST -> MapDirection.NORTH;
        };
    }

    public Vector2d toUnitVector () {
        return switch (this) {
            case NORTH -> new Vector2d(0,1);
            case EAST -> new Vector2d(1,0);
            case SOUTH -> new Vector2d(0,-1);
            case WEST -> new Vector2d(-1,0);
        };
    }
}
