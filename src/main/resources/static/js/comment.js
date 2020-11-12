function toggleFontWeight(element) {
    const ele = element.parentNode.children;
    for(var i = 0 ; i < ele.length; i++){
        ele[i].classList.remove('bold');
    }
    element.classList.add('bold');
}

function createModDelBox(ele, id, boardseq, commentseq) { // ele는 내가 선택한 객체
    let div = document.createElement('div');
    div.classList.add('comment-modDel-box');
    div.classList.add('flex-center');

    let modDiv = document.createElement('div');
    modDiv.innerText = '수정';
    modDiv.classList.add('cursor');
    modDiv.addEventListener('click', () => {
		modifyComment(id, commentseq);
    })

    let delDiv = document.createElement('div');
    delDiv.innerText = '삭제';
    delDiv.classList.add('cursor');
    delDiv.addEventListener('click', () => {
		deleteComment(boardseq, commentseq);
    })

    div.append(modDiv);
    div.append(delDiv);
    ele.append(div);
}

function removeModDelBox(ele) {
    ele.remove();
}

function showModDelBox(ele, id, boardseq, commentseq) {
    const modDelBox = document.querySelector('.comment-modDel-box');
    if(modDelBox) {
        if(ele != modDelBox.parentNode) {
            createModDelBox(ele, id, boardseq, commentseq);
        }
        removeModDelBox(modDelBox);
        return;
    } else {
        createModDelBox(ele, id, boardseq, commentseq);
        return;
    }
}

function cutComment(ele) {
    const eleValue = ele.value;
    if(eleValue.length > 150) {
        alert('댓글은 150자를 넘을 수 없습니다!');
        ele.value = eleValue.substr(0, 150);
    }
}

function formSubmit(boardseq) {
    const form = document.querySelector('#commentIns-form');
    const eleValue = form.comment.value;
    if(eleValue.length == 0) {
        alert('댓글을 입력해주세요!');
        return;
    }
    commentRegMod(boardseq);
}

function modifyComment(id, commentseq) {
	const commentContent = document.querySelector('#' + id).innerText;
	const form = document.querySelector('#commentIns-form');
	form.comment.value = commentContent;
	form.commentseq.value = commentseq;
	//console.log(commentseq + '  ' + commentContent);	
}

function commentRegMod(boardseq) {
	const form = document.querySelector('#commentIns-form');
	const commentseq = form.commentseq.value;
	const textarea = form.comment.value;
	//console.log("코멘트 시퀀스 : " + commentseq);
	//console.log('보드 시퀀스 : ' + boardseq);
	//console.log('텍스트 : ' + textarea);
	ajaxCommentRegMod(commentseq, boardseq, textarea);
	
}

function ajaxCommentRegMod(commentseq, boardseq, textarea) {
	const url = '/commentRegModAction';
	const param = {
		'commentseq': commentseq,
		'boardseq': boardseq,
		'commentcontent': textarea,
	};
	
	axios.post(url, param).then(function(res) {
		const boardseq = res.data;
		if(boardseq == 0) {
			alert('로그인을 해주세요!');
			location.href = 'main';
		}
		alert('댓글이 등록되었습니다!');
		goToComment(boardseq);
	})
}

function deleteComment(boardseq, commentseq) {
	if(!confirm('삭제하시겠습니까?')) {
		return;
	}
	axios.get('/commentDel', {
		params: {
			'commentseq': commentseq
		}
	}).then(function(res){
		alert('댓글이 삭제되었습니다.');
		goToComment(boardseq);
	})
}

function cancleComment() {
	const form = document.querySelector('#commentIns-form');
	form.commentseq.value = 0;
	form.comment.value = null;
}


let byWriter = true; // default : 전체조회
let orderBy = true; // default : 오름차순(ASC)

function changeByWriter(boolean) {
	byWriter = boolean;
}

function changeorderBy(boolean) {
	orderBy = boolean;
}

function showComment(boardseq, element) {

	const param = {
		'boardseq': boardseq,
		'byWriter': byWriter,
		'orderBy': orderBy
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
			}).then(function() {
				toggleFontWeight(element)
			})
		})
}



