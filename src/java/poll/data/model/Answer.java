/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.model;

/**
 *
 * @author gigan
 */
public interface Answer {
    
    String getID();
    void setID(String id);
    String getQuestionID();
    void setQuestionID(String questionId);
    
    String getText();
    void setText(String text);
    float getNumber();
    void setNumber(float answerNumber);
    
    int getLmin();
    int getLmax();
    void setLenght(int min, int max);
    int getVotes();
    void setVotes(int votes);
    int getPosition();
    void setPosition(int position);
    
}
