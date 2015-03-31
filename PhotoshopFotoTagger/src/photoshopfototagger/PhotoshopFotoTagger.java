/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photoshopfototagger;

import Watchers.Watcher;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Willem
 */
public class PhotoshopFotoTagger extends Application {

    private FototaggerController controller;
    private Watcher watcher;

    @Override
    public void start(Stage stage) {
        try {

            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Kies een directory");
            File defaultDirectory = new File("c:/Users/");
            chooser.setInitialDirectory(defaultDirectory);
            File selectedDirectory = chooser.showDialog(stage);

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "Fototagger.fxml"
                    )
            );

            AnchorPane page = (AnchorPane) loader.load();
            Scene scene = new Scene(page);
            stage.setScene(scene);
            this.controller = loader.<FototaggerController>getController();
            stage.setTitle("Foto Tagger");

            NieuweWatch(selectedDirectory.toPath());
            
            
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent we) {
                    System.out.println("Stage is closing");
                    stage.close();
                }
            });

            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(PhotoshopFotoTagger.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void NieuweWatch(Path pad) {
        System.out.println("Path being watched");
        try {
            this.watcher = new Watcher(pad, false, this, this.controller);
            new Thread(watcher).start();
            System.out.println("watcher started");
        } catch (IOException ex) {
            Logger.getLogger(PhotoshopFotoTagger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
