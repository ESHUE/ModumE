function goToTop() {
	let location = document.querySelector('.boardContents');
	location.scrollTop = 0;
}

/*
function removeAllChild(ele) {
	while(ele.firstChild) {
		ele.removeChild(ele.lastChild);
	}
}
*/


function changeLocation(location) {
	const boardContainer = document.querySelector('.boardContainer');		
	fetchBoard(boardContainer, location);
}

function fetchBoard(ele, location) {
	fetch(location).then(function(response) {
		response.text().then(function(text) {
			ele.innerHTML = text;
		})
	})
}