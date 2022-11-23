package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMap;
import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.stream.IntStream;

public class App extends Application {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private int numOfCols;
    private int numOfRows;
    private final GridPane gridPane = new GridPane();
    private final Scene scene = new Scene(gridPane, WIDTH, HEIGHT);
    private AbstractWorldMap map;

    public void init() {
        //  MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
        this.map = new GrassField(10);
        this.updateColRowDimensions();
        this.initGridPane();
        this.draw();
        //  Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 2)};
        System.out.println(map);
        //  IEngine engine = new SimulationEngine(directions, map, positions);
        //  engine.run();
    }

    private void initGridPane(){
        addLabel("y/x", 0,0);
        IntStream.rangeClosed(this.map.boundingRect().lowerLeft().x, this.map.boundingRect().upperRight().x)
                .forEach(i -> addLabel(String.valueOf(i), convToCol(i), 0));
        IntStream.rangeClosed(this.map.boundingRect().lowerLeft().y, this.map.boundingRect().upperRight().y)
                .forEach(i -> addLabel(String.valueOf(i), 0, convToRow(i)));
    }

    private void draw() {
        map.mapElements().forEach(i -> addLabel(i.toString(), convToCol(i.getPosition().x), convToRow(i.getPosition().y)));
    }

    private int convToCol(int x) {
        return x-this.map.boundingRect().lowerLeft().x+1;
    }

    private int convToRow(int y) {
        return this.map.boundingRect().upperRight().y-y+1;
    }

    private void addLabel(String text, int col, int row) {
        Label node = new Label(text);
        this.gridPane.add(node, col, row);
        GridPane.setHalignment(node, HPos.CENTER);
    }

    private void updateColRowDimensions() {
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
            int rowHeight = WIDTH/numOfRows;
            this.gridPane.getRowConstraints().clear();
            IntStream.range(0, numOfRows).forEach(i -> this.gridPane.getRowConstraints().add(new RowConstraints(rowHeight)));
        }
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println(getParameters().getNamed().toString());
        gridPane.setGridLinesVisible(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
