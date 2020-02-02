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
import poll.data.dao.PollUserDAO;
import poll.data.dao.PollUserDAO_MySQL;
import poll.data.dto.PollUserViewDTO;
import poll.data.impl.PollUserImpl;

/**
 *
 * @author gigan
 */
public class SignUpController extends HttpServlet {
    
    public SignUpController(){
        
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        PrintWriter out=response.getWriter();  
        Map<String, String> message = new HashMap<String, String>(); 
        Map<String, String> errMessage = new HashMap<String, String>(); 
        errMessage.put("txt1", "Something went wrong");
        
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        boolean validate = CasePattern.validateWord(name);
        boolean verify = true;
        if (!validate){
            verify = false;
            errMessage.put("error", "Username is incorrect");
            request.setAttribute("errMessage", errMessage);
            request.getRequestDispatcher("/error.jsp").forward(request, response);//forwarding the request
        }
        validate = CasePattern.validateEmail(email);
        if (!validate){
            verify = false;
            errMessage.put("error", "Email is invalid");
            request.setAttribute("errMessage", errMessage);
            request.getRequestDispatcher("/error.jsp").forward(request, response);//forwarding the request
        }
        validate = CasePattern.validateWord(password);
        if (!validate){
            verify = false;
            errMessage.put("error", "Password is incorrect");
            request.setAttribute("errMessage", errMessage);
            request.getRequestDispatcher("/error.jsp").forward(request, response);//forwarding the request
        }
        PollUserImpl user = new PollUserImpl();
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(email);
        
        PollUserDAO userDao = new PollUserDAO_MySQL();
        String userSignUp = userDao.signUpUser(user);
        if(userSignUp.equals("SUCCESS")&&verify) //If function returns success string then user will be rooted to Home page
         {
            message.put("name", name);
            message.put("txt1", "You've been successfully registered");
            request.setAttribute("message", message);
            request.getRequestDispatcher("/success.jsp").forward(request, response);//RequestDispatcher is used to send the control to the invoked page.
        }
        else
        { 
            errMessage.put("error", userSignUp); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
            request.setAttribute("errMessage", errMessage);
            request.getRequestDispatcher("/error.jsp").forward(request, response);//forwarding the request
        }
    }
}
