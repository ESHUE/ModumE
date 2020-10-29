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

    if(title == '') {
        alert('제목을 입력해주세요!');
        return false;
    } else if(textarea == '') {
        alert('내용을 입력해주세요!');
        return false;
    } 
	ajaxRegMod(title, textarea);
    return true;

}

function ajaxRegMod(title, textarea) {
	axios.post('/boardRegModAction', {
		title: title,
		content: textarea
	}). then(function(res) {
		console.log(res);
	})
}

