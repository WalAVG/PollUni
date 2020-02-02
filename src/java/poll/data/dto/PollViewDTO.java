/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.dto;

import java.util.List;
import poll.data.model.Question;

/**
 *
 * @author gigan
 */
public class PollViewDTO {
    
    private String title;
    private List<Question> questions;
 
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setQuestions(List<Question> questions){
        this.questions=questions;
    }
    public List<Question> getQuestions(){
        return questions;
    }
    
}
