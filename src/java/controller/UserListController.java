/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import poll.data.dao.PollUserDAO;
import poll.data.dao.PollUserDAO_MySQL;
import poll.data.impl.PollUserImpl;

/**
 *
 * @author gigan
 */
public class UserListController extends HttpServlet {
    
    public UserListController(){
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int parametro1 = Integer.parseInt(request.getParameter("id"));
        PollUserImpl user = new PollUserImpl();
        
        PollUserDAO userDao = new PollUserDAO_MySQL();
       // userDao.getUserByID(parametro1);
        response.getWriter().append("Hello World ! ");
        response.getWriter().append("" + parametro1);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int parametro1 = Integer.parseInt(request.getParameter("id"));
        
        PollUserDAO userDao = new PollUserDAO_MySQL();
        PollUserImpl user = (PollUserImpl)userDao.getUserByID(parametro1);//userDao.getUserByID(parametro1);
        //String name = user.getUsername();
        
        response.getWriter().print("Hello "+ user.getUsername() + "!!");

    }
    
}
