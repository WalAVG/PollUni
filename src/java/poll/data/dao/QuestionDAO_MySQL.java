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
import poll.data.impl.QuestionImpl;
import poll.data.model.Question;

/**
 *
 * @author gigan
 */
public class QuestionDAO_MySQL implements QuestionDAO {

    @Override
    public String createQuestion(QuestionImpl question) {
        Connection connection = MyConnection.getConnection();
        try{
            Statement stmt = connection.createStatement();
            int questionPosition = question.getNumber();
            int pollId = question.getID();
            int check = question.getCheck();
            String questionText = question.getText();
            int questionType = question.getType();
            int globalPollId = 0;
            ResultSet jambo = stmt.executeQuery("SELECT * FROM GlobalVar WHERE id='admin'");
            if (jambo.next())
                globalPollId = jambo.getInt("pollId");
            String questionId = globalPollId + "Q" + (999-questionPosition);
            int rs = stmt.executeUpdate("INSERT INTO Question " +
                    "VALUES ('"+ questionId + "',"+
                    questionPosition + "," +
                    pollId + ",'" +
                    questionText +"'," +
                    questionType + "," +
                    0 + "," +   //max
                    0 + "," + //min
                    check + ")" //checkanswer
            );
            connection.close();
            return questionId;
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return "FAIL";
    }

    @Override
    public QuestionImpl getQuestionByNumberPosition(int index, int pollId) {
        
        QuestionImpl question = new QuestionImpl();
         
        Connection connection = MyConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int questionNumber = 1;
            ResultSet rs = stmt.executeQuery("SELECT * FROM Question WHERE poll_ID=" + pollId + " AND question_number=" + index );
            if(rs.next())
            {
                question.setNumber(rs.getInt("question_number"));
                question.setType(rs.getInt("question_type"));
                question.setCheck(rs.getInt("checkAnswer"));
                question.setAnswersNumber(extractAnswerNumber(rs.getString("question_ID")));
                question.setText(rs.getString("question_text"));
                connection.close();
                return question;
            }
                     
            connection.close();

            return question;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
        


    }

    private int extractAnswerNumber(String questionId) {
        Connection connection = MyConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int answerNumber = 0;
            ResultSet rs = stmt.executeQuery("SELECT * FROM Answer WHERE question_ID='" + questionId+"'");
            while(rs.next())
            {

                answerNumber++; 
                
            }

            connection.close();

            return answerNumber;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    @Override
    public String vote3(int pollId, String questionId, int vote) {
        
        Connection connection = MyConnection.getConnection();
        try {
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Answer WHERE question_ID='"+questionId+"' AND answer_text='"+vote+"'");
            if(rs.next())
            {
                int voteUpdate = rs.getInt("votes")+1;
                int jambo = stmt.executeUpdate("UPDATE Answer SET votes="+voteUpdate+" WHERE question_ID='"+questionId+"' AND answer_text='"+vote+"'");
                return "SUCCESS";
            }
            else{
                ResultSet rs1 = stmt.executeQuery("SELECT * FROM Answer WHERE question_ID='"+questionId+"'");
                int index = 0;
                while (rs1.next()){
                    index++;
                }
                int rs2 = stmt.executeUpdate("INSERT INTO Answer VALUES ('"+questionId+"','"+questionId+"A"+(999-index)+"',"+
                        index +",'"+vote+"',0,0,500,1)");
                return "SUCCESS";
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return "FAIL";
    }
    @Override
    public String vote2(int pollId, String questionId, String vote) {
        
        Connection connection = MyConnection.getConnection();
        try {
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Answer WHERE question_ID='"+questionId+"' AND answer_text='"+vote+"'");
            if(rs.next())
            {
                int voteUpdate = rs.getInt("votes")+1;
                int jambo = stmt.executeUpdate("UPDATE Answer SET votes="+voteUpdate+" WHERE question_ID='"+questionId+"' AND answer_text='"+vote+"'");
                return "SUCCESS";
            }
            else{
                ResultSet rs1 = stmt.executeQuery("SELECT * FROM Answer WHERE question_ID='"+questionId+"'");
                int index = 0;
                while (rs1.next()){
                    index++;
                }
                int rs2 = stmt.executeUpdate("INSERT INTO Answer VALUES ('"+questionId+"','"+questionId+"A"+(999-index)+"',"+
                        index +",'"+vote+"',0,0,500,1)");
                return "SUCCESS";
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return "FAIL";
    }
    @Override
    public String vote0(int pollId, int i, String vote) {
        
        Connection connection = MyConnection.getConnection();
        try {
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Answer WHERE answer_ID='"+vote+"'");
            if(rs.next())
            {
                int voteUpdate = rs.getInt("votes")+1;
                int jambo = stmt.executeUpdate("UPDATE Answer SET votes="+voteUpdate+" WHERE answer_ID='"+vote+"'");
                return "SUCCESS";
            }
            else{
                ResultSet rs1 = stmt.executeQuery("SELECT * FROM Answer WHERE question_ID='"+pollId+"Q"+(999-i)+"'");
                int index = 0;
                while (rs1.next()){
                    index++;
                }
                ResultSet rs2 = stmt.executeQuery("INSERT INTO Answer VALUES ('"+pollId+"Q"+(999-i)+"','"+pollId+"Q"+(999-i)+"A"+(999-index)+"',"+
                        index +",'"+vote+"',0,0,500,1)");
                return "SUCCESS";
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return "FAIL";
    }

}
