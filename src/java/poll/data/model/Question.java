/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.model;

import java.util.List;

/**
 *
 * @author gigan
 */
public interface Question {
    
    int getID();
    void setID(int id);
    
    int getNumber();
    void setNumber(int n);
    
    String getText();
    void setText();
    
    int getType();
    void setType(String i);
    
    int getMinChoice();
    int getMaxChoice();
    void setChoice(int min, int max);
    
    List<Answer> getAnswers();
    void setAnswers(List<Answer> answers);
    void addAnswer(Answer answer);
    void removeAnswer(Answer answer);
    
}
