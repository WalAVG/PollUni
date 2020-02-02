import java.io.IOException;  
import java.io.PrintWriter;  
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
public class HomeServlet extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                      throws ServletException, IOException {  
        response.setContentType("text/html");  
          
        Map<String, String> elaborate = new HashMap<String, String>(); 

        HttpSession session=request.getSession(false);  
        if(session!=null){  
            String name=(String)session.getAttribute("name");   
            
            PollDAO pollDao = new PollDAO_MySQL();
            String OUTPUT = "<div class=\"conatiner\">";
            OUTPUT += "<div class=\"wrap\">";
            int index = 1;
            String boxTextNumber = "one";
            String url = "";
            while(index<10){
                Poll poll = new PollImpl();
                poll = pollDao.getLastPoll(index);
                index++;
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
            OUTPUT += "</div></div>";
            elaborate.put("pollCardServlet", OUTPUT);
 
            request.setAttribute("hotPoll", elaborate);
            request.getRequestDispatcher("home.jsp").include(request, response); 
        }  
        else{  
            request.getRequestDispatcher("login.html").include(request, response);  
        }  
    }  
}  