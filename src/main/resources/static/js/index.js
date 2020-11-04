var sectionContainer = document.querySelector('.sectionContainer');
var centralContainer = document.querySelector('.centralContainer');
var centralMenu1_2 = document.querySelector('.centralMenu1_2');


function findVideo(evt) {
   evt.preventDefault();
   const keyword = document.querySelector('#searchVideo').value;
   // console.log(keyword);
	axios.get('/googleSearch', {
      params : {
         keyword
      }
   }).then(function(res) {
      // console.log(res);
      showSearchList(res);
   }).then(function() {
      var searchSwiper = new Swiper('.searchResultContainer>.swiper-container', {
         slidesPerView: 4,
         spaceBetween: 10,
         slidesPerGroup: 4,
         loop: false,
         loopFillGroupWithBlank: true,
         mousewheel: true,
         pagination: {
           el: '.swiper-pagination',
           clickable: true,
         },
         navigation: {
           nextEl: '.swiper-button-next',
           prevEl: '.swiper-button-prev',
         },
      });
   })
}

function chatInit() {
    const chatContainer = document.querySelector('.chatContainer');
    const boardContainer = document.querySelector('.boardContainer');
    if(boardContainer != null) {
        closeContainer(boardContainer);
    }
    if(chatContainer == null) {
        chatList()
    } else {
        closeContainer(chatContainer);
    }
}

function boardInit() {
    const boardContainer = document.querySelector('.boardContainer');
    const chatContainer = document.querySelector('.chatContainer');
    if(chatContainer != null) {
        closeContainer(chatContainer);
    } 
    if(boardContainer == null) {
        openboard();
    } else {
        closeContainer(boardContainer);
    }
}

function openChat() {
    const makeDiv = document.createElement('div');
    makeDiv.className = 'chatContainer';
	 makeDiv.setAttribute('id','chatContainer')
    sectionContainer.append(makeDiv);
    const chatList = document.createElement('div');
    chatList.className = 'chatList';
    makeDiv.append(chatList)
   /*채팅관련 창들 */
   
   console.log('chat화면 띄우기 완료')
}

function chatList() {
   fetch('/chat/rooms').then(function(response) {
      response.text().then(function(text) {
         openChat()
         document.querySelector('#chatContainer').innerHTML = text;
      })
   })
}

	//채팅을 위한 전역변수들( 미리 초기화 )
	var roomId = null;
	var member = null;
	var message = null;

function chatDetail(roomId, member) {
	$(function() {
		var chatBox = $('.chat-box');
		var messageInput = $('input[name="message"]');
		message = messageInput;
		var sendBtn = $('.send');
		var sock = new SockJS("/ws");
		var client = Stomp.over(sock); // 1. SockJS를 내부에 들고 있는 client를 내어준다.
		// 2. connection이 맺어지면 실행된다.
		client.connect({}, function() {
			// 3. send(path, header, message)로 메시지를 보낼 수 있다.
			client.send('/publish/chat/join', {}, JSON.stringify({
				chatRoomId : roomId,
				writer : member
			}));
			// 4. subscribe(path, callback)로 메시지를 받을 수 있다. callback 첫번째 파라미터의 body로 메시지의 내용이 들어온다.
			client.subscribe('/subscribe/chat/room/' + roomId, function(
					chat) {
            var content = JSON.parse(chat.body);
            if(content.writer==member){
               chatBox.append('<li class="myId"><span class="myMember">' + content.message + '</span>('
						+ content.writer + ')</li>')
            }else{
               chatBox.append('<li class="otherId"><span class="otherMember">' + content.message + '</span>('
						+ content.writer + ')</li>')
            }
				
			});
		});
		sendBtn.click(function() {
         var message = messageInput.val();
         console.log(message);
         if(message ==null || message==''){
            alert('Please Enter Content')
            
         }else{
            client.send('/publish/chat/message', {}, JSON.stringify({
               chatRoomId : roomId,
               message : message,
               writer : member
            }));
            messageInput.val('');
         }
		});
	});
	}
