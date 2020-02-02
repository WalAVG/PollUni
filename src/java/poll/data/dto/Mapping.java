/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poll.data.dto;

import poll.data.impl.PollImpl;

/**
 *
 * @author gigan
 */
public class Mapping {
    public static PollViewDTO map(final PollImpl pollDO){
        PollViewDTO view = new PollViewDTO();
        view.setTitle(pollDO.getTitle());
        view.setQuestions(pollDO.getQuestions());
        
        return view;
    }
    
}
