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
public interface PollUser {
    
    int getID();
    void setID(int id);
    
    String getUsername();
    void setUsername(String username);
    
    String getEmail();
    void setEmail(String email);
    
    String getPassword();
    void setPassword(String password);
       
}
