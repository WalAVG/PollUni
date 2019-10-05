/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.impl;

import java.util.Date;
import java.util.List;
import poll.data.model.Poll;
import poll.data.model.PollUser;
import poll.data.model.Question;

/**
 *
 * @author gigan
 */
public class PollImpl implements Poll {

    private int id;
    private String title;
    private Date creation_date;
    private Date edit_date;
    private Date close_date;
    private Date delete_date;
    private boolean published;
    private boolean closed;
    private boolean limited;
    private String limitedPassword;
    private List<PollUser> limitedUsers;
    private PollUser author;
    private List<Question> questions;
    
    public PollImpl(){
        id = 0;
        title = "";
        published = false;
        closed = false;
        limited = false;
        limitedUsers = null;
        questions = null;
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
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title=title;
    }

    @Override
    public Date getCreationDate() {
        return creation_date;
    }

    @Override
    public void setCreationDate(Date date) {
        this.creation_date=date;
    }

    @Override
    public Date getEditDate() {
        return edit_date;
    }

    @Override
    public void setEditDate(Date date) {
        this.edit_date=date;
    }

    @Override
    public Date getCloseDate() {
        return close_date;
    }

    @Override
    public void setCloseDate(Date date) {
        this.close_date=date;
    }

    @Override
    public Date getDeleteDate() {
        return delete_date;
    }

    @Override
    public void setDeleteDate(Date date) {
        this.delete_date=date;
    }

    @Override
    public boolean getPublished() {
        return published;
    }

    @Override
    public void setPublished(boolean bool) {
        this.published=bool;
    }

    @Override
    public boolean getClosed() {
        return closed;
    }

    @Override
    public void setClosed(boolean bool) {
        this.closed=bool;
    }

    @Override
    public boolean getLimited() {
        return limited;
    }

    @Override
    public void setLimited(boolean bool) {
        this.limited=bool;
    }

    @Override
    public String getLimPassword() {
        return limitedPassword;
    }

    @Override
    public void setLimPassword(String password) {
        this.limitedPassword=password;
    }

    @Override
    public PollUser getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(PollUser author) {
        this.author=author;
    }

    @Override
    public List<PollUser> getLimitedUsers() {
        return limitedUsers;
    }

    @Override
    public void setLimitedUsers(List<PollUser> users) {
        this.limitedUsers = users;
    }

    @Override
    public void addLimitedUser(PollUser user) {
        this.limitedUsers.add(user);
    }

    @Override
    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public void setQuestions(List<Question> questions) {
        this.questions=questions;
    }

    @Override
    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    @Override
    public void removeQuestion(Question question) {
        this.questions.remove(question);
    }
    
}
