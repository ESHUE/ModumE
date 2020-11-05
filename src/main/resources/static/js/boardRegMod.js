function changeTitle__cnt() {
    const form = document.querySelector('#board-regMod__form');
    const title_cnt = document.querySelector('#board-regMod-title__cnt');
    cutTitle(form.title);
    title_cnt.innerText = form.title.value.length + '/100';

}

function cutTitle(ele) {
    let eleValue = ele.value;
    if(eleValue.length > 100) {
        alert('제목은 100자를 넘을 수 없습니다!');
        ele.value = eleValue.substr(0, 100);
    }
}



function chkSubmit() {
    const form = document.querySelector('#board-regMod__form');
    const boardseq = form.boardseq.value;
	const title = form.title.value;
    const textarea = CKEDITOR.instances.boardRegModTexarea.getData();
	const convertcontent = textarea.replace(/(<([^>]+)>)/ig, "");

	console.log("보여줘 너의 보드시퀀스 : " + boardseq);
    if(title == '') {
        alert('제목을 입력해주세요!');
        return;
    } else if(textarea == '') {
        alert('내용을 입력해주세요!');
        return;
    } 
	// console.log(convertcontent);
	ajaxRegMod(boardseq, title, textarea, convertcontent);
}

function ajaxRegMod(boardseq, title, textarea, convertcontent) {
	const url = '/boardRegModAction';
	const param = {
		'boardseq': boardseq,
		'title': title,
		'content': textarea,
		'convertcontent' : convertcontent
	};
	
	axios.post(url, param).then(function(res) {
		const boardseq = res.data;
		alert('게시글이 등록되었습니다.');
		goToDetail(boardseq);
	})
}
