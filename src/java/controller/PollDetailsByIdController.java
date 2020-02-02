/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import poll.data.dao.PollDAO;
import poll.data.dao.PollDAO_MySQL;
import poll.data.impl.PollImpl;

/**
 *
 * @author gigan
 */
public class PollDetailsByIdController extends HttpServlet {
    
    public PollDetailsByIdController(){
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int parametro1 = Integer.parseInt(request.getParameter("id"));
        
        PollDAO pollDao = new PollDAO_MySQL();
        PollImpl poll = (PollImpl)pollDao.getPollById(parametro1);
        int pollId = poll.getID();
        
        

        response.getWriter().print("Poll Title: " + poll.getTitle() + "<br/>" +
                "Poll ID: " + poll.getID() + "<br/>" +
                "Poll password: " + poll.getLimPassword() + "<br/>" 
        );

    }
    
}
