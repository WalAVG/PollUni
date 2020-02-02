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
import poll.data.dao.AnswerDAO;
import poll.data.dao.AnswerDAO_MySQL;
import poll.data.dao.PollDAO;
import poll.data.dao.PollDAO_MySQL;
import poll.data.dao.QuestionDAO;
import poll.data.dao.QuestionDAO_MySQL;
import poll.data.impl.AnswerImpl;
import poll.data.impl.PollImpl;
import poll.data.impl.QuestionImpl;
import poll.data.model.QuestionType;

/**
 *
 * @author gigan
 */
public class PreVotePollController extends HttpServlet {
    
    public PreVotePollController(){
        
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int pollId = Integer.parseInt(request.getParameter("pollId"));
        int userId = 0;
        
        Map<String,String> errMessage = new HashMap<String,String>();
        Map<String,String> elaborate = new HashMap<String,String>();
        Map<String,String> userLogged = new HashMap<String,String>();
        boolean success = true;

        //poll
        PollImpl poll = new PollImpl();
        PollDAO pollDao = new PollDAO_MySQL();
        poll = (PollImpl)pollDao.getPollById(pollId);
        String url = "/PollByIdController?pollId="+pollId;
        userLogged.put("missing", "");
        
        HttpSession session=request.getSession(false);  
        if(session!=null){  
            userLogged = (Map<String,String>)session.getAttribute("user");
            userId = Integer.parseInt(userLogged.get("id"));
        }  
        else{
            errMessage.put("error","Invalid Session");
            request.setAttribute("errMessage", errMessage); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
            request.getRequestDispatcher("/error.jsp").forward(request, response);//forwarding the request
  
        }
        
        String OUTPUT = "";
        OUTPUT += "<form id=\"pollVote\" method=\"post\" action=\"VotePollController\">";
        OUTPUT += "<h2>" + poll.getTitle() +"</h2><br/>";
        OUTPUT += "<div class=\"item-poll-list\"><input type=\"text\" name=\"pollId\" hidden value=\""+pollId+"\">";

        int i = 0;
        while(i<poll.getQuestionsNumber()){
            QuestionDAO questionDao = new QuestionDAO_MySQL();
            QuestionImpl question = questionDao.getQuestionByNumberPosition(i, pollId);
            AnswerDAO answerDao = new AnswerDAO_MySQL();
            
            OUTPUT += "<div><h3>"+ (i+1) +". "+ question.getText() + "</h3>";            

            int questionType = question.getType();
            int questionCheck = question.getCheck();
            int check = 0;
            //domanda con scelta singola
            if (questionType == 0){
                String vote = request.getParameter("question"+i);
                String voteText = answerDao.getAnswerById(vote);
                OUTPUT+="<input type='radio' name='question"+i+"' hidden value='"+vote+"' checked><div>"+voteText+"</div>";                
                if (vote!=null)
                    check=1; 
            }
            
            //domanda scelta multipla
            if (questionType == 1){
                int index = 0;
                int aNumber = question.getAnswersNumber();
                while (index<aNumber){
                    String vote = request.getParameter("question"+i+"a"+index);
                    String answerId = pollId+"Q"+(999-i)+"A"+(999-index);
                    if (vote!=null){
                        OUTPUT += "<input type='checkbox' name='question"+i+"a"+index+"' hidden value='"+vote+"' checked>"+"<div>"+vote+"</div>";                
                        check = 1;
                    }
                    else
                        OUTPUT += "<input type='checkbox' name='question"+i+"a"+index+"' hidden value='"+vote+"'>";                
                    index++;
                }
                
            }            
            //domanda con input e numeri
            if (questionType == 2 || questionType ==3){
                String vote = request.getParameter("question"+i).toLowerCase(); 
                if (vote!=""){
                    check = 1;
                    OUTPUT+="<input type='text' name='question"+i+"' hidden value='"+vote+"'><div>"+vote+"</div>";                
                }
                else
                    OUTPUT+="<input type='text' name='question"+i+"' hidden value=''>";                

            }
            OUTPUT += "</div>";
            OUTPUT += "<br/>";
            /*domanda con numeri
            if (questionType == 3){
                int vote = Integer.parseInt(request.getParameter("question"+i));
                String questionId = pollId+"Q"+(999-i); 
                String voteSuccess = questionDao.vote3(pollId, questionId, vote);
                if (voteSuccess != "SUCCESS")
                    request.getRequestDispatcher("/error.jsp").forward(request, response);//forwarding the request
                if (vote != null)
                    check = 1;
            }*/
            if (questionCheck==1)
                if(check!=1)
                    success=false;
            i++;
            
        }
        OUTPUT += "</div><br/><div>Confermi? <input class='submit-button' type=\"submit\" form=\"pollVote\" value=\"Submit\"> "
                + "<a href='/Progetto_1"+url+"'><button class='submit-button' type='button'>Back</button></a></div></form>";
        elaborate.put("formServlet", OUTPUT);

        if ((Map<String,String>)request.getAttribute("pollInfo")!=null){
            request.removeAttribute("pollInfo");
        }
        request.setAttribute("pollInfo", elaborate);    
        if(success)
            request.getRequestDispatcher("/userpolls.jsp").forward(request, response);//forwarding the request
        else
            request.getRequestDispatcher(url).forward(request, response);//forwarding the request

    }
    
}
