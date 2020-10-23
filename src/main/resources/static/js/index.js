var sectionContainer = document.querySelector('.sectionContainer');
var centralContainer = document.querySelector('.centralContainer');
var centralMenu1_2 = document.querySelector('.centralMenu1_2');

var stompClient = null;
var inputChat = null;
var username = null;
var inputUl = null;

function chatInit() {
    const chatContainer = document.querySelector('.chatContainer');
    const boardContainer = document.querySelector('.boardContainer');
    if(boardContainer != null) {
        closeContainer(boardContainer);
    }
    if(chatContainer == null) {
        openChat();
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
    sectionContainer.append(makeDiv);
	/*채팅관련 창들 */
	const chatDiv = document.createElement('div');
	chatDiv.className = 'hidden';``
	makeDiv.append(chatDiv);
	inputChat = document.createElement('input');
	inputChat.className = 'inputChat';
	makeDiv.append(inputChat);
	inputUl = document.createElement('ul');
	inputUl.className = 'messageArea';
	chatDiv.append(inputUl);
	const btnChat = document.createElement('button');
	btnChat.className = 'btnChat';
	makeDiv.append(btnChat);
	btnChat.innerText = "보내기";
	btnChat.addEventListener('click', sendMessage, true);
	connect()
	console.log('chat화면 띄우기 완료')
}


function openboard() {
    const makeDiv = document.createElement('div');
    makeDiv.className = 'boardContainer';
    sectionContainer.append(makeDiv);
	changeLocation('/boardList');
}

function closeContainer(ele) {
    ele.remove();
}

function onConnected(){
	console.log("I don't know")
	stompClient.subscribe('/topic/public', onMessageReceived);
	stompClient.send("/app/chat.addUser",
	{},
	JSON.stringify({sender: username, type: 'JOIN'})
	)
	console.log("onConnected 끝")
}

function onError(error){
	connectingElement.textContent = "ConnectionError";
	connectingElement.style.color = 'red';
}

function sendMessage(event){
	console.log('message전송 시작')
	var messageContent = inputChat.value.trim();
	console.log('messageContent: ' + messageContent)
	if(messageContent && stompClient){
		var chatMessage = {
			sender : username,
			content: inputChat.value,
			type : 'CHAT'
		};
	stompClient.send("/app/chat.sendMessage",{},JSON.stringify(chatMessage));
	inputChat.value ='';
	}
	event.preventDefault();
}

function onMessageReceived(payload){
	var message = JSON.parse(payload.body);
	
	var messageElement = document.createElement('li');
	console.log('message.type:' + message.type)
	console.log('message.sender: ' + message.sender)
	if(message.type === "JOIN"){
		message.content = message.sender + "joined!";
	}else if(message.type === "LEVAE"){
		message.content = message.sender + "left!";
	}else{
		/* 채팅창에 채팅 메세지 띄우기 */
		messageElement.className='chat-message';
		inputUl.append(messageElement);
		
		var usernameElement = document.createElement('span');
		var usernameText = document.createTextNode(message.sender);
		usernameElement.appendChild(usernameText);
		messageElement.appendChild(usernameElement);	
	}
	var textElement = document.createElement('p');
	var messageText = document.createTextNode(message.content);
	textElement.appendChild(messageText);
	
	messageElement.appendChild(textElement);
}

function userMenuInit(isLogin) {
    const userMenu = document.querySelector('.userMenu');
    const alertMenu = document.querySelector('.alertMenu');
    if(alertMenu != null) {
        closeContainer(alertMenu);
    }
	if(userMenu == null) {
		openUserMenu(isLogin);
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

function openUserMenu(isLogin) {
    const makeDiv = document.createElement('div');
    makeDiv.className = 'userMenu';
    makeDiv.style.position = 'absolute';
    makeDiv.style.right = '35px';
	makeDiv.style.width = '150px';
	makeDiv.style.height = '350px';
	makeDiv.style.borderRadius = '5px';
    makeDiv.style.backgroundColor = 'rgba(0, 0, 0, 0.6)';
	makeDiv.style.color = 'white';
    makeDiv.style.zIndex = '2';

	const makeDiv2 = document.createElement('div');
	makeDiv2.className = 'userMenuTooltip';
	
	const makeSpan2_1_1 = document.createElement('span');
	makeSpan2_1_1.classList.add('cursor');
	makeSpan2_1_1.innerText = '로그인';
	makeSpan2_1_1.setAttribute('onclick',"showLogin()");
//	const makeSpan2_1_2 = document.createElement('span');
//	makeSpan2_1_2.classList.add('cursor');
//	makeSpan2_1_2.innerText = '회원가입';
//	makeSpan2_1_2.setAttribute('onclick',"location.href='/join'");
	
	const makeSpan2_2_1 = document.createElement('span');
	makeSpan2_2_1.classList.add('cursor');
	makeSpan2_2_1.innerText = '로그아웃';
	makeSpan2_2_1.setAttribute('onclick',"location.href='/logout'");
//	const makeSpan2_2_2 = document.createElement('span');
//	makeSpan2_2_2.classList.add('cursor');
//	makeSpan2_2_2.innerText = '메인화면';
//	makeSpan2_2_2.setAttribute('onclick',"location.href='/'");
	
	if(isLogin) {
		makeDiv2.append(makeSpan2_2_1);
//		makeDiv2.append(makeSpan2_2_2);
	} else {
		makeDiv2.append(makeSpan2_1_1);
//		makeDiv2.append(makeSpan2_1_2);
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

function makeLogin() {
	let loginWindowContainer = document.createElement('div');
	loginWindowContainer.classList.add('loginWindowContainer');
	loginWindowContainer.setAttribute('id', 'loginWindowContainer');
	
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




/*usernameForm.addEventListener('submit', connect, true)*/
