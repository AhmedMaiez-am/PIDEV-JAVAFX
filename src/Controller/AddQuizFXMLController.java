/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entities.Questions;
import Entities.Quiz;

import Services.Questions_Service;
import Services.Quiz_Service;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import Utils.mail;

/**
 * FXML Controller class
 *
 * @author maiez
 */
public class AddQuizFXMLController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private ImageView backgroundajout;
    @FXML
    private JFXTextField tftitle;
    @FXML
    private JFXRadioButton roption41;
    @FXML
    private JFXTextArea questionfield;
    @FXML
    private JFXTextField tfoption1;
    @FXML
    private JFXTextField tfoption2;
    @FXML
    private JFXTextField tfoption3;
    @FXML
    private JFXTextField tfoption4;
    @FXML
    private JFXRadioButton roption4;
    @FXML
    private JFXRadioButton roption3;
    @FXML
    private JFXRadioButton roption2;
    @FXML
    private JFXRadioButton roption1;
    @FXML
    private JFXButton submitQuiz;
    @FXML
    private JFXButton nextQuestion;
    @FXML
    private ImageView imagebackground;
    @FXML
    private JFXTreeView treeView;
     private ToggleGroup toggleGroup;
    private String title = null;
    private Quiz quiz = null;
    private HashMap<String, String[]> questions = new HashMap<>();
    private ArrayList<Questions> questionData = new ArrayList<>();
    private Integer numberOfRightAnswers = 0;
    private JFXTextField id;
    private JFXTextField ques;
    private JFXTextField o1;
    private JFXTextField o2;
    private JFXTextField o3;
    private JFXTextField o4;
    private JFXTextField ans;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            radio_Button_SetUp();
            renderTreeView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    

    @FXML
    private void submitQuiz(ActionEvent event) throws SQLException, Exception {
        int i=0;
        if (roption41.isSelected()){
         i= 1; 
        }
        Services.Quiz_Service quiz_Service = new Quiz_Service();
        Quiz quizToAdd = new Quiz();
        quizToAdd.setTitle(tftitle.getText().trim());
        quizToAdd.setIsamericain(i);
               mail.main();
        quiz_Service.Add_Quiz(quizToAdd);
        this.renderTreeView();
    }

    @FXML
    private void AddNextQuestion(ActionEvent event) throws SQLException {
        boolean valid_Fiels = validate_All_Fields();
        if (valid_Fiels) {

            Questions questionToAdd = new Questions();
            questionToAdd.setQuestion(this.questionfield.getText().trim());

            questionToAdd.setOption1(tfoption1.getText().trim());
            questionToAdd.setOption2(tfoption2.getText().trim());
            questionToAdd.setOption3(tfoption3.getText().trim());
            questionToAdd.setOption4(tfoption4.getText().trim());

            Toggle selected = toggleGroup.getSelectedToggle();
            String this_answer = null;
            if (selected == roption1) {
                this_answer = tfoption1.getText().trim();
            } else if (selected == roption2) {
                this_answer =tfoption2.getText().trim();
            } else if (selected == roption3) {
                this_answer = tfoption3.getText().trim();
            } else if (selected == roption4) {
                this_answer = tfoption4.getText().trim();
            }
            questionToAdd.setAnswer(this_answer);

            this.questionfield.clear();
            this.tfoption1.clear();
            this.tfoption2.clear();
            this.tfoption3.clear();
            this.tfoption4.clear();

            System.out.println("saving question ...");

            questionData.add(questionToAdd);
            if (tftitle.getText().trim().isEmpty()) {
                Notifications notification = Notifications.create();
                notification.text("Quiz n'a pas été sauvegardé !");
                notification.title("Quiz ");
                notification.darkStyle();
                notification.position(Pos.CENTER);
                notification.hideAfter(Duration.millis(1500));
                notification.showError();
            }
            Services.Questions_Service questions_Service = new Questions_Service();
            
            questions_Service.Add_Questions(questionToAdd, tftitle.getText().trim());
            Notifications notification = Notifications.create();
            notification.text("Quiz a été sauvegardé !");
            notification.title("Quiz ");
            notification.darkStyle();
            notification.position(Pos.CENTER);
            notification.hideAfter(Duration.millis(1500));
            notification.showInformation();
            System.out.println("Object Questions contains : " + questionToAdd);
            System.out.println("Object quiz contains : " + quiz);
        }
    }


    private void renderTreeView() throws SQLException {
        Services.Questions_Service zz = new Questions_Service();
        zz.getAllQuestionsRelatedQuiz();
        Map<Quiz, List<Questions>> dataView = zz.getAllQuestionsRelatedQuiz();
        Set<Quiz> quizes = dataView.keySet();
        TreeItem root = new TreeItem("Quizzes");
        for (Quiz quiz : quizes) {
            TreeItem treeItem = new TreeItem(quiz);

            List<Questions> questions = dataView.get(quiz);
            for (Questions question : questions) {
                TreeItem treeItemQuestion = new TreeItem(question);
                treeItemQuestion.getChildren().add(new TreeItem("A : " + question.getOption1()));
                treeItemQuestion.getChildren().add(new TreeItem("B : " + question.getOption2()));
                treeItemQuestion.getChildren().add(new TreeItem("C : " + question.getOption3()));
                treeItemQuestion.getChildren().add(new TreeItem("D : " + question.getOption4()));
                treeItemQuestion.getChildren().add(new TreeItem("ANSWER : " + question.getAnswer()));
                treeItem.getChildren().add(treeItemQuestion);
            }
            treeItem.setExpanded(true);
            root.getChildren().add(treeItem);

        }
        root.setExpanded(true);
        this.treeView.setRoot(root);
        System.out.println("zzzzzzz" + zz.getAllQuestionsRelatedQuiz());
    }

    private void radio_Button_SetUp() {
        toggleGroup = new ToggleGroup();
        roption1.setToggleGroup(toggleGroup);
        roption2.setToggleGroup(toggleGroup);
        roption3.setToggleGroup(toggleGroup);
        roption4.setToggleGroup(toggleGroup);
    }
    private void setQuizTitle(ActionEvent event) {
        String title = tftitle.getText();
        if (title.trim().isEmpty()) {
            Notifications notification = Notifications.create();
            notification.text("Entrer un titre ");
            notification.title("Titre du Quiz");
            notification.darkStyle();
            notification.position(Pos.CENTER);
            notification.hideAfter(Duration.millis(1500));
            notification.showError();
        } else {
            tftitle.setEditable(false);
            this.quiz = new Quiz(title);

        }
    }
     private boolean validate_All_Fields() {
        String question = this.questionfield.getText();
        String option1 = this.tfoption1.getText();
        String option2 = this.tfoption2.getText();
        String option3 = this.tfoption3.getText();
        String option4 = this.tfoption4.getText();
        Toggle selected_Radio_button = toggleGroup.getSelectedToggle();
//  if (option1 == option2 ||option1 == option3 ||option1 == option4||option2 == option3  ||option2 == option4 ||option3 == option4 ){
//
//            Notifications notification = Notifications.create();
//            notification.text("Verify Your Options  ..... ");
//            notification.title("Alert");
//            notification.darkStyle();
//            notification.position(Pos.CENTER);
//            notification.hideAfter(Duration.millis(1500));
//            notification.showError();
//            return false;
//        }
   
        if (question.trim().isEmpty() || option1.trim().isEmpty() || option2.trim().isEmpty() || option3.trim().isEmpty() || option4.trim().isEmpty()) {

            Notifications notification = Notifications.create();
            notification.text("Les champs ne sont pas valides ..... /n [Question , Option1 , Option2 , Option3 , Option4 ]");
            notification.title("Titre du Quiz");
            notification.darkStyle();
            notification.position(Pos.CENTER);
            notification.hideAfter(Duration.millis(1500));
            notification.showError();
            return false;
        } else {
               
    if (option1.trim().equals(option2.trim())  || option1.trim().equals(option3.trim()) || option1.trim().equals(option4.trim())  || option2.trim().equals(option3.trim()) || option2.trim().equals(option4.trim()) || option3.trim().equals(option4.trim())) {
        
            Notifications notification = Notifications.create();
            notification.text("Vérifier vos options ...");
            notification.title("Titre du Quiz");
            notification.darkStyle();
            notification.position(Pos.CENTER);
            notification.hideAfter(Duration.millis(1500));
            notification.showError();
            return false;
        } 
       
            if (selected_Radio_button == null) {
                String message = "Sélectionner une réponse ...";
                Notifications notification = Notifications.create();
                notification.text(message);
                notification.title("Titre du Quiz");
                notification.darkStyle();
                notification.position(Pos.CENTER);
                notification.hideAfter(Duration.millis(1500));
                notification.showError();
                return false;
            } else {
                return true;
            }

        }
    }
    
}
