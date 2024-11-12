let orderInputArr = document.querySelectorAll(".orderVal"); //값 배열
let minusBtns = document.querySelectorAll(".minus"); //'-' 버튼
let plusBtns = document.querySelectorAll(".plus"); //'+' 버튼

//'-' 버튼 개수만큼
for(let i=0; i<minusBtns.length; i++){
	//마이너스 버튼 클릭시
	minusBtns[i].addEventListener('click',function(){
		//1보다 작거나 강의 개수보다 클 떄 작동 X
		if(orderInputArr[i].value > 1 && orderInputArr[i].value < orderInputArr.length+1){
			orderInputArr[i].value = orderInputArr[i].value - 1; // -1
			orderValCheck(orderInputArr[i].value); //똑같은 값이 있을 경우 빨간색 표시
		}
	});
}

//플러스 버튼 클릭시
for(let i=0; i<plusBtns.length; i++){
	plusBtns[i].addEventListener('click',function(){
		if(orderInputArr[i].value > 0 && orderInputArr[i].value < orderInputArr.length){
			orderInputArr[i].value = parseInt(orderInputArr[i].value) + 1;
			orderValCheck(orderInputArr[i].value);
		}
	});
}

//변경 완료 버튼 클릭시
function changeSubmit(){
	
	//중복 검사 > 중복값이 있다면 리턴
	for(let i=0; i<orderInputArr.length; i++) {
	  let check = orderInputArr[i].value;
	  
	  for(let j=i+1; j<orderInputArr.length; j++) {
	    if(check == orderInputArr[j].value) {
	      alert("강의 순서는 중복값이 올 수 없습니다.");
	      return;
	    }
	  }
	}
	
	//전송
	 document.querySelector("#orderform").submit();
}

//'-' or '+' 누를때마다 함수 호출
function orderValCheck(changeValue){

	for(let i=0; i<orderInputArr.length; i++) {
	  let check = orderInputArr[i].value;
	  
	  for(let j=i+1; j<orderInputArr.length; j++) {
	    if(check == orderInputArr[j].value) { //중복값이 있을 경우
	      changeColor(check); //글자색 변경
	      return;
	    }
	  }
	}
	
	//중복값 체크가 하나도 걸리지 않았을 때
	for(let i=0; i<orderInputArr.length; i++) {
		
		orderInputArr[i].style.color = "black";
	}
}

//중복 값 색 변경
function changeColor(sameValue){
	
	for(let i=0; i<orderInputArr.length; i++) {
		
		if(sameValue == orderInputArr[i].value){
			orderInputArr[i].style.color = "red";
		} else {
			orderInputArr[i].style.color = "black";
		}
	}
}


