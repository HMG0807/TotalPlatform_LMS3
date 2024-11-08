// Get the edit button and input fields
var editBtn = document.getElementById("editBtn");
var titleDisplay = document.getElementById("titleDisplay");
var titleInput = document.getElementById("titleInput");
var contentDisplay = document.getElementById("contentDisplay");
var contentInput = document.getElementById("contentInput");
var saveCancelBtns = document.getElementById("saveCancelBtns");

// Get the save and cancel buttons
var saveBtn = document.getElementById("saveBtn");
var cancelBtn = document.getElementById("cancelBtn");

// Get the QnA ID from the hidden input
var qnaId = document.getElementById("qnaId").value; // QnA ID 가져오기

// When the user clicks the edit button, show the input fields and save/cancel buttons
editBtn.onclick = function() {
  titleDisplay.style.display = "none";  // Hide the display version of the title
  titleInput.style.display = "block";   // Show the input field for the title

  contentDisplay.style.display = "none";  // Hide the display version of the content
  contentInput.style.display = "block";   // Show the textarea for the content

  saveCancelBtns.style.display = "block"; // Show save and cancel buttons
  editBtn.style.display = "none";         // Hide the edit button
}


cancelBtn.onclick = function() {
  titleDisplay.style.display = "block";   
  titleInput.style.display = "none";      

  contentDisplay.style.display = "block"; 
  contentInput.style.display = "none";    

  saveCancelBtns.style.display = "none";  
  editBtn.style.display = "block";        


saveBtn.onclick = function() {
  var formActionUrl = "/mypage/qna/edit/" + qnaId; // QnA ID를 사용하여 URL 생성
  
  const formData = {
    title: titleInput.value,
    content: contentInput.value,
  };

  fetch(formActionUrl, {
    method: "POST",
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(formData),
  })
  .then(response => response.json())
  .then(data => {
    if (data.success) {
      // 성공 시 UI 업데이트 또는 페이지 리로드
      window.location.reload();
    } else {
      alert('수정에 실패했습니다.');
    }
  })
  .catch(error => console.error('Error:', error));
}