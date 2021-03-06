package lu.innocence.ignis.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lu.innocence.ignis.engine.Project;
import lu.innocence.ignis.view.gamedb.TilesetTab;

/**
 * Copyright by Fabien Steines
 * Innocence Studios 2016
 */
public class GameDatabase extends Stage {

    private TilesetTab tilesetTabContent;
    private boolean accepted;
    private Project project;

    public GameDatabase(Stage parentStage, Project project) {

        this.initOwner(parentStage);
        this.initModality(Modality.WINDOW_MODAL);
        this.setResizable(false);
        this.setTitle("Game Database");
        this.buildGUI(project);
        this.initData();
        this.sizeToScene();
        this.accepted = false;
        this.project = project;

    }

    private void buildGUI(Project project) {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);
        this.setScene(scene);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, t -> {
            if (t.getCode() == KeyCode.ESCAPE) {
                this.close();
            }
        });

        TabPane mainTabber = new TabPane();

        Tab actorTab = new Tab();
        actorTab.setText("Actor");
        actorTab.setClosable(false);


        Tab classTab = new Tab();
        classTab.setText("Classes");
        classTab.setClosable(false);

        Tab skillTab = new Tab();
        skillTab.setText("Skills");
        skillTab.setClosable(false);

        Tab itemTab = new Tab();
        itemTab.setText("Items");
        itemTab.setClosable(false);

        Tab tilesetTab = new Tab();
        tilesetTab.setText("Tileset");
        tilesetTab.setClosable(false);
        tilesetTabContent = new TilesetTab(project,this);
        tilesetTab.setContent(tilesetTabContent);

        mainTabber.getTabs().addAll(actorTab, classTab, itemTab, skillTab, tilesetTab);

        root.setCenter(mainTabber);

        // Box on the Bottom
        VBox bottom = new VBox();

        HBox bottomBar = new HBox();

        bottomBar.setSpacing(10);
        bottomBar.setPadding(new Insets(15));
        bottomBar.setAlignment(Pos.CENTER_RIGHT);

        Button acceptButton = new Button();
        acceptButton.setText("Ok");
        acceptButton.setOnAction(event -> {
            this.accepted = true;
            this.close();
        });

        Button cancelButton = new Button();
        cancelButton.setText("Cancel");
        cancelButton.setOnAction(event -> this.close());

        bottomBar.getChildren().addAll(cancelButton, acceptButton);
        root.setBottom(bottom);

        this.setOnShown(event -> {
            this.tilesetTabContent.init();
        });

        bottom.getChildren().addAll(new Separator(),bottomBar);
    }

    private void initData() {

    }

}
