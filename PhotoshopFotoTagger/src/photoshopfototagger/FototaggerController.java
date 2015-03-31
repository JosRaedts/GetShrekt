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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void ChangeFotoName(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (latestFile != null) {
                File tempfile = new File(path.toString() + "\\" + this.latestFile.toString());

                String extension = "";
                int i = tempfile.getName().lastIndexOf('.');                
                if (i > 0) {
                    extension = tempfile.getName().substring(i + 1);
                }

                System.out.println(path.toString() + "\\" + this.latestFile.toString());
                System.out.println(path.toString() + "\\" + this.BarcodeTB.getText() + "." + extension);
                
                File newFile = new File(path.toString() + "\\" +  this.BarcodeTB.getText() + "." + extension);

                if (newFile.exists()) {
                    String name = newFile.getName();
                    int i2 = name.contains(".") ? name.lastIndexOf('.') : name.length();
                    String dstName = name.substring(0, i) + "(Copy)" + name.substring(i);
                    File dest = new File(newFile.getParent(), dstName);
                    tempfile.delete();
                } else {
                    try {
//                    boolean success = tempfile.renameTo(newFile);
//                    if (!success) {
//                        this.StatusTB.appendText("Foto kon niet worden hernoemd \n");
//                    } else {
//                        this.StatusTB.appendText("Foto is aangemaakt en hernoemd \n");
//                    }
                        move(tempfile.toPath(), newFile.toPath(), REPLACE_EXISTING);
                        this.StatusTB.appendText("Foto is aangemaakt en hernoemd naar " + newFile.getName() + "\n \n");
                    } catch (IOException ex) {
                        this.StatusTB.appendText("Foto kon niet worden hernoemd naar " + newFile.getName() + "\n \n");
                    }
                }
                this.latestFile = null;
                this.BarcodeTB.requestFocus();
            }
        }
    }

    public void SetPath(Path path) {
        this.path = path;
    }

    public void SetFile(File file) {
        this.latestFile = file;
    }
}
