const helpbtn = document.getElementById('help-btn');
const helpmodal = document.getElementById('help');
const closebtn = document.getElementsByClassName('close')[0];

helpbtn.onclick = function() {
  helpmodal.style.display="block";
}

closebtn.onclick = function() {
  helpmodal.style.display="none";
}

window.onclick = function(event) {
  if(event.target==helpmodal){
    helpmodal.style.display="none";
  }
}