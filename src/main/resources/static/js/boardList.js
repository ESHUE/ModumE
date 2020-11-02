function goToTop() {
	let location = document.querySelector('.boardContents');
	location.scrollTop = 0;
}

function goToEditor(boardDetail) {
   const boardContainer = document.querySelector('.boardContainer');
	fetch('/boardRegMod').then(function(response) {
		console.log(response);
		response.text().then(function(text) {
			boardContainer.innerHTML = text;
		}).then(function() {
         CKEDITOR.replace('boardRegModTexarea',
							{filebrowserUploadUrl:'/imageUpload'
							 , resize_enabled: false
							 , height: '34vh'
							 });
		modifyPost(boardDetail);
		//console.log(${loginType});
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
	const boardContainer = document.querySelector('.boardContainer');
	const param = {
		boardseq: board_seq
	}
	
	const fetchOpt = {
		method: 'POST',
		headers: {
		    'Content-Type': 'application/json'
		},
		body: JSON.stringify(param)
	}
	
	fetch('/boardDetail', fetchOpt).then(function(response) {
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



