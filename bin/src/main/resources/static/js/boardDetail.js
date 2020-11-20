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
	if(!confirm('삭제하시겠습니까?')) {
		return;
	}
	
	axios.get('/boardDel', {
		params: {
			boardseq: boardseq
		}
	}).then(function(res){
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
			document.querySelector('.boardContainer').innerHTML = text;
			})
		})
}