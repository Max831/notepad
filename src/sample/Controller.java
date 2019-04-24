package sample;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
    final private FileChooser fileChooser = new FileChooser();
    private Desktop desktop = Desktop.getDesktop();
    Stage primaryStage;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea text;

    @FXML
    private Button create;

    @FXML
    private Button open;

    @FXML
    private Button save;

    @FXML
    private Button exit;

    @FXML
    void initialize() {
open.setOnAction(new EventHandler<ActionEvent>()  {
    @Override
    public void handle(ActionEvent event) {
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Текстовые документы.txt", "*.txt");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            File file2 = file;
            try {
                text.clear();
                Scanner scanner = new Scanner(Paths.get(file2.getAbsolutePath()), StandardCharsets.UTF_8.name());
                //здесь мы можем использовать разделитель, например: "\\A", "\\Z" или "\\z"
                String data = scanner.useDelimiter("\\A").next();
                text.appendText(data);
                scanner.close();

            }
            catch (NoSuchElementException e){
                text.appendText("Пустой файл");
            }
            catch (Exception e) {
                e.getMessage();
                //System.out.println(e);


            }

            }
        }

});
        create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    text.clear();
                    fileChooser.setTitle("Создать документ");
                    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Текстовые документы.txt", "*.txt");
                    fileChooser.getExtensionFilters().add(filter);
                    // File file=fileChooser.setInitialFileName();
                    File file = fileChooser.showSaveDialog(primaryStage);
                    file.createNewFile();
                    //file.;



                    save.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String text1= text.getText();
                            try {
                                //File file = fileChooser.showOpenDialog(primaryStage);
                                FileWriter fileWriter=new FileWriter(file.getAbsolutePath());
                                fileWriter.write(text1);
                                fileWriter.close();


                            }
                            catch(Exception e){
                                System.out.println(e);
                            }

                        }
                    });
                }
                catch(Exception e){
                    System.out.println(e);
                }

            }
        });


        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Platform.exit();
            }
        });

    }



}