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
		console.log(res)
		
		fetch('/boardList').then(function(response) {
		response.text().then(function(text) {
			document.querySelector('.boardContainer').innerHTML = text;
			})
		})
	})
}

function goToComment() {
	fetch('/comment').then(function(response) {
		response.text().then(function(text) {
			document.querySelector('.boardContainer').innerHTML = text;
			})
		})
}