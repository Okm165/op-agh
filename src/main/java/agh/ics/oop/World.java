package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        Animal animal = new Animal();
        MoveDirection[] directions = OptionsParser.parse(args);
        for (MoveDirection move : directions){
            animal.move(move);
        }
        System.out.println(animal.toString());
    }
}