function chatListDetail(temp, mem) {
	roomId = temp;
	member = mem;
	var url = '/chat/rooms/' + temp
	console.log(url)
	fetch(url).then(function(response) {
		response.text().then(function(text) {
			chatDetail(roomId, mem)
			document.querySelector('#chatContainer').innerHTML = text;
		})
	})
}
function removeChatPage(){
	let chatContainer = document.querySelector('#chatContainer');
	chatContainer.remove();
}


function openboard() {
    const makeDiv = document.createElement('div');
    makeDiv.className = 'boardContainer';
    sectionContainer.append(makeDiv);
	changeLocation('/boardList');
}

function changeLocation(location) {
	const boardContainer = document.querySelector('.boardContainer');
	//fetchBoard(boardContainer, location);
	fetchBoard(boardContainer, location);
}

function fetchBoard(ele, location) {
	fetch(location).then(function(response) {
		response.text().then(function(text) {
			ele.innerHTML = text;
		})
	})
}

function closeContainer(ele) {
    ele.remove();
}



function userMenuInit(isLogin,temp) {
    const userMenu = document.querySelector('.userMenu');
    const alertMenu = document.querySelector('.alertMenu');
    if(alertMenu != null) {
        closeContainer(alertMenu);
    }
   if(userMenu == null) {
      openUserMenu(isLogin,temp);
   } else {
      closeContainer(userMenu);
   }
}

function alertMenuInit() {
    const alertMenu = document.querySelector('.alertMenu');
    const userMenu = document.querySelector('.userMenu');
    if(userMenu != null) {
        closeContainer(userMenu);
    }
    if(alertMenu == null) {
      openAlertMenu();
   } else {
      closeContainer(alertMenu);
   }
}

var nickname = null;
function openUserMenu(isLogin,temp) {
   nickname = temp;
    const makeDiv = document.createElement('div');
    makeDiv.className = 'userMenu';
    makeDiv.style.position = 'absolute';
    makeDiv.style.right = '35px';
    makeDiv.style.width = '300px';
    makeDiv.style.height = '350px';
    makeDiv.style.borderRadius = '5px';
    makeDiv.style.backgroundColor = 'rgba(0, 0, 0, 0.6)';
    makeDiv.style.color = 'white';
    makeDiv.style.zIndex = '2';

   const makeDiv2 = document.createElement('div');
   makeDiv2.className = 'userMenuTooltip';
   
   makeDiv2.style.position = 'relative';
   makeDiv2.style.top = '50px';
 
   
   const makeSpan2_1_1 = document.createElement('span');
   makeSpan2_1_1.classList.add('cursor');
   makeSpan2_1_1.innerText = '로그인';
   makeSpan2_1_1.setAttribute('onclick',"showLogin()");
//   const makeSpan2_1_2 = document.createElement('span');
//   makeSpan2_1_2.classList.add('cursor');
//   makeSpan2_1_2.innerText = '회원가입';
//   makeSpan2_1_2.setAttribute('onclick',"location.href=+/join'");

   /*const nickName = document.createElement('span');
   nickName.innerText = temp+'님님님';*/
   
   const makeSpan2_2_1 = document.createElement('span');
   makeSpan2_2_1.classList.add('cursor');
   makeSpan2_2_1.innerText = '로그아웃';
   makeSpan2_2_1.setAttribute('onclick',"location.href='/logout'");
   console.log(temp)
   console.log(isLogin)
	
   const makeSpan2_2_2 = document.createElement('span');
   makeSpan2_2_2.classList.add('cursor');
   makeSpan2_2_2.innerText = '마이페이지';
   makeSpan2_2_2.setAttribute('onclick', 'openUserInfo()');
   
//   const makeSpan2_2_2 = document.createElement('span');
//   makeSpan2_2_2.classList.add('cursor');
//   makeSpan2_2_2.innerText = '메인화면';
//   makeSpan2_2_2.setAttribute('onclick',"location.href='/'");
   
	const makeSpan2_2_3 = document.createElement('span');
   makeSpan2_2_3.classList.add('cursor');
   makeSpan2_2_3.innerText = temp;
   makeSpan2_2_3.setAttribute('onclick', 'openUserInfo()');

   makeSpan2_2_1.style.display = ' block';
   makeSpan2_2_2.style.display = ' block';
 //  makeSpan2_2_3.style.display = ' block';


   
   if(isLogin) {
      makeDiv2.append(makeSpan2_2_3);
      makeDiv2.append(makeSpan2_2_2);
      makeDiv2.append(makeSpan2_2_1);
	  
   } else {
      makeDiv2.append(makeSpan2_1_1);
//      makeDiv2.append(makeSpan2_1_2);
   }
   makeDiv.append(makeDiv2);
   centralMenu1_2.append(makeDiv);
}

