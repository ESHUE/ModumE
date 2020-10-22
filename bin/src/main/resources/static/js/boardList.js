function goToTop() {
	let location = document.querySelector('.boardContents');
	location.scrollTop = 0;
}

function removeAllChild(ele) {
	while(ele.firstChild) {
		ele.removeChild(ele.lastChild);
	}
}


function goToDetail() {
	const boardContainer = document.querySelector('.boardContainer');		
	removeAllChild(boardContainer);
	fetchBoardDetail(boardContainer);
}

function fetchBoardDetail(ele) {
	fetch('/boardDetail').then(function(response) {
		response.text().then(function(text) {
			ele.innerHTML = text;
		})
	})
}