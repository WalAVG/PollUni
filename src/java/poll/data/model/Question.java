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
    void setText(String text);
    
    int getType();
    void setType(int i);
    
    int getMinChoice();
    int getMaxChoice();
    void setChoice(int min, int max);
    
    List<Answer> getAnswers();
    void setAnswers(List<Answer> answers);
    void addAnswer(Answer answer);
    void removeAnswer(Answer answer);
    
    int getAnswersNumber();
    void setAnswersNumber(int i);
    
}
