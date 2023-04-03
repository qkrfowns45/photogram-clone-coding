/**
  1. 유저 프로파일 페이지
  (1) 유저 프로파일 페이지 구독하기, 구독취소
  (2) 구독자 정보 모달 보기
  (3) 구독자 정보 모달에서 구독하기, 구독취소
  (4) 유저 프로필 사진 변경
  (5) 사용자 정보 메뉴 열기 닫기
  (6) 사용자 정보(회원정보, 로그아웃, 닫기) 모달
  (7) 사용자 프로파일 이미지 메뉴(사진업로드, 취소) 모달 
  (8) 구독자 정보 모달 닫기
 */

// (1) 유저 프로파일 페이지 구독하기, 구독취소
function toggleSubscribe(toUserId,obj) {
	if ($(obj).text() === "구독취소") {
		
		$.ajax({
			type:"delete",
			url:"/api/subscribe/"+toUserId,
			dataType:"json"
		}).done(res =>{
			$(obj).text("구독하기");
			$(obj).toggleClass("blue");
		}).fail(error =>{
			console.log("구독취소실패",error);
		});
		
		
	} else {
		
		$.ajax({
			type:"post",
			url:"/api/subscribe/"+toUserId,
			dataType:"json"
		}).done(res =>{
			$(obj).text("구독취소");
			$(obj).toggleClass("blue");
		}).fail(error =>{
			console.log("구독하기실패",error);
		});
	}
}











