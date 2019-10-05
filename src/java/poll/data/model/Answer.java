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
    
    int getID();
    void setID(int id);
    
    String getText();
    void setText(String text);
    
    int getLmin();
    int getLmax();
    void setLenght(int min, int max);
    
}
