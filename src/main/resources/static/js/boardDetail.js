function backToList() {
	const boardContainer = document.querySelector('.boardContainer');
	removeAllChild(boardContainer);
	fetchBoardListToBack(boardContainer);
}

function fetchBoardListToBack(ele) {
	fetch('/boardList').then(function(response) {
		response.text().then(function(text) {
			ele.innerHTML = text;
		})
	})
}
