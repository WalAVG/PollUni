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
public class CreatePollController extends HttpServlet {
    
    public CreatePollController(){
        
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String title = request.getParameter("poll_title");
        int questionsNumber = Integer.parseInt(request.getParameter("questionNumber"));
        String pollPasswordCheck = request.getParameter("pollPasswordCheck");
        
        Map<String,String> errMessage = new HashMap<String,String>();
        Map<String,String> userLogged = new HashMap<String,String>();
        int userId = 1; 
      
        //new poll
        PollImpl poll = new PollImpl();
        PollDAO pollDao = new PollDAO_MySQL();
        int pollId = 0;
        if (title!=""){
            pollId = pollDao.newId();
        }
        poll.setID(pollId);
        poll.setQuestionsNumber(questionsNumber);
        
        poll.setTitle(title);
        poll.setAuthorId(userId);
        if (pollPasswordCheck!=null){
            poll.setLimited(true);
            poll.setLimPassword(request.getParameter("pollPassword"));
        }    
        else{
            poll.setLimited(false);
            poll.setLimPassword(null);
        }
        
        HttpSession session=request.getSession(false);  
        if(session!=null){  
            userLogged = (Map<String,String>)session.getAttribute("user");
            int userLoggedId = Integer.parseInt(userLogged.get("id"));
            poll.setAuthorId(userLoggedId);
        }  
        else{
            errMessage.put("error","Invalid Session");

            request.setAttribute("errMessage", errMessage); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
            request.getRequestDispatcher("/error.jsp").forward(request, response);//forwarding the request
  
        }
        
        //can't go back
        String pollCreation="";
        if(title==""){
            errMessage.put("error","Invalid Title"); 
            request.setAttribute("errMessage", errMessage); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
            request.getRequestDispatcher("/error.jsp").forward(request, response);//forwarding the request
        }
        else
            pollCreation = pollDao.createPoll(poll);

        if(pollCreation.equals("SUCCESS"))
            {
             request.getRequestDispatcher("/success.jsp").forward(request, response);
            
            }
        else
            {
                request.setAttribute("errMessage", pollCreation); 
                request.getRequestDispatcher("/error.jps").forward(request, response);
            }
        String questionText;
        int checkAnswer = 0;
        int i = 0;
        //add a new question for each q_number submitted
        while(i<questionsNumber){
            questionText = request.getParameter("question"+i);
            String quesCheck = request.getParameter("checkanswer"+i);
            int questionType =  Integer.parseInt(request.getParameter("qType"+i));
            //QuestionImpl question = new QuestionImpl();
            QuestionImpl question = new QuestionImpl(pollId, i, questionText, questionType);
            
            if (quesCheck!=null)
                checkAnswer = 1;
            else
                checkAnswer = 0;
            question.setCheck(checkAnswer);
            
            QuestionDAO questionDao = new QuestionDAO_MySQL();
            String questionCreation = questionDao.createQuestion(question);
            if(questionCreation.equals("FAIL"))
            {
                request.setAttribute("errMessage", questionCreation); 
                request.getRequestDispatcher("/error.jps").forward(request, response);
            }
            int j = Integer.parseInt(request.getParameter("answer" + i));
            while(j>0){
                j--;
                AnswerImpl answer = new AnswerImpl();
                answer.setPosition(j);
                answer.setQuestionID(questionCreation);
                answer.setID(questionCreation + "A" + (999-j));
                if (questionType==3){
                    answer.setNumber(Float.parseFloat(request.getParameter("answerC" + i + "A" + j)));
                }
                else {
                    answer.setText(request.getParameter("answerC" + i + "A" + j));
                }
                AnswerDAO answerDao = new AnswerDAO_MySQL();
                String answerCreation = answerDao.createAnswer(answer);
                if (answerCreation.equals("SUCCESS")){
                    
                }
                else{
                    request.setAttribute("errMessage", questionCreation); 
                    request.getRequestDispatcher("/error.jps").forward(request, response);
                }
            }
            i = i+1;
            
        }
  
    }
    
}
