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
import poll.data.impl.PollImpl;
import poll.data.impl.QuestionImpl;

/**
 *
 * @author gigan
 */
public class PollByIdController extends HttpServlet {
    
    public PollByIdController(){
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession(false);  
        if(session==null){  
            request.getRequestDispatcher("login.html").include(request, response);  
        }  

        Map<String, String> user = (Map<String, String>)session.getAttribute("user"); 

        int userId = Integer.parseInt(user.get("id"));
        int pollId = Integer.parseInt(request.getParameter("pollId"));
        String url = request.getRequestURL().toString() + "?pollId=" + pollId;

        Map<String, String> elaborate = new HashMap<String, String>(); 
        Map<String, String> errMessage = new HashMap<String, String>(); 
        
        errMessage = (Map<String,String>)session.getAttribute("error");
        String OUTPUT = "";
        session.removeAttribute("errorMissing");
        if (session.getAttribute("check")!=null){
            session.setAttribute("errorMissing", "");
            session.removeAttribute("check");
        }
        
        PollDAO pollDao = new PollDAO_MySQL();
        int userStatus = pollDao.getStatus(pollId, userId);
        PollImpl poll = (PollImpl)pollDao.getPollById(pollId);
        Map<String, String> closePoll= new HashMap<String, String>();
        closePoll.put("pollId", Integer.toString(pollId));
        request.setAttribute("closePoll", closePoll);
        boolean checkPassword = true;
        if (poll.getLimPassword()!= null)
            checkPassword = pollDao.checkPassUser(pollId,userId);
        
        if ((userStatus==0|userStatus==10|userStatus==100|userStatus==110)&&(!poll.getClosed())){

            if (userStatus==10||userStatus==100||userStatus==110){
                OUTPUT += "<form id='closePoll' method='post' action='CloseServlet'>"
                        + "<input hidden type='text' name='pollId' value='"+pollId+"'>"
                        + "<input hidden type='text' name='userId' value='"+userId+"'>"
                        + "<button style=\"text-align: right;float:right;\"><i class=\"material-icons\" style=\"font-size:32px;color:red;\">close</i></button>"
                        + "</form>";
            }
            OUTPUT += "<form id=\"pollVote\" method=\"post\" action=\"PreVotePollController\">";
            OUTPUT += "<h2>" + poll.getTitle() +"</h2><br/>";
            OUTPUT += "<input type=\"text\" name=\"pollId\" hidden value=\""+pollId+"\">";

            int questionNumber = poll.getQuestionsNumber();
            int index = 0;
            while(index<questionNumber){
                int indexQ = 0;
                QuestionDAO questionDao = new QuestionDAO_MySQL();
                QuestionImpl question = (QuestionImpl)questionDao.getQuestionByNumberPosition(index, pollId);
                int answerNumber = question.getAnswersNumber();
                String checkText = "";
                if (question.getCheck()==1)
                    checkText = " *";
                OUTPUT += "<div><h3>"+ (index+1) +". "+ question.getText() + checkText + "</h3>";
                OUTPUT += "<div class=\"item-poll-list\">";
                //OUTPUT += " ANSWER NUMBER: "+ answerNumber;

                //domanda a risposta esclusiva
                if(question.getType()==0){
                    while (indexQ<answerNumber){
                        AnswerDAO answerDao = new AnswerDAO_MySQL();
                        String answerText =  answerDao.getAnswerByNumberPosition(indexQ, index, pollId);

                        //domanda a risposta esclusiva
                        if (indexQ == 0 & (question.getCheck()==1))
                            OUTPUT += "<div><INPUT TYPE=\"RADIO\" VALUE=\""+pollId+"Q"+(999-index)+"A"+(999-indexQ)+"\" NAME=\"question"+index+"\" checked> ";
                        else
                            OUTPUT += "<div><INPUT TYPE=\"RADIO\" VALUE=\""+pollId+"Q"+(999-index)+"A"+(999-indexQ)+"\" NAME=\"question"+index+"\"> ";
                        OUTPUT += " "+answerText + "</div>";
                        indexQ++;
                    }
                }
                //domanda a risposta multipla
                if(question.getType()==1){
                    while (indexQ<answerNumber){
                        AnswerDAO answerDao = new AnswerDAO_MySQL();
                        String answerText =  answerDao.getAnswerByNumberPosition(indexQ, index, pollId);

                        if (indexQ == 0 & (question.getCheck()==1))
                            OUTPUT += "<div><INPUT TYPE=\"checkbox\" NAME=\"question"+index+"a"+indexQ+"\" checked> ";
                        else
                            OUTPUT += "<div><INPUT TYPE=\"checkbox\" NAME=\"question"+index+"a"+indexQ+"\"> ";
                        OUTPUT += " "+answerText + "</div>";
                        indexQ++;
                    }
                } 
                //domanda con input
                if(question.getType()==2){
                        if (question.getCheck()==1)
                            OUTPUT += "<div><textarea PLACEHOLDER=\"Insert Answer\" value='' NAME=\"question"+index+"\" maxlength=\"100\"></textarea>";
                        else
                            OUTPUT += "<div><textarea PLACEHOLDER=\"Insert Answer\" NAME=\"question"+index+"\" value='' maxlength=\"100\"></textarea>";
                }
                //domanda con numeri
                if(question.getType()==3){
                        if (question.getCheck()==1)
                            OUTPUT += "<div><INPUT class='input-number' TYPE=\"number\" VALUE=\""+0+"\" NAME=\"question"+index+"\" required> ";
                        else
                            OUTPUT += "<div><INPUT class='input-number' TYPE=\"number\" NAME=\"question"+index+"\"> ";                
                }

                OUTPUT += "</div></div>";
                OUTPUT += "<br/>";

                index++;
            }
            OUTPUT += "<input class='submit-button' type=\"submit\" form=\"pollVote\" value=\"Submit\"></form>";
            OUTPUT += "<br/><br/>";
            OUTPUT += "<div class='item-poll-listv4'>Share it with your friend"
                    + "<div><input class='input-text'  id='copyLink' name='copyLink' type='text' value='"+url+"' readonly>"
                    + "<button class='rainbow-button' type='button' onclick='copyLinkInput()'><span class='rainbow-button-after'>Copy</span></button></div>"
                    + "</div>";
            
        }
        
        
        else{
            
            if (userStatus==10||userStatus==100||userStatus==110){
 
                OUTPUT += "<form id='closePoll' method='post' action='CloseServlet'>"
                        + "<input hidden type='text' name='pollId' value='"+pollId+"'>"
                        + "<input hidden type='text' name='userId' value='"+userId+"'>"
                        + "<button style=\"text-align: right;float:right;\"><i class=\"material-icons\" style=\"font-size:32px;color:red;\">close</i></button>"
                        + "</form>";
            }
            OUTPUT += "<div id=\"pollVote\" >";
            OUTPUT += "<h2>" + poll.getTitle() +"</h2><br/>";
            OUTPUT += "<input type=\"text\" name=\"pollId\" hidden value=\""+pollId+"\">";

            int questionNumber = poll.getQuestionsNumber();
            int index = 0;
            while(index<questionNumber){
                int indexQ = 0;
                QuestionDAO questionDao = new QuestionDAO_MySQL();
                QuestionImpl question = (QuestionImpl)questionDao.getQuestionByNumberPosition(index, pollId);
                int answerNumber = question.getAnswersNumber();
                String checkText = "";
                if (question.getCheck()==1)
                    checkText = " *";
                OUTPUT += "<div><h3>"+ (index+1) +". "+ question.getText() + checkText + "<i  class='align-span-right'>Votes</i></h3>";
                OUTPUT += "<div class=\"item-poll-list\">";
                //OUTPUT += " ANSWER NUMBER: "+ answerNumber;

                while (indexQ<answerNumber){
                    AnswerDAO answerDao = new AnswerDAO_MySQL();
                    String answerText =  answerDao.getAnswerByNumberPosition(indexQ, index, pollId);
                    int answerVotes =  answerDao.getVotesByNumberPosition(indexQ, index, pollId);
                    OUTPUT += "<div>"+answerText + "<span  class='align-span-right'>"+answerVotes+"</span></div>";
                    indexQ++;
                }
                
                OUTPUT += "</div></div>";
                OUTPUT += "<br/>";

                index++;
            }
            OUTPUT += "<br/>";
            OUTPUT += "<div class='item-poll-listv4'>Share it with your friend"
                    + "<div><input class='input-text'  id='copyLink' name='copyLink' type='text' value='"+url+"' readonly>"
                    + "<button class='rainbow-button' type='button' onclick='copyLinkInput()'><span class='rainbow-button-after'>Copy</span></button></div>"
                    + "</div>";
            
        }
        
        elaborate.put("formServlet", OUTPUT);

        if ((Map<String,String>)request.getAttribute("pollInfo")!=null){
            request.removeAttribute("pollInfo");
        }
        if(!checkPassword){ 
            String OUTPUT2 = "";
            OUTPUT2+= "<form name=\"passwordServlet\" action=\"PasswordServlet\" method=\"post\">Insert password: "
                    + "<input type='text' id='password' name='password'>"
                    + "<input type='text' id='pollId' name='pollId' hidden value='"+pollId+"'>"
                    + "<input type='text' id='userId' name='userId' hidden value='"+userId+"'>"
                    + "<br/>"
                    + "<input type='submit'></form>";
            elaborate.put("txt1", OUTPUT2);
            request.setAttribute("error", errMessage);
            request.setAttribute("message", elaborate);
            request.getRequestDispatcher("/success.jsp").forward(request, response);//forwarding the request
        }
        
        request.setAttribute("error", errMessage);
        request.setAttribute("pollInfo", elaborate);
        
        request.getRequestDispatcher("/userpolls.jsp").forward(request, response);//forwarding the request
         
    }
         

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doGet(request, response);
    }
}
