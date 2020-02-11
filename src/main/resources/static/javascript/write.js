const topic = document.getElementById('topic');
const question = document.getElementById('question');
const topicbutton = document.getElementsByClassName('topicbutton');
const questionbutton = document.getElementsByClassName('questionbutton');

for (let i = 0; i < topicbutton.length; i++) {
  topicbutton[i].onclick = function() {
    question.style.display = "none";
    topic.style.display = "block";
  }
}

for (let i = 0; i < questionbutton.length; i++) {
  questionbutton[i].onclick = function() {
    topic.style.display = "none";
    question.style.display = "block";
  }
}