function openAlertMenu() {
    const makeDiv = document.createElement('div');
    makeDiv.className = 'alertMenu';
    makeDiv.style.position = 'absolute';
    makeDiv.style.right = '35px';
   makeDiv.style.width = '250px';
   makeDiv.style.height = '350px';
   makeDiv.style.borderRadius = '5px';
    makeDiv.style.backgroundColor = 'rgba(0, 0, 0, 0.6)';
   makeDiv.style.color = 'white';
    makeDiv.style.zIndex = '2';
   centralMenu1_2.append(makeDiv);
}

function event(e) {
	if(e.target.className == 'loginWindowContainer') {
		removeLogin();
	}
}

function makeLogin() {
   let loginWindowContainer = document.createElement('div');
   loginWindowContainer.classList.add('loginWindowContainer');
   loginWindowContainer.setAttribute('id', 'loginWindowContainer');
	   loginWindowContainer.addEventListener('click', event);
   

   let loginPageContainer = document.createElement('div');
   loginPageContainer.classList.add('loginPageContainer');
   
   let closeLoginWindow = document.createElement('div');
   closeLoginWindow.classList.add('closeLoginWindow');
   
   let material_icons = document.createElement('span');
   material_icons.classList.add('material-icons');
   material_icons.setAttribute('onclick', 'removeLogin()');
   material_icons.innerText = 'clear';
   
   let loginWindow = document.createElement('div');
   loginWindow.setAttribute('id', 'loginWindow');
   
   closeLoginWindow.append(material_icons);
   loginPageContainer.append(closeLoginWindow);
   loginPageContainer.append(loginWindow);
   
   loginWindowContainer.append(loginPageContainer);
   
   let body = document.querySelector('body');
   body.prepend(loginWindowContainer);
}


function showLogin() {
   fetch('/login').then(function(response) {
      response.text().then(function(text) {
         makeLogin();
         document.querySelector('#loginWindow').innerHTML = text;
      })
   })
}

function removeLogin() {
   let loginWindowContainer = document.querySelector('#loginWindowContainer');
   loginWindowContainer.remove();
}

function moveToJoin(){
	removeLogin();
	makeJoin();
}

function moveToLogin(){
	removeLogin();
	showLogin();
}

function makeJoin(){
   fetch('/join').then(function(response) {
      response.text().then(function(text) {
		 makeLogin();
		document.querySelector('#loginWindow').innerHTML = text;
      })
   })
}

/*usernameForm.addEventListener('submit', connect, true)*/

function openUserInfo() {
   const body = document.querySelector('body');
   const makeDiv = document.createElement('div');
   makeDiv.classList.add('shadowWindow');
   makeDiv.classList.add('userInfoWindow');
   
   const makeDiv2 = document.createElement('div');
   makeDiv2.classList.add('loginPageContainer');
   
   const makeDiv2_1 = document.createElement('div');
   makeDiv2_1.classList.add('closeLoginWindow');
   
   const makeSpan2_1_1 = document.createElement('span');
   makeSpan2_1_1.classList.add('material-icons');
   makeSpan2_1_1.innerText = 'clear';
   makeSpan2_1_1.addEventListener('click', ()=>{
      const userInfoWindow = document.querySelector('.userInfoWindow');
      userInfoWindow.remove();
   })
   
   const makeDiv2_2 = document.createElement('div');
   makeDiv2_2.classList.add('userInfoContainer');
   fetch('/userinfo').then(function(response) {
      response.text().then(function(text){
         makeDiv2_2.innerHTML = text;
      })
   })
   
   makeDiv2_1.append(makeSpan2_1_1);
   makeDiv2.append(makeDiv2_1);
   makeDiv2.append(makeDiv2_2);
   makeDiv.append(makeDiv2);
   body.prepend(makeDiv);
}