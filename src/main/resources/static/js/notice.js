function init() {
    $('.slideContainer').html("<div class='noticeContainer'></div>");
    $('.noticeContainer').css('width', '80%')
    .css('height', '85%')
    .css('border-radius', '5px')
    .css('color', 'seashell')
	.css('position','absolute')
    .html(
        '<br><br>' + 
        "<div class='notice_title'>모두미에 오신 것을 환영합니다.<br><br></div>" + 
        "<div class='notice_content'>모두미는 여러 플랫폼의 영상과 방송을 한 곳에서 시청할 수 있도록 만들어진 웹사이트입니다.<br><br><br>" + 
        "<p style='line-height: 2em;'>모두미는 비로그인 시 다음과 같은 기능을 제공합니다.</p>" + 
        "● 유튜브 영상 검색 및 시청 기능 제공<br><br>" + 
        "<p style='line-height: 2em;'>모두미는 로그인 시 다음과 같은 기능을 제공합니다.</p>" + 
        "● 유튜브 영상 검색 및 시청 기능 제공<br>" + 
        "● 생방송 중인 스트리머의 실시간 방송 리스트 제공(트위치 로그인 시)<br>" + 
        "● 방송 및 동영상 미시청 시 유저 전체 채팅 기능 제공<br>" + 
        "● 방송 및 동영상 시청 시 같은 영상을 보는 사람들끼리 채팅 기능 제공<br>" + 
        "● 자유게시판 이용 가능<br>" + 
        "</div>"
        );

    $('.notice_title').css('font-size', '2em')
    .css('font-weight', 'bolder')
    $('.notice_content').css('line-height', '1.5em')
    .css('font-size', '1.1em')
    .css('overflow', 'auto')
}

init();