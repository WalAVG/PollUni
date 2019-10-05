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
    
    private int id;
    private String text;
    private int lmin;
    private int lmax;
    
    public AnswerImpl(){
        id = 0;
        text = "";
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
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
    
}
