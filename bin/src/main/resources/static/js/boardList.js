function goToTop() {
	let location = document.querySelector('.boardContents');
	location.scrollTop = 0;
}

function goToEditor(boardseq) {
   const boardContainer = document.querySelector('.boardContainer');
	fetch('/boardRegMod').then(function(response) {
		if(response.status == 404) {
			alert('로그인이 필요합니다!');
			location.href = '/main';
			return;
		}
		response.text().then(function(text) {
			boardContainer.innerHTML = text;
		}).then(function() {
         CKEDITOR.replace('boardRegModTexarea',
							{filebrowserUploadUrl:'/imageUpload'
							 , resize_enabled: false
							 , height: '34vh'
							 });
		if(boardseq) {
			const url = '/boardRegMod';
			const param = {
				boardseq: boardseq
			};
			
			axios.post(url, param).then(function(res) {
				//console.log(res.data); 
				const boardDetail = res.data;
				
				const form = document.querySelector('#board-regMod__form');
				
				CKEDITOR.instances.boardRegModTexarea.setData(boardDetail.content, function(){
                CKEDITOR.instances.boardRegModTexarea.setData(boardDetail.content);
              	})

				form.title.value = boardDetail.title;
				form.boardseq.value = boardseq;
			})
		}
      })
	})
}

function modifyPost(title, content) {
	const form = document.querySelector('#board-regMod__form');
	if(title == undefined) {
		title = '';
	}
	CKEDITOR.instances.boardRegModTexarea.setData(content);
	form.title.value = title;
}

function goToDetail(boardseq) {
	const boardContainer = document.querySelector('.boardContainer');
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



