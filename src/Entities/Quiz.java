/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author maiez
 */
import Utils.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ayoub
 */
public class Quiz {
     private MaConnection dataSource;
    private Statement statement;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    
    private Integer quizId;
    private String Title;
    private Integer  isamericain;

    public Integer getIsamericain() {
        return isamericain;
    }

    public void setIsamericain(Integer isamericain) {
        this.isamericain = isamericain;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public Quiz(String Title) {
        this.Title = Title;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.quizId);
        hash = 59 * hash + Objects.hashCode(this.Title);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Quiz other = (Quiz) obj;
        if (!Objects.equals(this.Title, other.Title)) {
            return false;
        }
        if (!Objects.equals(this.quizId, other.quizId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Quiz{" + "quizId=" + quizId + ", Title=" + Title + '}';
    }

 
   public Quiz(Integer quizId, String Title,Integer isamericain) {
        this.quizId = quizId;
        this.Title = Title;
        this.isamericain=isamericain;
    }
   
    public Quiz(Integer quizId, String Title) {
        this.quizId = quizId;
        this.Title = Title;
    }

    public Quiz() {
    }
}
