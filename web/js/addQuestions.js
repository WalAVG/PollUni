/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var questionNumber = 0;




function addQuestion(){
    var container = document.getElementById("container");
    var i = questionNumber;
    var questionOrderNumber = document.createElement("p");
    questionOrderNumber.id = "questionOrderNumber"+i;
    questionOrderNumber.value = i;
    var container_i = document.createElement("div");
    container_i.id = "con" + i;
    container_i.value = i;
    var containerText = document.createElement("h3");
    containerText.innerHTML = ("Question " + (i+1));
    containerText.id = "conText" + i;
    var questionRemoveButton = document.createElement("button");    
    questionRemoveButton.class ="add";
    questionRemoveButton.innerHTML = "&nbsp;X&nbsp;";
    questionRemoveButton.type ="button";
    questionRemoveButton.onclick = function() {
        var f = 0;
        var s = 0;
        questionNumber--;
        var quesNumber = document.getElementById("questionNumber");
        quesNumber.value = questionNumber;
        quesNumber.innerHTML = questionNumber;
        container.removeChild(container_i);
        while (s<questionNumber+2){
            var atext = document.getElementById("conText"+s);
            if (atext!==null){
                var aques = document.getElementById("question"+s);
                var acon = document.getElementById("con"+s);
                var atype = document.getElementById("qType"+s);
                var aabutton = document.getElementById("button"+s);
                var acheck = document.getElementById("checkanswer"+s);
                var aanswer = document.getElementById("answer"+s);
                var acc = document.getElementById("cc"+s);
                var indice = 0;
                atext.innerHTML = "Question "+(f+1);
                atext.id = "conText"+f;
                acon.id = "con"+f;
                atype.id = "qType"+f;
                aques.id = "question"+f;
                aabutton.id = "button"+f;
                aques.name = "question"+f;
                atext.name = "conText"+f;
                acon.name = "con"+f;
                atype.name = "qType"+f;
                aabutton.name = "button"+f;
                
                aanswer.id = "answer"+f;
                acheck.id = "checkanswer"+f;
                acc.id = "cc"+f;
                aanswer.name = "answer"+f;
                acheck.name = "checkanswer"+f;
                acc.name = "cc"+f;
                
                aabutton.onclick = function (){
                    var indexAdd = 0;
                    while (indexAdd<questionNumber+2){
                        var subAns = document.getElementById("answer"+indexAdd);                           
                        var subCC = document.getElementById("cc"+indexAdd);
                        if (subAns!==null){
                            var subValue = subAns.value;
                            for (j = 0; j < 10; j++){   
                                var inputChild = document.getElementById("answerC"+indexAdd+"A"+j);
                                var ictext1 = document.getElementById("ictext"+indexAdd+"A"+j);
                                if (j<subValue){
                                    if (inputChild===null){
                                        var ictext = document.createElement("p");
                                        ictext.id = "ictext"+indexAdd+"A"+j;
                                        ictext.name = "ictext"+indexAdd+"A"+j;
                                        ictext.innerHTML = "Answer "+(j+1)+" ";
                                        subCC.appendChild(ictext);                                        
                                        var newInputChild = document.createElement("input");
                                        newInputChild.id = "answerC"+indexAdd+"A"+j;
                                        newInputChild.name = "answerC"+indexAdd+"A"+j;
                                        newInputChild.type = "text";
                                        subCC.appendChild(newInputChild);            
                                    }
                                }
                                else{
                                    if (inputChild!== null ){
                                        subCC.removeChild(inputChild);
                                        subCC.removeChild(ictext1);
                                    }
                                }
                            }
                        }
                        indexAdd++;
                    } 
                };
                while (indice <10){
                    var aansinput = document.getElementById("answerC" + s + "A" + indice);
                    if(aansinput!==null){
                        aansinput.id = "answerC" + f + "A" + indice;
                        aansinput.name = "answerC" + f + "A" + indice;
                        var ictextex = document.getElementById("ictext"+s+"A"+indice);
                        ictextex.id = "ictext"+f+"A"+indice;
                        ictextex.name = "ictext"+f+"A"+indice;
                    }
                    indice++;
                }
                f++;                
            }
            s++;
        }
    };
    container_i.appendChild(containerText);
    var questionInput = document.createElement("input");
    questionInput.type = "text";
    questionInput.name = "question" + i;
    questionInput.id = "question" + i;
    questionInput.value = "";
    container_i.appendChild(questionInput);
    container_i.appendChild(questionRemoveButton);

    
    container_i.appendChild(document.createElement("br"));
                
    // answer number and type

    var questionType = document.createElement("select");
    questionType.class = "custom-select sources";
    questionType.id = "qType"+i;
    questionType.name = "qType"+i;
    var option1 = document.createElement("option");
    option1.value = "0";
    option1.text = "Single Box";
    var option2 = document.createElement("option");
    option2.value = "1";
    option2.text = "Multi Box";
    var option3 = document.createElement("option");
    option3.value = "2";
    option3.text = "Text Input";
    var option4 = document.createElement("option");
    option4.value = "3";
    option4.text = "Number Input";
    questionType.appendChild(option1);
    questionType.appendChild(option2);
    questionType.appendChild(option3);
    questionType.appendChild(option4);
    container_i.appendChild(questionType);
    var answerTextNumber = document.createElement("span");
    answerTextNumber.innerHTML = (" Insert number of answers: ");
    container_i.appendChild(answerTextNumber);
    var answer_button = document.createElement("button");
    answer_button.id = "button" + i;
    answer_button.type = "button";
    answer_button.innerHTML = "Add answers";
    answer_button.onclick = function (){
                    var indexAdd = 0;
                    while (indexAdd<questionNumber+2){
                        var subAns = document.getElementById("answer"+indexAdd);                           
                        var subCC = document.getElementById("cc"+indexAdd);
                        if (subAns!==null){
                            var subValue = subAns.value;
                            for (j = 0; j < 10; j++){   
                                var inputChild = document.getElementById("answerC"+indexAdd+"A"+j);
                                var ictext1 = document.getElementById("ictext"+indexAdd+"A"+j);
                                if (j<subValue){
                                    if (inputChild===null){
                                        var ictext = document.createElement("p");
                                        ictext.id = "ictext"+indexAdd+"A"+j;
                                        ictext.name = "ictext"+indexAdd+"A"+j;
                                        ictext.innerHTML = "Answer "+(j+1)+" ";
                                        subCC.appendChild(ictext);                                        
                                        var newInputChild = document.createElement("input");
                                        newInputChild.id = "answerC"+indexAdd+"A"+j;
                                        newInputChild.name = "answerC"+indexAdd+"A"+j;
                                        newInputChild.type = "text";
                                        subCC.appendChild(newInputChild);            
                                    }
                                }
                                else{
                                    if (inputChild!== null ){
                                        subCC.removeChild(inputChild);
                                        subCC.removeChild(ictext1);
                                    }
                                }
                            }
                        }
                        indexAdd++;
                    } 
                };
    
    var answer = document.createElement("input");
    answer.type = "number";
    answer.id = "answer" + i;
    answer.name = "answer" + i;
    answer.value = 1;
    answer.min = 1;
    answer.max = 10;
    container_i.appendChild(answer);
    container_i.appendChild(answer_button);  

    //checkbox for mustanswer type
    
    var checkAnswerText = document.createElement("div");
    checkAnswerText.innerHTML = "Check Answer: ";
    var checkAnswer = document.createElement("input");
    checkAnswer.type = "checkbox";
    checkAnswer.id = "checkanswer" + i;  
    checkAnswer.name = "checkanswer" + i;  
    checkAnswerText.appendChild(checkAnswer);
    container_i.appendChild(checkAnswerText);
    //new container for answers
                
    var container_c = document.createElement("div");
    container_c.id = "cc"+i;
    container_i.appendChild(container_c);
    // Append a line break 
    container_i.appendChild(document.createElement("br"));
    container.appendChild(container_i);
    questionNumber++;
    document.getElementById("questionNumber").value = questionNumber;
}


















    function addQuestions(){
            // Number of inputs to create
            var number = document.getElementById("question").value;
            // Container <div> where dynamic content will be placed
            var container = document.getElementById("container");
            // Clear previous contents of the container
            while (container.hasChildNodes()) {
                container.removeChild(container.lastChild);
            }
            var i = 0;
            while (i<number){
                var container_i = document.createElement("div");
                container_i.id = "con" + i;
                container_i.value = i;
                container_i.appendChild(document.createTextNode("Question " + (i+1)));
                // Create an <input> element, set its type and name attributes
                // question text
                var questionInput = document.createElement("input");
                questionInput.type = "text";
                questionInput.name = "question" + i;
                questionInput.id = "question" + i;
                questionInput.value = "";
                container_i.appendChild(questionInput);
                
                container_i.appendChild(document.createElement("br"));
                
                // answer number and type
                var questionType = document.createElement("select");
                questionType.id = "qType"+i;
                questionType.name = "qType"+i;
                var option1 = document.createElement("option");
                option1.value = "0";
                option1.text = "Single Box";
                var option2 = document.createElement("option");
                option2.value = "1";
                option2.text = "Multi Box";
                var option3 = document.createElement("option");
                option3.value = "2";
                option3.text = "Text Input";
                var option4 = document.createElement("option");
                option4.value = "3";
                option4.text = "Number Input";
                questionType.appendChild(option1);
                questionType.appendChild(option2);
                questionType.appendChild(option3);
                questionType.appendChild(option4);
                container_i.appendChild(questionType);
                
                var answer_button = document.createElement("button");
                answer_button.id = "button" + i;
                answer_button.type = "button";
                answer_button.innerHTML = "Add answers";
                answer_button.onclick = function (){
                    //get subcontainer id
                    var container_p = document.getElementById($(this).closest("div").attr("id"));
                    //get subcontainer value = i from the "for" loop
                    var conId = container_p.value;
                    //number of answers
                    var answerNum = document.getElementById("answer"+conId).value;
                    //get container child for shows the answers
                    var conChild = document.getElementById("cc"+conId);
                    while (conChild.hasChildNodes()){
                        conChild.removeChild(conChild.lastChild);
                    }
                    for (j = 0; j < answerNum; j++){
                        conChild.appendChild(document.createTextNode("Answer " + (j+1) ));
                        var inputChild = document.createElement("input");
                        inputChild.type = "text";
                        inputChild.name = "answerC" + conId + "A" + j;
                        conChild.appendChild(inputChild);
                        conChild.appendChild(document.createElement("br"));
                    }
                };
                container_i.appendChild(answer_button);  
                
                var answer = document.createElement("input");
                answer.type = "number";
                answer.id = "answer" + i;
                answer.name = "answer" + i;
                answer.value = 1;
                answer.min = 1;
                answer.max = 10;
                container_i.appendChild(answer);
                
                //checkbox for mustanswer type
                var checkAnswerText = document.createElement("div");
                checkAnswerText.innerHTML = "Check Answer: ";
                var checkAnswer = document.createElement("input");
                checkAnswer.type = "checkbox";
                checkAnswer.id = "checkanswer" + i;  
                checkAnswer.name = "checkanswer" + i;  
                checkAnswerText.appendChild(checkAnswer);
                container_i.appendChild(checkAnswerText);
                //new container for answers
                var container_c = document.createElement("div");
                container_c.id = "cc"+i;
                container_i.appendChild(container_c);


                      
                // Append a line break 
                container.appendChild(document.createElement("br"));
                container.appendChild(container_i);
                i = i +1;
            }
             
        }
        function addAnswer(i){
            var number = document.getElementById("answer_" + i).value;
            var container = document.getElementById("container_" + i);
            while (container.hasChildNodes()) {
                container.removeChild(container.lastChild);
            }
            for (j = 0; j < number; j++){
                container.appendChild(document.createTextNode("Answer " + (j+1)));

            }
        }