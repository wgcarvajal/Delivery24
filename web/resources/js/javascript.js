/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var x = document.createElement("AUDIO");
 x.setAttribute("id","pruebaaudio");  
          x.setAttribute("src","/delivery24/faces/resources/js/sonido_admin.mp3");
          document.body.appendChild(x);

function playsound()
{
          
  x.play();
}

function pausar()
{  
          
    x.pause();
}

function handleComplete(xhr, status, args) {    

    if(args.respuesta)
    {
        playsound();
    }    
   
}

function handleCompleteStop(xhr, status, args) {    

    pausar();
    x.currentTime = 0;   
}




