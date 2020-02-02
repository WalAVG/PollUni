/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.impl;

import poll.data.model.Answer;

/**
 *
 * @author gigan
 */
public class AnswerImpl implements Answer {
    
    private String id;
    private String questionId;
    private int position;
    private String answerText;
    private float answerNumber;
    private int lmin;
    private int lmax;
    private int votes;
    
    public AnswerImpl(){
        id = "";
        position = 0;
        answerText = "";
        answerNumber = 0;
        lmin = 0;
        lmax = 500;
        votes = 0;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public void setID(String id) {
        this.id = id;
    }

    @Override
    public String getText() {
        return answerText;
    }

    @Override
    public void setText(String text) {
        this.answerText=text;
    }

    @Override
    public int getLmin() {
        return lmin;
    }

    @Override
    public int getLmax() {
        return lmax;
    }

    @Override
    public void setLenght(int min, int max) {
        this.lmin=min;
        this.lmax=max;
    }
    @Override
    public int getVotes() {
        return votes;
    }
    @Override
    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public float getNumber() {
        return answerNumber;
    }

    @Override
    public void setNumber(float answerNumber) {
        this.answerNumber = answerNumber;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String getQuestionID() {
        return questionId;
    }

    @Override
    public void setQuestionID(String questionId) {
        this.questionId = questionId;
    }
    
}
