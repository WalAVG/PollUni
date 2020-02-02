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
import java.util.Set;
import org.apache.catalina.connector.Connector;
import poll.data.dto.PollUserViewDTO;
import poll.data.impl.PollUserImpl;
import poll.data.model.PollUser;

/**
 *
 * @author gigan
 */
public class PollUserDAO_MySQL implements PollUserDAO{

    @Override
    public PollUserImpl getUserByID(int id) {
        
        PollUserImpl error = new PollUserImpl("error","no","no",1);
        Connection connection = MyConnection.getConnection();
        try {
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PollUser WHERE user_ID=" + id);
            if(rs.next())
            {
                return extractUserFromResultSet(rs);
            }
            else{
                return error;
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }
    
    private PollUserImpl extractUserFromResultSet(ResultSet rs) throws SQLException {
        PollUserImpl user = new PollUserImpl();
            user.setUsername( rs.getString("username") );
            user.setPassword( rs.getString("pass") );
            user.setEmail( rs.getString("email") );
            user.setID( rs.getInt("user_ID") );
            
        return user;
    }
    

    @Override
    public Set<PollUserImpl> getAllUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PollUserImpl getByName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertUser(PollUserImpl user) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PollUser VALUES (NULL, ?, ?, ?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
        
    }

    @Override
    public boolean updateUser(PollUserImpl user) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE user SET username=?, pass=?, email=? WHERE user_ID=?");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            int i = ps.executeUpdate();
            if(i == 1) {
                return true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
        
    }

    @Override
    public boolean deleteUser(int id) {
    Connection connection = MyConnection.getConnection();
    try {
        Statement stmt = connection.createStatement();
        int i = stmt.executeUpdate("DELETE FROM PollUser WHERE user_ID=" + id);
      if(i == 1) {
    return true;
      }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return false;
    }

    @Override
    public String authenticateUser(PollUserImpl user) {
        Connection connection = MyConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String name = user.getUsername();
            String password = user.getPassword();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PollUser");
            while(rs.next())
            {
                String usernameDB = rs.getString("username");
                String passwordDB = rs.getString("pass");
                if (name.equals(usernameDB)&password.equals(passwordDB)) return "SUCCESS";
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "FAIL";
    
    }

    @Override
    public String signUpUser(PollUserImpl user) {
        Connection connection = MyConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String name = user.getUsername();
            String password = user.getPassword();
            String email = user.getEmail();           
            ResultSet jambo = stmt.executeQuery("SELECT * FROM PollUser WHERE username='"+name +"'");
            if (jambo.next())
                return "Username già esistente";
            jambo = stmt.executeQuery("SELECT * FROM PollUser WHERE email='"+ email +"'");
            if (jambo.next())
                return "Email già utilizzata";
            int globalUserId = 0;
            ResultSet rs = stmt.executeQuery("SELECT * FROM GlobalVar WHERE id='admin'");
            if (rs.next())
                globalUserId = rs.getInt("userId") +1;
            int conferma = stmt.executeUpdate("INSERT INTO PollUser "  + "VALUES ('"+ name +"', '"+ password +"', '"+ email +"',"+ globalUserId +")");
            int conferma2 = stmt.executeUpdate("UPDATE GlobalVar SET userId="+globalUserId+" WHERE id='admin'");
            connection.close();
            return "SUCCESS";            
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "FAIL";
    }

    @Override
    public String authenticateUserByEmail(PollUserImpl user) {
        Connection connection = MyConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String email = user.getEmail();
            String password = user.getPassword();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PollUser");
            while(rs.next())
            {
                String emailDB = rs.getString("email");
                String passwordDB = rs.getString("pass");
                if (email.equals(emailDB)&password.equals(passwordDB)) return "SUCCESS";
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "FAIL";
    }
    @Override
    public PollUserImpl userByEmailPass(PollUserImpl user) {
        Connection connection = MyConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String email = user.getEmail();
            String password = user.getPassword();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PollUser");
            while(rs.next())
            {
                String emailDB = rs.getString("email");
                String passwordDB = rs.getString("pass");
                if (email.equals(emailDB)&password.equals(passwordDB)) {
                    user.setUsername(rs.getString("username"));
                    user.setID(rs.getInt("user_ID"));
                    return user;
                }
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
