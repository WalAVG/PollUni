/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.dao;

import poll.data.impl.AnswerImpl;


/**
 *
 * @author gigan
 */
public interface AnswerDAO {
    
            String createAnswer(AnswerImpl answer);

    public String getAnswerByNumberPosition(int indexQ, int index, int pollId);

    public String getAnswerById(String answerId);

    public int getVotesByNumberPosition(int indexQ, int index, int pollId);

    
}
