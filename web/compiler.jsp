<%-- 
    Document   : profile
    Created on : 24-gen-2020, 17.06.59
    Author     : gigan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Compile a new poll</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="css/customain.css">
	<link rel="stylesheet" type="text/css" href="css/buttons.css">
<!--===============================================================================================-->
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>  
        <script src="js/addQuestions.js"></script>
</head>
<body>
    <div class="header">        
        <div class="header-icon"></div>
        
        <div class="navbar-top-menu">
            <nav>
                <ul>
                    <li>
                        <div class="navbar-top-menu-item-container">
                            <a href="LogoutServlet" class="navbar-top-menu-item-navigator">
                                
                                    <i class="material-icons">logout</i>
                                
                            </a>
                        </div>
                    </li>
                    <li>
                        <div class="navbar-top-menu-item-container">
                            <a href="ProfileServlet" class="navbar-top-menu-item-navigator"><span>POLLS</span></a>
                        </div>
                    </li>
                    <li>
                        <div class="navbar-top-menu-item-container">
                            <a href="ProfileServlet" class="navbar-top-menu-item-navigator"><span>PROFILE</span></a>
                        </div>
                    </li>
                    <li>
                        <div class="navbar-top-menu-item-container">
                            <a class="navbar-top-menu-item-navigator" href="HomeServlet"><span>HOME</span></a>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>
        
        
    </div>
    <div class="bg-main-bodyv2">
        <div class="wrap-customp2v2">
                
                
        <form id="createPoll" action="CreatePollController" method="post">
            <h2>
                Create a Poll
            </h2>
            <br/>
            <h3>Title</h3>
            <div>
                <input class="input-text" type="text" id ="poll_title" placeholder="Poll Title" name="poll_title">
            </div>
            <br/>
            <p class="ques-number-div">&nbsp;&nbsp;Number of questions:&nbsp;
                <input class="ques-number-p" type="number" id="questionNumber" name="questionNumber" value="0" readonly></a><br />
            </p>
         <div class="big-container">   
            <p id="question" name="question" hidden></p>
            <div class="item-poll-list">
                <div  id="container"></div>
                <button class="add" type="button" onclick="addQuestion()">+</button>
            </div>
            </div>    
            <br/>
            <div class="item-poll-listv4">Set a password for this poll
                <input type="checkbox" name="pollPasswordCheck" id="pollPasswordCheck"/><br/>
                <input class="input-text" type="text" id="pollPassword" name="pollPassword"/>
            </div>
                <br/>
            <div>
                <input class="submit-button" value="Submit" type="submit" name="Send">
            </div>
        </form>
                 

        </div>
    </div>
    
    
	
<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>