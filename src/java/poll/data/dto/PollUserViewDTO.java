/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.dto;

/**
 *
 * @author gigan
 */
public class PollUserViewDTO {
    
    private String username;
    
    public PollUserViewDTO(){
        this.username = "ciao";
    }
    
    public String getUsername(){
        return username;
    }
    public void setUsername(String n){
        this.username = n;
    }
    
}
