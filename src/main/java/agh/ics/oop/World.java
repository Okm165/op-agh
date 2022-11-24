package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

// libka guawa lub apache commons

public class World {
    public static void main(String[] args) {
        try {
            Application.launch(App.class, args);
        } catch(IllegalArgumentException err) {
            System.out.println(err);
        }
    }
}
