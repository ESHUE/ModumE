var sectionContainer = document.querySelector('.sectionContainer');
var centralContainer = document.querySelector('.centralContainer');

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
}

function openboard() {
    const makeDiv = document.createElement('div');
    makeDiv.className = 'boardContainer';
    sectionContainer.append(makeDiv);
}

function closeContainer(ele) {
    ele.remove();
}