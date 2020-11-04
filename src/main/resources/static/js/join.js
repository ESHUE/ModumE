function profileAddIconChange() {
    const personIcon = document.querySelector('.personIcon');
    console.log(personIcon)
    const makeSpan1 = document.createElement('span');
    makeSpan1.style.width = '100%';
    makeSpan1.style.height = '100%';
    makeSpan1.style.position = 'absolute';
    makeSpan1.style.top = '0';
    makeSpan1.style.left = '0';
    makeSpan1.style.color = 'red';
    makeSpan1.className = 'material-icons addIconHidden addIcon';
    makeSpan1.innerText = 'add';
    
    personIcon.append(makeSpan1);
}

function profilePersonIconChange() {
    const addIcon = document.querySelector('.addIcon');
    addIcon.classList.add('addIconHidden');
    const personIcon = document.querySelector('.personIcon');
    personIcon.style.removeProperty('display');
}

function fileNameMove() {
    const fileName = document.getElementById('fileName');
    const profile = document.getElementById('profile');
    fileName.value = profile.value;
    
}
// 프로필 이미지 미리보기 	
function profilePreview(event) {
    const prevImg = document.querySelector('.imgPreview');
    if(prevImg != null) {
        prevImg.remove();
    }
    var reader = new FileReader();
    reader.onload = function(event) { 
        var img = document.createElement("img"); 
        img.classList.add('imgPreview')
        img.style.width= '100px';
        img.style.height = '80px';
        
        img.setAttribute("src", event.target.result); 
        document.querySelector("div#image_container").appendChild(img); 
    }; 
    reader.readAsDataURL(event.target.files[0]); 
}

// function init() {
//     const personIcon = document.querySelector('.personIcon');
//     console.log(personIcon)
//     personIcon.style.position = 'relative';
//     personIcon.addEventListener('mouseover', ()=>{
//         profileAddIconChange();
//     });
// }

// init();