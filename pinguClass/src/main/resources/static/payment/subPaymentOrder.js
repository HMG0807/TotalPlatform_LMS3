// 결제 버튼 클릭시
function success(subscId){
	location.href = "/subscriptionSuccess/"+ subscId;
}

// **********************결제금액 계산(업데이트)********************************

let totalPrice = parseInt(document.getElementById("totalPrice").value);

if(totalPrice === 30000){
	totalPrice = totalPrice*0.9;
}else if(totalPrice === 60000){
	totalPrice = totalPrice*0.8;
}else if(totalPrice === 120000){
	totalPrice = totalPrice*0.7;
}

let orderName = document.getElementById("subscribeType").value;

// **********************결제하기 버튼 눌렀을때 이벤트(결제정보를 DB에 저장시키기 위한 함수)********************************/


/////////////////////////랜덤한 4자리 알파벳을 만들기 위한 함수//////////////////////////////
const random = (length = 4) => {
  const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
  let str = '';
  for (let i = 0; i < length; i++) {
    str += chars.charAt(Math.floor(Math.random() * chars.length));
  }
  return str;
};

let orderId = random()+new Date().getTime(); // 랜덤 알파벳 + 오늘 날짜 = 주문번호

///////////////////////////////////////////////////////////
function requestTossPayment(orderId, totalPrice,orderName) {
    const tossPayments = TossPayments('test_ck_26DlbXAaV0MOP52Gyd6KrqY50Q9R');
    tossPayments.requestPayment('카드', {
        amount: totalPrice,
        orderId: orderId,
        orderName:orderName ,
        successUrl: window.location.origin + '/success',
        failUrl: window.location.origin + '/fail'
    }).catch(function (error) {
                if (error.code === 'USER_CANCEL') {
                    // 결제 고객이 결제창을 닫았을 때 에러 처리
                } else if (error.code === 'INVALID_CARD_COMPANY') {
                    // 유효하지 않은 카드 코드에 대한 에러 처리
                }
            })
}

const psf = document.getElementById("paymentBtn");
	psf.addEventListener('click',function(e){
		e.preventDefault();	
		requestTossPayment(orderId, totalPrice,orderName);
	})

///////////////////////////////////////////////////////////////////