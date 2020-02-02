/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.proxy;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import poll.data.dao.DataException;
import poll.data.dao.DataLayer;
import poll.data.dao.PollUserDAO;
import poll.data.impl.PollImpl;
import poll.data.model.PollUser;

/**
 *
 * @author gigan
 */
public class PollProxy extends PollImpl {
    
    protected boolean dirty;
    protected int author_key = 0;
    
    protected DataLayer dataLayer;
    
    public PollProxy(DataLayer d){
        super();
        this.dataLayer=d;
        this.dirty = false;
        this.author_key = 0;
    }
    
    @Override
    public void setID(int id){
        super.setID(id);
        this.dirty = true;
    }
    
    @Override
    public void setTitle(String title){
        super.setTitle(title);
        this.dirty = true;
    }
    
    @Override
    public void setCreationDate(Date date){
        super.setCreationDate(date);
        this.dirty = true;
    }
    
    @Override
    public void setEditDate(Date date){
        super.setEditDate(date);
        this.dirty = true;
    }
    
     @Override
    public void setDeleteDate(Date date){
        super.setDeleteDate(date);
        this.dirty = true;
    }
    
    @Override
    public void setLimPassword (String password){
        super.setLimPassword(password);
        this.dirty = true;
    }
    
    @Override
    public PollUser getAuthor(){
        /*if (super.getAuthor() == null && author_key > 0) {
            try {
                super.setAuthor(((PollUserDAO) dataLayer.getDAO(PollUser.class)).getAuthor(author_key));
            } catch (DataException ex) {
                Logger.getLogger(PollProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        return super.getAuthor();
    }
    @Override
    public void setAuthor(PollUser author){
        super.setAuthor(author);
        this.author_key = author.getID();
        this.dirty = true;
    }
    
    
    
    
    
    
    //METODI DEL PROXY
    //PROXY-ONLY METHODS
    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }

    public boolean isDirty() {
        return dirty;
    }
    
    public void setAuthorKey(int author_key){
        this.author_key = author_key;
        super.setAuthor(null);
    }

}
