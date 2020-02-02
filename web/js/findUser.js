/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 
        function findUser(){
                /*//get input from id="userID"
                var number = document.getElementById("userID").value;
                //clear previous findUser container
                var container = document.getElementById("userSearchList");
                while (container.hasChildNodes()){
                    container.removeChild(container.lastChild);
                }*/
                
        
                var obj = {a: "GG",
                    b: "AHAHHAHA"
                }
                
                $(document).ready(function(){
                    //pick "userID" from the form
                        var id = $("#userID").val();
                        $.ajax({
                           type: "POST"
                           ,
                           url: "UserListController",
                           data: {id: id},
                           dataType: "text",
                           success: function(response){
                               $("#userSearchList").append(response);
                           },
                           error: function(){
                               document.getElementById("userSearchList").innerHTML = obj.b;
                           }
                        });
                });
                 
        }
        
        
        
        