/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import poll.data.dao.PollDAO;
import poll.data.dao.PollDAO_MySQL;
import poll.data.impl.PollImpl;
import poll.data.model.Poll;

/**
 *
 * @author gigan
 */
public class PollUserListController extends HttpServlet {
    
    public PollUserListController(){
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> userSession = new HashMap<String, String>(); 
        Map<String, String> elaborate = new HashMap<String, String>(); 
        userSession.put("username", "ehehehehehehe");
        int userId = 1;
  
        HttpSession session=request.getSession(false);  
        if(session!=null){  
            userSession = (Map<String,String>)session.getAttribute("user");
            userId = Integer.parseInt(userSession.get("id"));
        }  
        PollDAO pollDao = new PollDAO_MySQL();
        /*
        int pollsId[]= new int[10];
        pollsId = pollDao.extractPollsIdByUserId(userId);
        int index = 1; 
            userSession.put("poll"+index, "Poll Title: "  + userSession.get("username") + "<br/>" +
                "Poll ID: <a style=\"background-color:Tomato;\">" + pollsId[index] + "</a><br/>" +
                "Questions number: " +  "<br/>" +
                "Poll password: " +  "<br/>" 
            );
         */

        
            String OUTPUT = "<div class=\"wrap-customp2\">";
            OUTPUT += "<div class=\"wrap\">";
            int index = 1;
            String boxTextNumber = "one";
            String url = "";
            while(index<10){
                Poll poll = new PollImpl();
                poll = pollDao.getLastUserPoll(index, userId);
                index++;
                if(poll.getID()!=0){
                    url = "/Progetto_1/PollByIdController?pollId="+poll.getID();
                    OUTPUT += "<a href='"+ url +"'><div class=\"box "+boxTextNumber+"\">";
                    OUTPUT += "<div class=\"date\"><h4>"+poll.getCreationDate()+"</h4></div>";
                    OUTPUT += "<h1>"+poll.getTitle()+"</h1>";
                    OUTPUT += "<div class=\"poster p"+index+"\">";                 
                    OUTPUT += "<h4>"+poll.getTitle().charAt(0)+"</h4>";
                    OUTPUT += "</div>";
                    OUTPUT += "</div></a>";
                    if (index==2)
                        boxTextNumber="two";
                    if (index==3)
                        boxTextNumber="three";
                    if (index==4)
                        boxTextNumber="four";
                    if (index==5)
                        boxTextNumber="five";
                    if (index==6)
                        boxTextNumber="six";
                    if (index==7)
                        boxTextNumber="seven";
                    if (index==8)
                        boxTextNumber="eight";
                    if (index==9)
                        boxTextNumber="nine";
                }       
            }                 
            OUTPUT += "</div></div>";
            
        request.setAttribute("gg", userSession); 
        elaborate.put("pollByUser", OUTPUT);

        try{
            request.removeAttribute("pollResult");
        }catch(Exception e){
            
        }
        request.setAttribute("pollResult", elaborate);
        request.getRequestDispatcher("/userpolls.jsp").forward(request, response);//forwarding the request

    }
}
