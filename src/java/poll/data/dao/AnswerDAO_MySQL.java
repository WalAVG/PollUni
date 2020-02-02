/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import poll.data.impl.AnswerImpl;

/**
 *
 * @author gigan
 */
public class AnswerDAO_MySQL implements AnswerDAO{

    @Override
    public String createAnswer(AnswerImpl answer) {
        Connection connection = MyConnection.getConnection();
        try{
            Statement stmt = connection.createStatement();
            String questionId = answer.getQuestionID();
            String answerId = answer.getID();
            int answerPosition = answer.getPosition();
            String answerText = answer.getText();
            float answerNumber = answer.getNumber();
            int lmin = answer.getLmin();
            int lmax = answer.getLmax();
            int votes = 0;
            
            int rs = stmt.executeUpdate("INSERT INTO Answer " +
                    "VALUES ('"+ questionId + "','"+
                    answerId + "'," +
                    answerPosition + ",'" +
                    answerText +"'," +
                    answerNumber + "," +
                    lmin + "," + //min
                    lmax + "," + //max
                    votes + ")" 
            );
            connection.close();
            return "SUCCESS";
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return "FAIL";
    }

    @Override
    public String getAnswerByNumberPosition(int indexQ, int index, int pollId) {

        AnswerImpl answer = new AnswerImpl();
         
        Connection connection = MyConnection.getConnection();
        try {
            Statement stmt = connection.createStatement(); 
            int indexRevised = 999-index;
            ResultSet rs = stmt.executeQuery("SELECT * FROM Answer WHERE question_ID='" + pollId + "Q"+ indexRevised + "' AND answer_position=" + indexQ );
            if(rs.next())
            {
                answer.setText(rs.getString("answer_text"));
                
                connection.close();
                return answer.getText();
            }
                     
            connection.close();

            return "";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
        

    }
 @Override
    public int getVotesByNumberPosition(int indexQ, int index, int pollId) {

        AnswerImpl answer = new AnswerImpl();
         
        Connection connection = MyConnection.getConnection();
        try {
            Statement stmt = connection.createStatement(); 
            int indexRevised = 999-index;
            ResultSet rs = stmt.executeQuery("SELECT * FROM Answer WHERE question_ID='" + pollId + "Q"+ indexRevised + "' AND answer_position=" + indexQ );
            if(rs.next())
            {
                answer.setText(rs.getString("answer_text"));
                answer.setVotes(rs.getInt("votes"));
                
                connection.close();
                return answer.getVotes();
            }
                     
            connection.close();

            return 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
        

    }
    @Override
    public String getAnswerById(String answerId) {

        AnswerImpl answer = new AnswerImpl();
         
        Connection connection = MyConnection.getConnection();
        try {
            Statement stmt = connection.createStatement(); 
            ResultSet rs = stmt.executeQuery("SELECT * FROM Answer WHERE answer_ID='" + answerId + "'");
            if(rs.next())
            {
                answer.setText(rs.getString("answer_text"));
                connection.close();
                return answer.getText();
            }
                     
            connection.close();

            return "";
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
        

    }
    
}
