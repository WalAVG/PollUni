/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.impl;

import java.util.List;
import poll.data.model.Answer;
import poll.data.model.Question;
import poll.data.model.QuestionType;

/**
 *
 * @author gigan
 */
public class QuestionImpl implements Question {
    
    private int id;
    private int question_number;
    private String text;
    private QuestionType question_type;
    private int minChoice;
    private int maxChoice;
    private List<Answer> answers;
    
    public QuestionImpl(){
        id = 0;
        question_number = 0;
        text = "";
        answers = null;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id=id;
    }

    @Override
    public int getNumber() {
        return question_number;
    }

    @Override
    public void setNumber(int n) {
        this.question_number=n;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText() {
        this.text=text;
    }

    @Override
    public int getType() {
        return question_type.ordinal();
    }

    @Override
    public void setType(String i) {
        this.question_type=QuestionType.valueOf(i);
    }

    @Override
    public int getMinChoice() {
        return minChoice;
    }

    @Override
    public int getMaxChoice() {
        return maxChoice;
    }

    @Override
    public void setChoice(int min, int max) {
        this.minChoice=min;
        this.maxChoice=max;
    }

    @Override
    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public void setAnswers(List<Answer> answers) {
        this.answers=answers;
    }

    @Override
    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    @Override
    public void removeAnswer(Answer answer) {
        this.answers.remove(answer);
    }
    
}
