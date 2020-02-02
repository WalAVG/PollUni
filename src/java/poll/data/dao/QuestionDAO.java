/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.dao;

import poll.data.impl.QuestionImpl;
import poll.data.model.Question;

/**
 *
 * @author gigan
 */
public interface QuestionDAO {
    
        String createQuestion(QuestionImpl question);

        public QuestionImpl getQuestionByNumberPosition(int index, int pollId);
        public String vote0(int pollId, int i, String vote);
        public String vote2(int pollId, String questionId, String vote);
        public String vote3(int pollId, String questionId, int vote);

}
