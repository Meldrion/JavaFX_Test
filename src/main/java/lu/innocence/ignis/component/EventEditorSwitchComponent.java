package lu.innocence.ignis.component;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Created by Fabien Steines
 * Last Update on: 05.02.2017.
 */
public class EventEditorSwitchComponent extends HBox{

    private CheckBox switch01;
    private TextField switch01TextField;
    private Button switch01TexButton;

    public EventEditorSwitchComponent() {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(5);
        this.switch01 = new CheckBox("Switch");

        HBox switchButtonBox01 = new HBox();
        this.switch01TextField = new TextField();

        this.switch01TexButton = new Button();
        this.switch01TexButton.setText("...");
        switchButtonBox01.getChildren().addAll(switch01TextField,switch01TexButton);

        switch01.selectedProperty().addListener((observable, oldValue, newValue) -> {
            this.update();
        });

        this.getChildren().addAll(switch01, switchButtonBox01);
        this.update();
    }

    private void update() {
        this.switch01TextField.setDisable(!switch01.isSelected());
        this.switch01TexButton.setDisable(!switch01.isSelected());
    }

}