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
public class Quiz_Service implements Interface_Service{
    
    private Statement statement;
    Connection cnx;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    public Quiz_Service() {
                cnx = MaConnection.getInstance().getConnection();
    }
    
     public void Add_Quiz(Quiz quizToAdd) throws SQLException {
        cnx = MaConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = cnx.prepareStatement("INSERT INTO `pidev`.`quiz` ( `title` , `isamericain`) VALUES ( ? , ? );");
        preparedStatement.setString(1, quizToAdd.getTitle());
          preparedStatement.setInt(2, quizToAdd.getIsamericain());
        preparedStatement.executeUpdate();

    }
      public List<Quiz> afficherquiz() throws SQLException{
        List<Quiz> list = new ArrayList<>();
        String req = "select * from quiz ";
        try {
         preparedStatement =cnx.prepareStatement(req);
        resultSet = preparedStatement.executeQuery();
            
               while (resultSet.next()) {
                list.add(new Quiz(resultSet.getInt("quizId"), resultSet.getString("Title")));
           
            }

        } catch (SQLException ex) {
           
        }
        return list;
    }
      public Map<Quiz, Integer> getAllWithQuestionCount() throws SQLException {
        cnx = MaConnection.getInstance().getConnection();
        Map<Quiz, Integer> quizes = new HashMap<>();
        Quiz key = null;

        String query = "SELECT  quizId , title , isamericain, Count(*)"
                + " AS question_count FROM quiz INNER JOIN questions ON quiz.quizId = questions.quiz GROUP BY quiz.quizId";

        preparedStatement =cnx.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Quiz temp = new Quiz();
            temp.setQuizId(resultSet.getInt(1));
            temp.setTitle(resultSet.getString(2));
            temp.setIsamericain(resultSet.getInt(3));
            int count = resultSet.getInt(4);
            quizes.put(temp, count);

        }

        return quizes;
    }
       public List<Questions> getQuestions(int quizId) throws SQLException{
        List<Questions> quizes = new ArrayList<>();
        String query ="SELECT * FROM questions where quiz = ?";
          Quiz quiz = new  Quiz();
        preparedStatement =cnx.prepareStatement(query);
        preparedStatement.setInt(1,  quizId );
        resultSet = preparedStatement.executeQuery();
        while( resultSet.next()){
            Questions questions = new Questions();
            questions.setQuestionId(resultSet.getInt(1));
            questions.setQuestion(resultSet.getString(2));
            questions.setOption1(resultSet.getString(3));
            questions.setOption2(resultSet.getString(4));
            questions.setOption3(resultSet.getString(5));
            questions.setOption4(resultSet.getString(6));
            questions.setAnswer(resultSet.getString(7));
            questions.setQuiz( quiz);
            quizes.add(questions);
        }
        return quizes;
    }
       public List<Quiz> rechercherquiz(String nomquiz) 
    {
        List<Quiz> list=new ArrayList();
           String req="select * from quiz where Title  LIKE '"+nomquiz+"%'" ;
         try {
         preparedStatement =cnx.prepareStatement(req);
        resultSet = preparedStatement.executeQuery();
            
               while (resultSet.next()) {
                list.add(new Quiz(resultSet.getInt("quizId"), resultSet.getString("Title")));
           
            }

        } catch (SQLException ex) {
           
        }
        return list;
     
}
        public int deleteQuiz(int Id ) throws SQLException{
              String sql2 = "delete from questions where quiz = '"+Id+"'";
        statement = cnx.prepareStatement(sql2);
        statement.executeUpdate(sql2);
        String sql = "delete from quiz where quizId = '"+Id+"'";
        statement = cnx.prepareStatement(sql);
        statement.executeUpdate(sql);
  
        return Id;
    }
    
    
}
