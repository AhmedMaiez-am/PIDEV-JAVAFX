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
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 *
 * @author maiez
 */
public class Quiz_Result_Service implements Interface_Service {
    
    private Statement statement;
    Connection cnx;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    public Quiz_Result_Service() {
                cnx = MaConnection.getInstance().getConnection();

    }
    public void add_Quiz_Result(Map<Questions , String> userAnsers ,   Quiz quiz , int rightAnswers , int score) throws SQLException{
         cnx = MaConnection.getInstance().getConnection();
          final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
          PreparedStatement preparedStatement=cnx.
                  prepareStatement("insert into quizresult (quiz_id , student_id , right_answer , score  ) values (? , ? , ? ,?  )");
              preparedStatement.setInt(1, quiz.getQuizId());
              preparedStatement.setInt(2, 1);
              preparedStatement.setInt(3, rightAnswers );
               preparedStatement.setInt(4, score );
              
            int result=  preparedStatement.executeUpdate();
           
    }
    private void saveQuizResultDetails(Map<Questions , String> answer ){
             
         }
    
    
    
    
}
