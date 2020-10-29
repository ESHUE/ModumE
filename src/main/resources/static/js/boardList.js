function goToTop() {
	let location = document.querySelector('.boardContents');
	location.scrollTop = 0;
}

function goToEditor(boardDetail) {
   const boardContainer = document.querySelector('.boardContainer');
	fetch('/boardRegMod').then(function(response) {
		response.text().then(function(text) {
			boardContainer.innerHTML = text;
		}).then(function() {
         CKEDITOR.replace('boardRegModTexarea',
							{filebrowserUploadUrl:'/imageUpload'
							 , resize_enabled: false
							 , height: '34vh'
							 });
		modifyPost(boardDetail);
		 
      })
	})
}

function modifyPost(boardDetail) {
	const form = document.querySelector('#board-regMod__form');
	let title = boardDetail;
	if(boardDetail == undefined) {
		title = '';
	}
	CKEDITOR.instances.boardRegModTexarea.setData(boardDetail);
	form.title.value = title;
}

function goToDetail(board_seq) {
	const form = document.querySelector('#board-regMod__form');
	/*
	const fetchOpt = {
		method: 'GET',
  		headers: {
		    'Content-Type': 'application/json'
		}
		body: JSON.stringify({USERBOARD_SEQ: board_seq})
	}
	*/
	
	fetch('/boardDetail').then(function(response) {
		response.text().then(function(text) {
			boardContainer.innerHTML = text;
		})
	})
}

/*
function removeAllChild(ele) {
	while(ele.firstChild) {
		ele.removeChild(ele.lastChild);
	}
}
*/



