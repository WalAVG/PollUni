/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.dao;

import java.util.Set;
import poll.data.dto.PollUserViewDTO;
import poll.data.impl.PollUserImpl;
import poll.data.model.PollUser;

/**
 *
 * @author gigan
 */
public interface PollUserDAO {
    
    PollUserImpl getUserByID(int id);
    Set<PollUserImpl> getAllUser();
    PollUserImpl getByName();
    boolean insertUser(PollUserImpl user);
    boolean updateUser(PollUserImpl user);
    boolean deleteUser(int id);

    public String authenticateUser(PollUserImpl user);
    public String authenticateUserByEmail(PollUserImpl user);

    public String signUpUser(PollUserImpl user);
    public PollUserImpl userByEmailPass(PollUserImpl user);
    
}
