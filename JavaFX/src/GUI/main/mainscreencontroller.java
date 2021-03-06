package GUI.main;

import GUI.Afterloadscreen.AfterLoadScreenController;
import com.Engine.EngineInterface;
import com.Engine.Myexception;
import com.Engine.StockException;
import com.load.Loadxml;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainscreencontroller  implements Initializable {
    File file;

    @FXML private ImageView lockImageView;
    @FXML private Label errMessage;
    @FXML private Button button;
    @FXML private AnchorPane anchorPane;
    @FXML private ProgressBar progressBar;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button.setPadding(new Insets(-1,-1,-1,-1));
        button.setGraphic(lockImageView);
        button.setOnMouseClicked(event -> {
                    FileChooser fileChooser = new FileChooser();
                    file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());

                    if (file != null) {
                        loadTaskfunc();
                    }
                });
        //Error Label
        errMessage.setText("");
        errMessage.prefWidthProperty().bind(anchorPane.widthProperty());
        //Progress bar
        progressBar.prefWidthProperty().bind(anchorPane.widthProperty());
        progressBar.setVisible(false);


    }

    public void loadTaskfunc(){
        Service<EngineInterface> service=new Service<EngineInterface>() {
            @Override
            protected Task<EngineInterface> createTask() {
                return new Task<EngineInterface>() {
                    @Override
                    protected EngineInterface call()  {
                        EngineInterface res=null;
                        progressBar.setVisible(true);
                        try {
                              res = Loadxml.ParseXml(file);
                            for(int i=1;i<=10;i++){
                                Thread.sleep(300);
                                updateProgress(i*100,1000);
                            }
                            updateMessage("Loading Success");

                        } catch (JAXBException | Myexception | FileNotFoundException | StockException | InterruptedException e) {
                            progressBar.setVisible(false);
                            updateMessage(e.toString());
                        }
                        return res;
                    }
                };
            }
        };
        service.setOnSucceeded(event -> {
              errMessage.setText(service.getMessage());
            try {
                if(service.getValue()!=null){
                    SwitchScene(service.getValue());
                }
            } catch (IllegalStateException|IOException e) {
                errMessage.setText(e.toString());
            }
        });
        progressBar.progressProperty().bind(service.progressProperty());
        service.start();
    }

        public void SwitchScene(EngineInterface engine) throws IOException {   //Switch scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUI/Afterloadscreen/AfterLoadScreen.fxml"));
            Parent userScene = loader.load();
            AfterLoadScreenController controller = loader.getController();
            controller.initEngine(engine,"classic",false);
            anchorPane.getScene().setRoot(userScene);

            }
        }



