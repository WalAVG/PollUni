
 
        function readMessage(){ 
                
        
                var obj = {a: "GG",
                    b: "Error"
                }
                
                $(document).ready(function(){
                    //pick "pollID" from the form
                        var id = $("#pollID").val();
                        $.ajax({
                           type: "POST"
                           ,
                           url: "PollByIdController", //controller
                           data: {id: id},
                           dataType: "text",
                           success: function(response){
                               $("#pollByIdSearchList").append(response); 
                           },
                           error: function(){
                               document.getElementById("pollByIdSearchList").innerHTML = obj.b;
                           }
                        });
                });
                 
        }
        
        
        
        