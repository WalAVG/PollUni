/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import poll.data.impl.PollImpl;
import poll.data.model.Poll;
import poll.data.proxy.PollProxy;

/**
 *
 * @author gigan
 */
public class PollDAO_MySQL implements PollDAO{

    
    @Override
    public String createPoll(PollImpl poll){
        Connection connection = MyConnection.getConnection();
        try{
            Statement stmt = connection.createStatement();
            int pollId = poll.getID();
            String pollTitle = poll.getTitle();
            Timestamp creationDate = Timestamp.valueOf(java.time.LocalDateTime.now());  
            Timestamp editDate = creationDate;
            Date closeDate = null;
            Date deleteDate = null;
            boolean publiced = poll.getPublished();
            boolean closed = poll.getClosed();
            boolean limit = poll.getLimited();
            int userId = poll.getAuthorId();
            String pollPassword = poll.getLimPassword();

            int rs1 = stmt.executeUpdate("INSERT INTO UserXPoll VALUES (" +
                    userId + "," +
                    pollId + ", TRUE , FALSE )"                    
            );
            int rs = stmt.executeUpdate("INSERT INTO Poll " +
                    "VALUES (" + pollId + ",'" + 
                    pollTitle + "','" +
                    creationDate + "','" +
                    editDate + "'," +
                    closeDate + "," +
                    deleteDate + "," +
                    publiced + "," +
                    limit + "," +
                    closed + "," +
                    userId + ",'" +
                    pollPassword + "')" 
                    
            );
            connection.close();
            return "SUCCESS";
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return "FAIL";
    }

    @Override
    public Poll getPoll(int poll_key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Poll> getPolls() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void storePoll(Poll poll) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int newId () {
        Connection connection = MyConnection.getConnection();
        try {
            int i = 0;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM GlobalVar WHERE id='admin'");
            if (rs.next()){
                i = rs.getInt("pollId") +1;
                int conferma2 = stmt.executeUpdate("UPDATE GlobalVar SET pollId="+i+" WHERE id='admin'");
            }            
            return i;                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    
        return 0;
    }

    @Override
    public int getStatus (int pollId, int userId) {
        Connection connection = MyConnection.getConnection();
        try {
            int status = 0;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM UserXPoll WHERE userId="+userId+" AND pollId="+pollId+"");
            if (rs.next()){ 
                if (rs.getBoolean("voted"))
                    status += 1;
                if (rs.getBoolean("superuser"))
                    status += 10;
                ResultSet rs1 = stmt.executeQuery("SELECT * FROM Admin WHERE userId="+userId+"");
                if (rs1.next())
                    status += 100;
            }            
            return status;                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    
        return 0;
    }
    @Override
    public void updateStatus (int pollId, int userId) {
        Connection connection = MyConnection.getConnection();
        try {
            int status = 0;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM UserXPoll WHERE userId="+userId+" AND pollId="+pollId+"");
            if (rs.next()){
                rs.updateBoolean("voted", true);
            }            
            else{
                int i = stmt.executeUpdate("INSERT INTO UserXPoll VALUES("+userId+","+pollId+",0,1)");
            }
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    
    }
    

    @Override
    public Poll getPollById(int id) {
        
        PollImpl error = new PollImpl();
        error.setTitle("questo è un errore");
        Connection connection = MyConnection.getConnection();
        try {
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Poll WHERE poll_ID=" + id);
            if(rs.next())
            {
                return extractPollFromResultSet(rs);
            }
            else{
                return error;
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return error;

    }
    
      @Override
    public int[] extractPollsIdByUserId(int userId) {
        int pollsId[] = new int[10];
         
        Connection connection = MyConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int questionNumber = 1;
            ResultSet rs = stmt.executeQuery("SELECT * FROM UserXPoll WHERE userId=" + userId + " AND superuser= TRUE" );
            while(rs.next()&&questionNumber<5)
            {
                pollsId[questionNumber] = rs.getInt("pollId");
                questionNumber++;
            }
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM UserXPoll WHERE userId=" + userId + " AND superuser= FALSE");
            while(rs1.next()&&questionNumber<10)
            {
                pollsId[questionNumber] = rs.getInt("pollId");
                questionNumber++;
            }
            pollsId[0] = questionNumber;
            while(questionNumber<10){
                pollsId[questionNumber]=0;
                questionNumber++;
            }
            return pollsId;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
        

    }

    private Poll extractPollFromResultSet(ResultSet rs) throws SQLException {
        PollImpl poll = new PollImpl();
        poll.setTitle(rs.getString("poll_title"));
        poll.setID(rs.getInt("poll_ID"));
        poll.setCreationDate(rs.getDate("creation_date"));
        poll.setEditDate(rs.getDate("edit_date"));
        poll.setCloseDate(rs.getDate("close_date"));
        poll.setDeleteDate(rs.getDate("delete_date"));
        poll.setLimited(rs.getBoolean("private"));
        poll.setPublished(rs.getBoolean("public"));
        poll.setClosed(rs.getBoolean("closed"));
        poll.setLimPassword(rs.getString("poll_password"));
        poll.setQuestionsNumber(extractQuestionsNumber(poll.getID()));
        return poll;
    }

    private int extractQuestionsNumber(int pollId) {
        Connection connection = MyConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            int questionNumber = 0;
            ResultSet jambo = stmt.executeQuery("SELECT * FROM Question WHERE poll_ID=" + pollId);
            while(jambo.next())
            {
                questionNumber++;
            }
            return questionNumber;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public Poll getLastPoll(int index){

        Poll error = new PollImpl();
        Poll pollReturn = new PollImpl();
        error.setTitle("questo è un errore");
        Connection connection = MyConnection.getConnection();
        try {
            int id = 0;
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM GlobalVar WHERE id='admin'");
            if (rs1.next())
                id = rs1.getInt("pollId");
            while((10-index)<10){
                pollReturn = getPollById(id);
                if(!pollReturn.getPublished())
                    index--;              
                id--;
            }
            return pollReturn;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return error;

    }

    @Override
    public Poll getLastUserPoll(int index, int userId){

        Poll error = new PollImpl();
        Poll pollReturn = new PollImpl();
        error.setTitle("questo è un errore");
        
        Connection connection = MyConnection.getConnection();
        try {
            int id = 0;
            int pollSuper = 0;
            Statement stmt = connection.createStatement();
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM UserXPoll WHERE userId=" + userId + " AND superuser= TRUE" );
            while (rs1.next()){ 
                pollSuper++; 
            }
            int[] idPolls = new int[pollSuper];
            int indexId = 0;
            ResultSet rs6 = stmt.executeQuery("SELECT * FROM UserXPoll WHERE userId=" + userId + " AND superuser= TRUE" );
            while (rs6.next()){
                idPolls[indexId]= rs6.getInt("pollId");
                indexId++;
            }
            if (index<10&&index>0)
                return getPollById(idPolls[indexId-index]);
            pollReturn.setID(0);
            index = index-pollSuper;
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM UserXPoll WHERE userId=" + userId + " AND superuser= FALSE" );
            int x = 0;
            while (rs2.next()){
                x++;
            }
            ResultSet rs7 = stmt.executeQuery("SELECT * FROM UserXPoll WHERE userId=" + userId + " AND superuser= FALSE" );
            while(x-index>0){
                x--;
                rs7.next();
                if (x-index<1){ 
                    return getPollById(rs7.getInt("pollId"));                    
                }  
            }
            
            return pollReturn;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return error;

    }

    @Override
    public void setPublic() {
 
        Connection connection = MyConnection.getConnection();
        try {
            
            Statement stmt = connection.createStatement();
            int rs = stmt.executeUpdate("UPDATE Poll SET public='TRUE'");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public String closePoll(int pollId, int userId) {
 
        Connection connection = MyConnection.getConnection();
        try {
            
            Statement stmt = connection.createStatement();
            boolean superuser = false;
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM UserXPoll WHERE userId=" + userId + " AND pollId="+pollId);
            if (rs1.next()){
                superuser = rs1.getBoolean("superuser");
                if (!superuser){    
                    ResultSet rs2 = stmt.executeQuery("SELECT * FROM Admin WHERE userId=" + userId);
                    if(rs2.next())
                        superuser = true;
                }
            }
            if (superuser){
                Timestamp closeDate = Timestamp.valueOf(java.time.LocalDateTime.now());  
                int rs = stmt.executeUpdate("UPDATE Poll SET closed=true, close_date='"+closeDate+"' WHERE poll_ID="+ pollId);
                return "SUCCESS";
            }
            
            return "FAIL";
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return"FAIL";
    }

    @Override
    public boolean checkPassUser(int pollId, int userId) {

        Connection connection = MyConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            
            ResultSet jambo = stmt.executeQuery("SELECT * FROM PassPoll WHERE pollId=" + pollId + " AND userId="+userId);
            if(jambo.next())
            {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;    }
  
    @Override
    public String checkPollPassword(int pollId, int userId, String password) {

        Connection connection = MyConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            Poll poll = getPollById(pollId);
            String pollPass = poll.getLimPassword();
            if (pollPass.equals(password)){     
                int jambo = stmt.executeUpdate("INSERT INTO PassPoll VALUES (" + userId + "," + pollId + ")");
                return "SUCCESS";
            } 
            return password+"FAIL"+pollPass;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "FAIL";    }
  
    
}




/**
 *
public class PollDAO_MySQL extends DAO implements PollDAO{

    private PreparedStatement sPollByID;
    private PreparedStatement sPolls, iPoll, uPoll, dPoll;
    
    public PollDAO_MySQL(DataLayer d) {
        super(d);
    }
    
   @Override
    public void init() throws DataException {
        try {
            super.init();
            sPollByID = connection.prepareStatement("SELECT * FROM Poll WHERE poll_ID=?");
            sPolls = connection.prepareStatement("SELECT * FROM Poll WHERE poll_ID=?");
            iPoll = connection.prepareStatement("SELECT * FROM Poll WHERE poll_ID=?");
            uPoll = connection.prepareStatement("SELECT * FROM Poll WHERE poll_ID=?");
            dPoll = connection.prepareStatement("SELECT * FROM Poll WHERE poll_ID=?");
        }  catch (SQLException ex) {
            throw new DataException("Error initializing newspaper data layer", ex);
        }
    }
    @Override
    public void destroy() throws DataException {
        //anche chiudere i PreparedStamenent è una buona pratica...
        //also closing PreparedStamenents is a good practice...
        try {

            sPollByID.close();
            sPolls.close();

            iPoll.close();
            uPoll.close();
            dPoll.close();
        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }
    
    

    @Override
    public PollProxy createPoll() {
        return new PollProxy(getDataLayer());
    }

    @Override
    public Poll createPoll(ResultSet rs) throws DataException {
        PollProxy a = createPoll();
        try {
            a.setID(rs.getInt("poll_ID"));
            a.setAuthorKey(rs.getInt("user_ID"));
            a.setCreationDate(rs.getDate("creation_date"));
            a.setTitle(rs.getString("poll_title"));
            
        } catch (SQLException ex){
            throw new DataException("polldaomysql", ex);
        }
        return a;
    }

    @Override
    public Poll getPoll(int poll_key) throws DataException {

        try {
            sPollByID.setInt(1, poll_key);
            try (ResultSet rs = sPollByID.executeQuery()) {
                if (rs.next()) {
                    return createPoll(rs);
                }
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load poll by ID", ex);
        }

        return null;
    }

    @Override
    public List<Poll> getPolls() throws DataException {
        List<Poll> result = new ArrayList();
        
        
        return result;
    }

    @Override
    public void storePoll(Poll poll) throws DataException {
        int key = poll.getID();
        try {
            if (poll.getID() > 0) {
                if (poll instanceof PollProxy && !((PollProxy) poll).isDirty()) {
                    return;
                }
                uPoll.setString(1, poll.getTitle());
                if (poll.getAuthor() != null) {
                    uPoll.setInt(2, poll.getAuthor().getID());
                } else {
                    uPoll.setNull(2, java.sql.Types.INTEGER);
                }
                uPoll.executeUpdate();
            }
            if (poll instanceof PollProxy) {
                ((PollProxy) poll).setDirty(false);
            }
        } catch (SQLException ex) {
            throw new DataException ("spero che non sia errore", ex);
        }
    }
    
}

 */

