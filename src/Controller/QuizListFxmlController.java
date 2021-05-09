/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;



import InterfacesServices.NewScreenListener;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
/**
 *
 * @author maiez
 */
public class QuizListFxmlController implements Initializable {
    @FXML
    private StackPane stackPanel;
    @FXML
    private JFXButton back;
    @FXML
    private AnchorPane an;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            addQuizListScreen();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }    
    private void addScrennToStackPane(Node node){
        this.stackPanel.getChildren().add(node);
        
    }
    private void addQuizListScreen() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QuizList.fxml"));
        try{
            Node node = fxmlLoader.load();
            QuizListController quizListController = fxmlLoader.getController();
            quizListController.setScreenListener(new NewScreenListener() { 
                @Override
                public void ChangeScreen(Node node) {
                    addScrennToStackPane(node);
                 }
                
                @Override
                public void handle(Event event) {
                 }

                @Override
                public void removeTopScreen() {
                    stackPanel.getChildren().remove(stackPanel.getChildren().size() - 1);
                 }

                

               
                
            });
            stackPanel.getChildren().add(node);
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void back(ActionEvent event) {
          ObservableList<Node> nodes = this.stackPanel.getChildren();
        if(nodes.size() == 1){
            return;
        }
        this.stackPanel.getChildren().remove(nodes.size()-1);
    }
    
}
