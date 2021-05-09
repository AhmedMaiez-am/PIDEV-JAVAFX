/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

import javax.jnlp.IntegrationService;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class ProgressCircleFXMLController implements Initializable {

    @FXML
    private Circle circle;
    @FXML
    private Label number;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setNumber(Integer number) {
        this.number.setText(number.toString());
    }

   public void setDefaultColor(){
        //circle.setFill(Color.web("#DAE0E2"));
        circle.setFill(javafx.scene.paint.Color.GRAY);
        number.setTextFill(javafx.scene.paint.Color.WHITE );
    }

    public void setCurrentQuestionColor(){
        circle.setFill((javafx.scene.paint.Color.BLUE));
        number.setTextFill((javafx.scene.paint.Color.WHITE));
    }

    public void setWrongAnsweredColor(){
        circle.setFill(javafx.scene.paint.Color.RED);
        number.setTextFill(javafx.scene.paint.Color.WHITE);
    }


    public void setRightAnsweredColor(){
        circle.setFill(javafx.scene.paint.Color.GREEN);
        number.setTextFill(javafx.scene.paint.Color.WHITE);
    }
    
}
