/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.impl;

import poll.data.model.PollUser;

/**
 *
 * @author gigan
 */
public class PollUserImpl implements PollUser {
    
    private int id;
    private String username;
    private String email;
    private String password;    
    
    public PollUserImpl(){
        id = 0;
        username="";
        email="";
    }
    
    public PollUserImpl(String name, String email, String password, int id){
        this.username=name;
        this.password=password;
        this.email=email;
        this.id=id;
    }
    
    @Override
    public int getID(){
        return id;
    }
    
    @Override
    public void setID(int id){
        this.id = id;
    }
    
    @Override
    public String getUsername(){
        return username;
    }
    
    @Override
    public void setUsername(String username){
        this.username=username;
    }
    
    @Override
    public String getEmail(){
        return email;
    }
    
    @Override
    public void setEmail(String email){
        this.email = email;
    }
    
    @Override
    public String getPassword(){
        return password;
    }
    
    @Override
    public void setPassword(String password){
        this.password = password;
    }
    
    
}
