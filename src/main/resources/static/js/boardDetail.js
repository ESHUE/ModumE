/*
function backToList() {
	const boardContainer = document.querySelector('.boardContainer');
	fetchBoardListToBack(boardContainer);
}

function fetchBoardListToBack(ele) {
	fetch('/boardList').then(function(response) {
		response.text().then(function(text) {
			ele.innerHTML = text;
		})
	})
}
*/

function deleteBoard(boardseq) {
	if (!confirm('삭제하시겠습니까?')) {
		return;
	}

	axios.get('/boardDel', {
		params: {
			boardseq: boardseq
		}
	}).then(function(res) {
		//console.log(res)

		alert('게시글이 삭제되었습니다.');
		fetch('/boardList').then(function(response) {
			response.text().then(function(text) {
				document.querySelector('.boardContainer').innerHTML = text;
			})
		})
	})
}

function goToComment(boardseq) {

	const boardContainer = document.querySelector('.boardContainer');

	boardContainer.innerHTML = `<div class="comment-menu">
								    <div class="comment-menu1">
								        <div id="allComment" class="cursor bold" onclick="changeByWriter(true); showComment(${boardseq}, this)">전체</div>
								        <div id="myComment" class="cursor" onclick="changeByWriter(false); showComment(${boardseq}, this)">내 댓글</div>
								    </div>
								    <div class="comment-menu2">
								        <div id="showASC" class="cursor bold" onclick="changeorderBy(true); showComment(${boardseq}, this)">오름차순</div>
								        <div id="showDESC" class="cursor" onclick="changeorderBy(false); showComment(${boardseq}, this)">내림차순</div>
								    </div>
								</div>`;

	let commentContents = document.createElement('div');
	commentContents.classList.add('commentContents');
	commentContents.classList.add('boardContents');

	boardContainer.append(commentContents);

	const param = {
		boardseq: boardseq
	}

	const fetchOpt = {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(param)
	}

	// axios boardseq로 commentList 뽑아야 함
	fetch('/comment', fetchOpt).then(function(response) {
		response.text().then(function(text) {
			//			document.querySelector('.boardContainer').innerHTML = text;
			commentContents.innerHTML = text;
			
				boardContainer.innerHTML += `<form id="commentIns-form" class="comment-option-box flex-center">
											    <div class="board-btns comment-backTop-btns">
											        <!-- 뒤로가기 버튼 -->
											        <div class="go-to-back board-detail-back cursor circle-btn50" onclick="goToDetail(${boardseq})">
											            <span class="material-icons">arrow_back</span>
											        </div>
											        <!-- top 버튼 -->
											        <div class="go-to-top board-detail-top cursor circle-btn50" onclick="goToTop()">
											            <span class="material-icons">arrow_upward</span>
											        </div>
											    </div>
											    <div class="comment-text-div">
											        <textarea name="comment" class="commentIns" onkeyup="cutComment(this)" placeholder="댓글을 입력해주세요."></textarea>
											    </div>
											    <input type="hidden" name="commentseq" value="0">
											    <div class="submitCancle-btns">
											        <div onclick="formSubmit(${boardseq})" class="rectangle-small-btn commentIns-btn">
												        등록
												    </div>
												    <div onclick="cancleComment()" class="rectangle-small-btn commentCancle-btn">
												        취소
												    </div>
											    </div>
											</form>`;
		})
	})
}