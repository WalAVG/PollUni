/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.dao;

import java.sql.ResultSet;
import java.util.List;
import poll.data.impl.PollImpl;
import poll.data.model.Poll;

/**
 *
 * @author gigan
 */
public interface PollDAO {
   
    String createPoll(PollImpl poll);
    Poll getPoll(int poll_key);
    List<Poll> getPolls();
    void storePoll (Poll poll);

    public int newId();
    public Poll getPollById (int id);
    public int[] extractPollsIdByUserId(int userId);
    public Poll getLastPoll(int index);
    public void setPublic();
    public int getStatus(int pollId, int userId);
    public void updateStatus (int pollId, int userId);

    public Poll getLastUserPoll(int index, int userId);
 

    public String closePoll(int pollId, int userId);

    public boolean checkPassUser(int pollId, int userId);

    public String checkPollPassword(int pollId, int userId, String password);
}
