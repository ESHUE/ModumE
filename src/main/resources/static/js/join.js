const underline = document.getElementsByClassName('tabMenuBtn') 
	function temp(idx) { 
		openMyPageDetails(idx)
		addUnderLine(idx) 
		//removeUnderLine(idx) 
	}
	/*function openMyPageDetails(idx) { 
		const tabBoxContainer = document.querySelector('.tabBoxContainer'); 
		if(idx == 4) {
			location.href = 'http://localhost:8080/logout'; 
			return; 
		} // jsp 파일 이름이 바뀌면 controller와 pageName이 변경되어야 한다. 
		const pageName = '/userinfo' + idx; 
		fetch(pageName).then(function(response) { 
			response.text().then(function(text) { 
				tabBoxContainer.innerHTML = text; 
			}) 
		})
	}*/
	function addUnderLine(idx) {
		if(document.querySelector('.underline')) { 
		const under_element = document.querySelector('.underline') 
		under_element.classList.remove('underline') 
		} 
		underline[idx].classList.add('underline') 	
	}
	
	function profileAddIconChange() {
		const personIcon = document.querySelector('.personIcon');
		const addIcon = document.querySelector('.addIconHidden');
		personIcon.style.display = 'none';
		personIcon.style.transition = '0.5s';
		addIcon.classList.remove('addIconHidden');
		const addIcon2 = document.querySelector('.addIcon');
		addIcon2.style.color = 'red';
	}

	function profilePersonIconChange() {
		const addIcon = document.querySelector('.addIcon');
		addIcon.classList.add('addIconHidden');
		const personIcon = document.querySelector('.personIcon');
		personIcon.style.removeProperty('display');
	}
	
	function profileInfo(event){
		profilePreview(event);
		fileNameMove();
	}
	
	function fileNameMove() {
		const fileName = document.getElementById('fileName');
		const profile = document.getElementById('profile');
		fileName.value = profile.value;
	}
	//프로필 이미지 미리보기
	function profilePreview(event) {
		const prevImg = document.querySelector('.imgPreview');
	    if(prevImg != null) {
	        prevImg.remove();
	    }
		var reader = new FileReader(); 
		reader.onload = function(event) { 
			var img = document.createElement("img"); 
			img.classList.add('imgPreview')
			img.style.width= '70px';
			img.style.height = '60px';
			img.style.borderRadius = '50%';
			
			img.setAttribute("src", event.target.result); 
			document.querySelector("label#profileIcon").appendChild(img);
			//const imgPreview = document.querySelector('imgPreview');
			img.style.position= 'relative';
			img.style.top = '5px';
			img.style.right= '7px'; 
			
		}; 
		reader.readAsDataURL(event.target.files[0]); 
		
	}
function chkId() {
		const username = frm.username.value
		axios.get('/IdChk', {
			params:{
				username
			}
		}).then(function(res) {
			if(res.data == '2') { //아이디 없음
				idChkResult.innerText = '사용할 수 있는 아이디입니다.'
			} else if(res.data == '3') { //아이디 중복됨
				idChkResult.innerText = '이미 사용중입니다.'
			}
		})
    }

