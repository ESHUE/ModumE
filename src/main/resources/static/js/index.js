var sectionContainer = document.querySelector('.sectionContainer');
var centralContainer = document.querySelector('.centralContainer');
var centralMenu1_2 = document.querySelector('.centralMenu1_2');

//채팅을 위한 전역변수들( 미리 초기화 )
var roomId = null;
var member = null;
var message = null;

function findVideo(evt) {
   evt.preventDefault();
   const keyword = document.querySelector('#searchVideo').value;
   // //console.log(keyword);
	axios.get('/googleSearch', {
      params : {
         keyword
      }
   }).then(function(res) {
      // //console.log(res);
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
	axios.get("/curUserName",{}).then(function(res){
      member = res.data;
   //console.log(res)

    const chatContainer = document.querySelector('.chatContainer');
    const boardContainer = document.querySelector('.boardContainer');
    if(boardContainer != null) {
        closeContainer(boardContainer);
    }
    if(chatContainer == null) {
      openChat(member)
    } else {
        closeContainer(chatContainer);
        chatLeave(client, roomId, member)
    }
	})
}
 
function loadChatInfo(){
	axios.get('/getRoomId',{}).then(function(res){
		//console.log(res);
		roomId = res.data.youTubeRoomId;
	})
}

function boardInit() {
    const boardContainer = document.querySelector('.boardContainer');
    const chatContainer = document.querySelector('.chatContainer');
    if(chatContainer != null) {
        closeContainer(chatContainer);
        chatLeave(client, roomId, member)
    } 
    if(boardContainer == null) {
        openboard();
    } else {
        closeContainer(boardContainer);
    }
}

function openChat(member) {
    const makeDiv = document.createElement('div');
    makeDiv.className = 'chatContainer';
	 makeDiv.setAttribute('id','chatContainer')
    sectionContainer.append(makeDiv);
    const chatList = document.createElement('div');
    chatList.className = 'chatList';
    makeDiv.append(chatList)
   //  axios.get("getChatId",{}).then(function(res){
   //     roomId = res.data
   //  })
    TestDetail(roomId,member)
   /*채팅관련 창들 */
   
   //console.log('chat화면 띄우기 완료')
}

function chatList() {
   fetch('/chat/rooms').then(function(response) {
      response.text().then(function(text) {
         openChat()
         document.querySelector('#chatContainer').innerHTML = text;
      })
   })
}
var client = null;

function chatDetail(roomId, member) {
   //console.log(roomId)
   //console.log('왜 들어와짐?')
	$(function() {
		var chatBox = $('.chat-box');
		var messageInput = $('input[name="message"]');
		messageInput.keypress(function(key){
			//console.log(key)
			if( key.keyCode == 13 ){
				var message = messageInput.val();
				//console.log(message);
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
               
				}
			});
		message = messageInput;
      var sendBtn = $('.send');
      var sock = new SockJS("/ws");
      client = Stomp.over(sock); 
      var content_class = document.querySelector(".content");
      // 1. SockJS를 내부에 들고 있는 client를 내어준다.
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
            content_class.scrollTop = content_class.scrollHeight;
         });
      });
      sendBtn.click(function() {
         var message = messageInput.val();
         //console.log(message);
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

function chatLeave(client, roomId, member){
   client.send('/publish/chat/leave',{},JSON.stringify({
      chatRoomId : roomId,
      writer : member
   }));
}

function TestDetail(roomId,member){
   var url = '/chat/rooms/' + roomId
   //console.log('roomId:'+roomId)
   //console.log('member:'+member)
   //console.log('url:'+url)
   fetch(url).then(function(response) {
		response.text().then(function(text) {
			chatDetail(roomId, member)
			document.querySelector('#chatContainer').innerHTML = text;
		})
	})
}

function saveRoomId(tmp){
   roomId = tmp;
}

function chatListDetail(temp, mem) {
	roomId = temp;
	member = mem;
	var url = '/chat/rooms/' + temp
	//console.log(url)
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
   member = temp;
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

function openUserMenu(isLogin,temp) {
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
   
   const makeSpan2_2_1 = document.createElement('span');
   makeSpan2_2_1.classList.add('cursor');
   makeSpan2_2_1.innerText = '로그아웃';
   makeSpan2_2_1.setAttribute('onclick',"location.href='/logout'");
   makeSpan2_2_1.style.position = 'relative';
   makeSpan2_2_1.style.top = '170px';
   makeSpan2_2_1.style.left = '0px';
   makeSpan2_2_1.style.display = 'flex';
   makeSpan2_2_1.style.justifyContent = 'center';
   //console.log(temp)
   //console.log(isLogin)
	
   const makeSpan2_2_2 = document.createElement('span');
   makeSpan2_2_2.classList.add('cursor');
   makeSpan2_2_2.innerText = '마이페이지';
   makeSpan2_2_2.setAttribute('onclick', 'openUserInfo(0)');
   makeSpan2_2_2.style.position = 'relative';
   makeSpan2_2_2.style.top = '100px';
   makeSpan2_2_2.style.left = '0px';
   makeSpan2_2_2.style.display = 'flex';
   makeSpan2_2_2.style.justifyContent = 'center';
   
   const makeSpan2_2_3 = document.createElement('span');
   makeSpan2_2_3.classList.add('cursor');
   makeSpan2_2_3.innerText = temp;
   makeSpan2_2_3.setAttribute('onclick', 'openUserInfo(0)');
   makeSpan2_2_3.style.position = 'relative';
   makeSpan2_2_3.style.top = '90px';
   makeSpan2_2_3.style.left = '0px';
   makeSpan2_2_3.style.display = 'flex';
   makeSpan2_2_3.style.justifyContent = 'center';

   const makeSpan2_2_4 = document.createElement('span');
   makeSpan2_2_4.classList.add('material-icons');
   makeSpan2_2_4.innerText = 'person_add';
   makeSpan2_2_4.style.position = 'relative';
   makeSpan2_2_4.style.top = '140px';
   makeSpan2_2_4.style.left = '0px';
   makeSpan2_2_4.style.display = 'flex';
   makeSpan2_2_4.style.justifyContent = 'center';
   makeSpan2_2_4.style.alignItems = 'center';
   makeSpan2_2_4.style.fontSize = '1.8em';

  

   const makeSpan2_2_5 = document.createElement('span');
   makeSpan2_2_5.classList.add('cursor');
   makeSpan2_2_5.innerText = '계정 추가';
   makeSpan2_2_5.style.fontSize = '18px';
   makeSpan2_2_4.addEventListener('click', ()=>{
	openUserInfo(3);
	});//userInfo3으로 바로가기로 바꿔야함

   //makeSpan2_2_1.style.display = ' block';
   //makeSpan2_2_2.style.display = ' block';
   //makeSpan2_2_4.style.display = ' block';


   if(isLogin) {
      makeDiv2.append(makeSpan2_2_3);
      makeDiv2.append(makeSpan2_2_2);
      makeSpan2_2_4.append(makeSpan2_2_5);
      makeDiv2.append(makeSpan2_2_4);
      makeDiv2.append(makeSpan2_2_1);
	  
   } else {
      makeDiv2.append(makeSpan2_1_1);
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

function openMyPageDetails(idx) { 
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
	}

function openUserInfo(idx) {
   const body = document.querySelector('body');
   const makeDiv = document.createElement('div');
   makeDiv.classList.add('shadowWindow');
   makeDiv.classList.add('userInfoWindow');
   
   const makeDiv2 = document.createElement('div');
   makeDiv2.classList.add('loginPageContainer');


   const myPageTabMenuContainer = document.createElement('div');
   myPageTabMenuContainer.classList.add('myPageTabMenuContainer');

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
      }).then(function () {openMyPageDetails(idx)});
   })
   
	
	
   makeDiv2_1.append(makeSpan2_1_1);
   myPageTabMenuContainer.append(makeDiv2_1);
   myPageTabMenuContainer.append(makeDiv2_2);
   makeDiv2.append(myPageTabMenuContainer);
   makeDiv.append(makeDiv2);
   body.prepend(makeDiv);
}
