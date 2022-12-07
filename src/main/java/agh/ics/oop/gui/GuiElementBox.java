package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    private final VBox vbox = new VBox();
    private final ImageView imageView;

    public GuiElementBox(IMapElement element) {
        try {
            FileInputStream file = new FileInputStream(element.toResource());
            this.imageView = new ImageView(new Image(file));
            this.imageView.setFitWidth(20);
            this.imageView.setFitHeight(20);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Node toNode(String name) {
        this.vbox.getChildren().clear();
        Label label = new Label(name);
        label.setFont(new Font(App.FONTSIZE));
        this.vbox.getChildren().addAll(this.imageView, label);
        this.vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
}
