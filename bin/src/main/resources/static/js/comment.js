function toggleFontWeight(element) {
    const ele = element.parentNode.children;
    for(var i = 0 ; i < ele.length; i++){
        ele[i].classList.remove('bold');
    }
    element.classList.add('bold');
}

function createModDelBox(ele) { // ele는 내가 선택한 객체
    let div = document.createElement('div');
    div.classList.add('comment-modDel-box');
    div.classList.add('flex-center');

    let modDiv = document.createElement('div');
    modDiv.innerText = '수정';
    modDiv.classList.add('cursor');
    modDiv.addEventListener('click', () => {
        alert('수정');
    })

    let delDiv = document.createElement('div');
    delDiv.innerText = '삭제';
    delDiv.classList.add('cursor');
    delDiv.addEventListener('click', () => {
        alert('삭제');
    })

    div.append(modDiv);
    div.append(delDiv);
    ele.append(div);
}

function removeModDelBox(ele) {
    ele.remove();
}

function showModDelBox(ele) {
    const modDelBox = document.querySelector('.comment-modDel-box');
    if(modDelBox) {
        if(ele != modDelBox.parentNode) {
            createModDelBox(ele);
        }
        removeModDelBox(modDelBox);
        return;
    } else {
        createModDelBox(ele);
        return;
    }
}

function cutComment(ele) {
    const eleValue = ele.value;
    if(eleValue.length > 300) {
        alert('댓글은 300자를 넘을 수 없습니다!');
        ele.value = eleValue.substr(0, 100);
    }
}

function formSubmit() {
    const form = document.querySelector('#commentIns-form');
    const eleValue = form.comment.value;
    if(eleValue.length == 0) {
        alert('댓글을 입력해주세요!');
        return;
    }
    // 아자아자아작스
    alert(eleValue);
}

