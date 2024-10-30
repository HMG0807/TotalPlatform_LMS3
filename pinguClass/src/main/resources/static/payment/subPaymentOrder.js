

     $("#paymentBtn").on("click", function () {
            var userid = $("#userid").val();
            var username = $("#username").val();

            var merchant_uid = "O" + new Date().getTime(); // 고유한 주문번호 생성 

            var IMP = window.IMP;
            IMP.init('imp73311280'); // 가맹점 식별코드 입력 

            IMP.request_pay({
                pg: "kakaopay",           // 등록된 pg사 (적용된 pg사는 KG이니시스)
                pay_method: "card",           // 결제방식: card(신용카드), trans(실시간계좌이체), vbank(가상계좌), phone(소액결제)
                merchant_uid: merchant_uid,   // 주문번호
                name: "개목걸이",                  // 상품명
                amount: "1000000",           // 금액
                buyer_name: "군침이",         // 주문자
                buyer_tel: "01012345678",             // 전화번호 (필수입력)
                buyer_addr: "안산시",    		  // 주소
                buyer_postcode: "12343"          // 우편번호
            }, function (rsp) {
                if (rsp.success) {
                    var mesg = '결제가 완료되었습니다.';
                  	
                  	alert(mesg);
                      // 겅증 후 결제 정보 & 주문 정보 DB 저장

                } else {
                    var mesg = '결제를 실패하였습니다.';
                    alert(msg);
                }
            }
            );
        });
        

		