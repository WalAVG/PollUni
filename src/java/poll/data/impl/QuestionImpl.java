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
    
    private int poll_id;
    private int question_position;
    private int answers_number;
    private String text;
    private int question_type;
    private int minChoice;
    private int maxChoice;
    private int check;
    private List<Answer> answers;
    
    public QuestionImpl(int pollId, int position, String text, int type){
        this.poll_id = pollId;
        this.question_position = position;
        this.text = text;
        this.question_type = type;
        this.check = 0;
        answers = null;
    }
    public QuestionImpl(){
        this.poll_id = 0;
        this.question_position = 0;
        this.text = "";
        this.question_type = 0;
        this.check = 0;
        answers = null;
    }

    @Override
    public int getID() {
        return poll_id;
    }

    @Override
    public void setID(int id) {
        this.poll_id=id;
    }

    @Override
    public int getNumber() {
        return question_position;
    }

    @Override
    public void setNumber(int n) {
        this.question_position=n;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text=text;
    }

    @Override
    public int getType() {
        return question_type;
    }

    @Override
    public void setType(int i) {
        this.question_type=i;
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

    @Override
    public int getAnswersNumber() {
        return this.answers_number;
    }

    @Override
    public void setAnswersNumber(int i) {
        this.answers_number = i;
    }

    public void setCheck(int checkAnswer) {
        this.check = checkAnswer;
    }
    public int getCheck (){
        return check;
    }
    
}
