/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author gigan
 */
public interface Poll {
    
    int getID();
    void setID(int id);
    
    String getTitle();
    void setTitle(String title);
    
    Date getCreationDate();
    void setCreationDate(Date date);
    
    Date getEditDate();
    void setEditDate(Date date);
    
    Date getCloseDate();
    void setCloseDate(Date date);
    
    Date getDeleteDate();
    void setDeleteDate(Date date);
    
    boolean getPublished();
    void setPublished(boolean bool);
    
    boolean getClosed();
    void setClosed(boolean bool);
    
    boolean getLimited();
    void setLimited(boolean bool);
    
    String getLimPassword();
    void setLimPassword (String password);
    
    PollUser getAuthor();
    void setAuthor(PollUser author);
    
    List<PollUser> getLimitedUsers();
    void setLimitedUsers(List<PollUser> users);
    void addLimitedUser(PollUser user);
    
    List<Question> getQuestions();
    void setQuestions(List<Question> questions);
    void addQuestion(Question question);
    void removeQuestion(Question question);
    
    int getQuestionsNumber();
    void setQuestionsNumber(int i);
    
    int getAuthorId();
    void setAuthorId(int i);
    
}
