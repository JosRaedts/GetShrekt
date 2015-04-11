/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photoshopfototagger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.move;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Willem
 */
public class FototaggerController implements Initializable {

    @FXML
    private TextField BarcodeTB;
    @FXML
    private TextArea StatusTB;

    private static Path path;
    private static File latestFile;

    private String TempString = "";
    private int AppendedNumber = 1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void ChangeFotoName() {
        if (latestFile != null) {
            File tempfile = new File(path.toString() + "\\" + this.latestFile.toString());

            String extension = "";
            int i = tempfile.getName().lastIndexOf('.');
            if (i > 0) {
                extension = tempfile.getName().substring(i + 1);
            }

            System.out.println(path.toString() + "\\" + this.latestFile.toString());
            System.out.println(path.toString() + "\\" + this.BarcodeTB.getText() + "." + extension);

            File newFile = new File(path.toString() + "\\..\\Gemaakte Foto's\\" + this.BarcodeTB.getText() + "." + extension);
            try {
                newFile.getParentFile().mkdirs();
                //newFile.createNewFile();
            } catch (Exception e) {
                System.out.println("File == null");
            }
            System.out.println(newFile.getPath());

            if (newFile.exists()) {
                try {
                    System.out.println("File existed");
                    File NewExistingFile = new File(path.toString() + "\\..\\Gemaakte Foto's\\" + this.BarcodeTB.getText() + "-" + this.AppendedNumber + "." + extension);
                    try {
                        NewExistingFile.getParentFile();
                        //NewExistingFile.createNewFile();
                    } catch (Exception e) {
                        System.out.println("File == null");
                    }
                    this.AppendedNumber++;
                    move(tempfile.toPath(), NewExistingFile.toPath(), REPLACE_EXISTING);
                    this.StatusTB.appendText("Foto is aangemaakt " + newFile.getName() + "\n");
                } catch (IOException ex) {
                    this.StatusTB.appendText("Foto niet hernoemd" + "\n");
                    ex.printStackTrace();
                }

            } else {
                try {
                    move(tempfile.toPath(), newFile.toPath(), REPLACE_EXISTING);
                    this.StatusTB.appendText("Foto is aangemaakt " + newFile.getName() + "\n");
                } catch (IOException ex) {
                    this.StatusTB.appendText("Foto niet aangemaakt" + "\n");
                    ex.printStackTrace();
                }
            }
            //this.latestFile = null;
            this.BarcodeTB.requestFocus();
        }
    }

    public void SetPath(Path path) {
        this.path = path;
    }

    public void SetFile(File file) {
        this.latestFile = file;
    }

    public void SetLatestCode(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (!this.BarcodeTB.getText().equals("")) {
                this.TempString = this.BarcodeTB.getText();
                this.AppendedNumber = 1;
                this.StatusTB.appendText("Student gescand: " + this.BarcodeTB.getText() + "\n");
            }
        }
    }
}
