/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Questions;
import Entities.Quiz;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class QuizResultScreenFXMLController implements Initializable {

    private PieChart attempedChart;
    @FXML
    private PieChart scoreChart;
    @FXML
    private VBox questionsContainer;

    private Map<Questions , String> userAnswers ;
    private Integer numberOfRightAnswers = 0;
    private Quiz quiz ;
    private List<Questions> questionList;
    private Integer notAttemped = 0 ;
    private Integer attemped = 0 ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void  setValues(Map<Questions, String> userAnswers,
                           Integer numberOfRightAnswers, Quiz quiz,
                           List<Questions> questionList) {
        this.userAnswers = userAnswers;
        this.numberOfRightAnswers = numberOfRightAnswers;
        this.quiz = quiz;
        this.questionList = questionList; 

        this.attemped = this.userAnswers.keySet().size();
        this.notAttemped = this.questionList.size() - attemped;

        setValuesToChart();
        renderQuestions();
    }
     private void renderQuestions(){
        for(int i = 0 ; i < this.questionList.size() ; i ++){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                    getResource("QuizResultSingleQuestionFXML.fxml"));

            try {
                Node node = fxmlLoader.load();
                QuizResultSingleQuestionFXMLController controller= fxmlLoader.getController();
                controller.setValues(this.questionList.get(i) , this.userAnswers.get(this.questionList.get(i)));
                questionsContainer.getChildren().add(node); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
     private void setValuesToChart(){
//        ObservableList<PieChart.Data> attempedData = this.attempedChart.getData();
//        attempedData.add(new PieChart.Data(String.format("Attemped (%d)", this.attemped) , this.attemped));
//        attempedData.add(new PieChart.Data(String.format("Not Attemped (%d)", this.notAttemped) , this.notAttemped));
//

        ObservableList<PieChart.Data> scoreChartDatata = this.scoreChart.getData();
        scoreChartDatata.add(new PieChart.Data(
                String.format("Right Answers (%d)", this.numberOfRightAnswers) , this.numberOfRightAnswers));
        scoreChartDatata.add(new PieChart.Data(
                String.format("Wrong Answers (%d)", this.attemped - this.numberOfRightAnswers) , this.attemped-this.numberOfRightAnswers));


    }
    
}
