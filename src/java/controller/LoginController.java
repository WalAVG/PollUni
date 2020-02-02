/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import poll.data.dao.PollUserDAO;
import poll.data.dao.PollUserDAO_MySQL;
import poll.data.dto.PollUserViewDTO;
import poll.data.impl.PollUserImpl;

/**
 *
 * @author gigan
 */
public class LoginController extends HttpServlet {
    
    public LoginController(){
        
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
        Map<String, String> message = new HashMap<String, String>(); 
        Map<String, String> errMessage = new HashMap<String, String>(); 
        Map<String, String> userSession = new HashMap<String, String>(); 
        errMessage.put("txt1", "Something went wrong");
        errMessage.put("link1", "/login.html");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        boolean validate = CasePattern.validateWord(password);

        validate = CasePattern.validateEmail(email);
        if (!validate){
            errMessage.put("error","Email is invalid");
            request.setAttribute("errMessage", errMessage);
            request.getRequestDispatcher("/error.jsp").forward(request, response);//forwarding the request
        }
        validate = CasePattern.validateWord(password);
        if (!validate){
            errMessage.put("error","Password is wrong");
            request.setAttribute("errMessage", errMessage);
            request.getRequestDispatcher("/error.jsp").forward(request, response);//forwarding the request
        }
        
        PollUserImpl user = new PollUserImpl();
        user.setEmail(email);
        user.setPassword(password);
        
        PollUserDAO userDao = new PollUserDAO_MySQL();
        PollUserImpl userLogged = userDao.userByEmailPass(user);
        if(userLogged != null) //If function returns success string then user will be rooted to Home page
        {
            String nameL = userLogged.getUsername();
            String idL = Integer.toString(userLogged.getID());
            String emailL = userLogged.getEmail();
            userSession.put("username", nameL);
            userSession.put("id", idL);
            userSession.put("email", emailL);
            HttpSession session=request.getSession();  
            session.setAttribute("user",userSession);
            
            message.put("txt1", "You've successfully authenticated");
            message.put("name", nameL);
            message.put("link1", "home.html");
            request.setAttribute("message", message); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
            request.getRequestDispatcher("/success.jsp").forward(request, response);//forwarding the request
        }
        else
        {
            errMessage.put("error","Email or password is wrong");

            request.setAttribute("errMessage", errMessage); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
            request.getRequestDispatcher("/error.jsp").forward(request, response);//forwarding the request
        } 
        out.close();  
    }
    
 
}
