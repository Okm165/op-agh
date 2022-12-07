package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class App extends Application implements IPositionChangeObserver {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final int FONTSIZE = 20;
    private int numOfCols;
    private int numOfRows;
    private final GridPane gridPane = new GridPane();
    public final HashMap<Integer, GuiElementBox> cachedResources = new HashMap<>();
    private AbstractWorldMap map;
    private SimulationEngine engine;

    public void init() {
//        MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
        this.map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 2)};
        Animal[] animals = Arrays.stream(positions).map(pos -> new Animal(map, pos)).toArray(Animal[]::new);
        this.engine = new SimulationEngine(this.map, animals);
        Arrays.stream(animals).forEach(animal -> animal.addObserver(this));
        this.renderMap();
    }

    private void initGridPane(){
        addLabel("y/x", 0,0);
        IntStream.rangeClosed(this.map.boundingRect().lowerLeft().x, this.map.boundingRect().upperRight().x)
                .forEach(i -> addLabel(String.valueOf(i), convToCol(i), 0));
        IntStream.rangeClosed(this.map.boundingRect().lowerLeft().y, this.map.boundingRect().upperRight().y)
                .forEach(i -> addLabel(String.valueOf(i), 0, convToRow(i)));
    }

    private void addLabel(String text, int col, int row) {
        Label label = new Label(text);
        this.gridPane.add(label, col, row);
        GridPane.setHalignment(label, HPos.CENTER);
        label.setFont(new Font(FONTSIZE));
    }

    public int convToCol(int x) {
        return x-this.map.boundingRect().lowerLeft().x+1;
    }

    public int convToRow(int y) {
        return this.map.boundingRect().upperRight().y-y+1;
    }

    public Vector2d convCoordinates(Vector2d pos) {
        return new Vector2d(convToCol(pos.x), convToRow(pos.y));
    }

    public void updateColRowDimensions() {
        int numOfCols = this.map.boundingRect().upperRight().x-this.map.boundingRect().lowerLeft().x+2;
        int numOfRows = this.map.boundingRect().upperRight().y-this.map.boundingRect().lowerLeft().y+2;
        if (numOfCols != this.numOfCols) {
            this.numOfCols = numOfCols;
            int colWidth = WIDTH/numOfCols;
            this.gridPane.getColumnConstraints().clear();
            IntStream.range(0, numOfCols).forEach(i -> this.gridPane.getColumnConstraints().add(new ColumnConstraints(colWidth)));
        }
        if (numOfRows != this.numOfRows) {
            this.numOfRows = numOfRows;
            int rowHeight = (HEIGHT-2*FONTSIZE)/numOfRows;
            this.gridPane.getRowConstraints().clear();
            IntStream.range(0, numOfRows).forEach(i -> this.gridPane.getRowConstraints().add(new RowConstraints(rowHeight)));
        }
        this.gridPane.setGridLinesVisible(true);
    }

    private void renderMap() {
        this.gridPane.getChildren().clear();
        this.initGridPane();
        this.updateColRowDimensions();
        map.mapElements().forEach(element -> {
            // check if resource exists
            GuiElementBox guiElement = this.cachedResources.get(element.hashCode());
            // resource does not exist yet, crate and cache it
            if (guiElement == null) {
                guiElement = new GuiElementBox(element);
                this.cachedResources.put(element.hashCode(), guiElement);
            }
            Vector2d convCoor = convCoordinates(element.position());
            this.gridPane.add(guiElement.toNode(element.position().toString()), convCoor.x, convCoor.y);
        });
    }

    @Override
    public void start(Stage primaryStage) {
        this.gridPane.setGridLinesVisible(true);    // I don't know why it disappears during simulation :(
        VBox mainContainer = new VBox();
        HBox bottomsContainer = new HBox();

        Button button = new Button("Start");
        button.setFont(new Font(FONTSIZE));
        TextField textField = new TextField();
        textField.setFont(new Font(FONTSIZE));

        button.setOnAction(event -> {
            MoveDirection[] directions = OptionsParser.parse(textField.getText().split(" "));
            this.engine.setDirections(directions);
            Thread engineThread = new Thread(this.engine);
            engineThread.start();
        });



        bottomsContainer.getChildren().addAll(button, textField);
        mainContainer.getChildren().addAll(this.gridPane, bottomsContainer);

        Scene scene = new Scene(mainContainer, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(this::renderMap);
    }

    @Override
    public void orientationChanged(MapDirection oldOrientation, MapDirection newOrientation) {
        Platform.runLater(this::renderMap);
    }
}
