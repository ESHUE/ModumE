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
    const title = form.title.value;
    const textarea = CKEDITOR.instances.boardRegModTexarea.getData();
	const convertcontent = textarea.replace(/(<([^>]+)>)/ig, "");

    if(title == '') {
        alert('제목을 입력해주세요!');
        return;
    } else if(textarea == '') {
        alert('내용을 입력해주세요!');
        return;
    } 
	// console.log(convertcontent);
	ajaxRegMod(title, textarea, convertcontent);
}

function ajaxRegMod(title, textarea, convertcontent) {
	const url = '/boardRegModAction';
	const param = {
		'title': title,
		'content': textarea,
		'convertcontent' : convertcontent
	};
	
	axios.post(url, param).then(function(res) {
		console.log(res.data); // 글 쓴 후 데이터
		const boardseq = res.data;
		goToDetail(boardseq);
	})
}
