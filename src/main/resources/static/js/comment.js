const input = document.querySelector(".commentIns");
const optionBox = document.querySelector(".comment-option-box");
const commentBackTopBox = document.querySelector(".comment-backTop-btns");


input.addEventListener("focusin",()=>{
    optionBox.style.height = "35%";
    optionBox.style.minHeight ="200px";
})

input.addEventListener("focusout",()=>{
    optionBox.style.height = "60px";
    optionBox.style.minHeight = null;
    
})

function toggleFontWeight(element) {
    const bro = element.parentNode.children;
    for(var i = 0 ; i < bro.length; i++){
        bro[i].classList.remove('bold');
        //console.log(bro)
    }
    element.classList.add('bold');
}


function showModDelBox() {
    let length = document.getElementsByClassName('comment-modDel-td').length;
    console.log(length);
    for(var i = 0; i < length; i++) {
        
        const a = document.querySelector('#comment-modDel-td' + i);
        console.log(a);
        
        a.addEventListener("focusin", (event)=> {
            
            let div = document.createElement('div');
            div.classList.add('comment-modDel-box');
            div.classList.add('flex-center');

            let modDiv = document.createElement('div');
            modDiv.innerText = '수정';
            modDiv.classList.add('cursor');
            modDiv.addEventListener('click', () => {

            })

            let delDiv = document.createElement('div');
            delDiv.innerText = '삭제';
            delDiv.classList.add('cursor');
            delDiv.addEventListener('click', () => {

            })

            div.append(modDiv);
            div.append(delDiv);
            event.target.append(div);

        })

        a.addEventListener("focusout", (event) => {
            console.log(event);
            event.target.removeChild()
            })
    }
}

showModDelBox();


// const aa = document.querySelector('.aaa');

// aa.addEventListener('focusin', () => {
//     alert('ggggdsdfds');
//     console.log(div);
//     while(div.firstChild) {
//         div.lastChild.remove();
//     }
//     div.remove();
// })

// function bb(ele) {
//     const comment_modDel_box = document.querySelector('.comment-modDel-box');
    
//     removeCommentBox(comment_modDel_box);
//     if(comment_modDel_box) {
        
//         return;
//     }

//     let div = document.createElement('div');
//     div.classList.add('comment-modDel-box');
//     div.classList.add('flex-center');

//     let modDiv = document.createElement('div');
//     modDiv.innerText = '수정';
//     modDiv.classList.add('cursor');
//     modDiv.addEventListener('click', () => {

//     })

//     let delDiv = document.createElement('div');
//     delDiv.innerText = '삭제';
//     delDiv.classList.add('cursor');
//     delDiv.addEventListener('click', () => {

//     })

//     div.append(modDiv);
//     div.append(delDiv);
//     ele.append(div);

// }

// function removeCommentBox(element) {
//     console.log(element);
//     element.parentNode.removeChild(element)
// }


