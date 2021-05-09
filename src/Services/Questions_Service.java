/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Questions;
import Entities.Quiz;
import InterfacesServices.Interface_Service;
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author maiez
 */
public class Questions_Service implements Interface_Service {
    
    private Statement statement;
    Connection cnx;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    public Questions_Service() {
        cnx = MaConnection.getInstance().getConnection();
    }
    public void Add_Questions(Questions questionsToAdd , String  title) throws SQLException {
        cnx = MaConnection.getInstance().getConnection();
       int  quizId = getQuizId(title);
        PreparedStatement preparedStatement=cnx.prepareStatement
        ("INSERT INTO `pidev`.`questions` ( `question`,`option1`,`option2`,`option3`,`option4`,`answer`,`quiz`) VALUES ( ? , ? , ? , ? , ? , ? , '"+ quizId+ "' );");
        
        preparedStatement.setString(1, questionsToAdd.getQuestion());
        preparedStatement.setString(2, questionsToAdd.getOption1());
        preparedStatement.setString(3, questionsToAdd.getOption2());
        preparedStatement.setString(4, questionsToAdd.getOption3());
        preparedStatement.setString(5, questionsToAdd.getOption4());
        preparedStatement.setString(6, questionsToAdd.getAnswer());
        
        preparedStatement.executeUpdate();
    }
     public int getQuizId(String title) throws SQLException{
        cnx = MaConnection.getInstance().getConnection();
        int id =0;
        preparedStatement = cnx.prepareStatement( "select quizId from quiz where title ='"+title+"' ");
        resultSet = preparedStatement.executeQuery();
         if (resultSet != null) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1));
                      id = resultSet.getInt(1);
                    
                }
            }
        return id;
    }
      public Map<Quiz , List<Questions>> getAllQuestionsRelatedQuiz() throws SQLException{
        Map<Quiz , List<Questions>> quizes = new HashMap<>(); 
        Quiz key = null;
        List<Questions> questions = new ArrayList<Questions>();
        cnx = MaConnection.getInstance().getConnection();
        String sql ="SELECT * FROM quiz JOIN questions on questions.quiz = quiz.quizId";
        preparedStatement =cnx.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        if(resultSet != null){
            while(resultSet.next()){
                Quiz quiz = new Quiz();
                quiz.setQuizId(resultSet.getInt("quizId"));
                quiz.setTitle(resultSet.getString("title"));
                
                Questions questionsList =new Questions();
                questionsList.setQuestionId(resultSet.getInt("questionId"));
                questionsList.setQuestion(resultSet.getString("question"));
                questionsList.setOption1(resultSet.getString("option1"));
                questionsList.setOption2(resultSet.getString("option2"));
                questionsList.setOption3(resultSet.getString("option3"));
                questionsList.setOption4(resultSet.getString("option4"));
                questionsList.setAnswer(resultSet.getString("answer"));
                
                if(key != null && key.equals(quiz)){
                    quizes.get(key).add(questionsList);
                }
                else{
                    ArrayList<Questions> values = new ArrayList<>();
                    values.add(questionsList);
                    quizes.put(quiz, values);
                }
                key = quiz;
            }
        }
        return quizes;
    }
      public int deleteQuestion(int Id ) throws SQLException{
        String sql = "delete from questions where questionID = '"+Id+"'";
        statement = cnx.prepareStatement(sql);
        statement.executeUpdate(sql);
        return Id;
    }
      public List<Questions> afficherquestions()throws SQLException {
        List<Questions> list = new ArrayList<>();
        String req = "select * from questions";
      
            cnx = MaConnection.getInstance().getConnection();
 
        preparedStatement =cnx.prepareStatement(req);
        resultSet = preparedStatement.executeQuery(req);

            while (resultSet.next()) {
                list.add(new Questions( resultSet.getInt("questionID"), resultSet.getString("question")));
           
        } 
        return list;
    }
      public void updateQuestion(int id , Questions question) throws SQLException{
        String sql ="update questions set question = ? , option1=? , option2=? , option3=? , option4=? , answer=? where questionID = '"+id+"' ";
        PreparedStatement preparedStatement= cnx.prepareStatement(sql);
        preparedStatement.setString(1, question.getQuestion());
        preparedStatement.setString(2, question.getOption1());
        preparedStatement.setString(3, question.getOption2());
        preparedStatement.setString(4, question.getOption3());
        preparedStatement.setString(5, question.getOption4());
        preparedStatement.setString(6, question.getAnswer());
         
        
        preparedStatement.executeUpdate();
    }
    
    
    
}
