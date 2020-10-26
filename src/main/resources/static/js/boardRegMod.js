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

function changeCtnt__cnt() {
    const textarea = document.querySelector('#board-regMod-ctnt__textarea');
    const ctnt_cnt = document.querySelector('#board-regMod-ctnt__cnt');
    cutCtnt(textarea);
    ctnt_cnt.innerText = textarea.value.length + '/10000';
}

function cutCtnt(ele) {
    let eleValue = ele.value;
    if(eleValue.length > 10000) {
        alert('내용은 10000자를 넘을 수 없습니다!');
        ele.value = eleValue.substr(0, 10000);
    }
}