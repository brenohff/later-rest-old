'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var choosePage = document.querySelector('#choose-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

var user = {
		id: 1369288233173370,
		email: 'breno15555@gmail.com',
		name: 'Breno Henrique',
		birthday: '05/22/1996',
		gender: 'male',
		link: 'https://www.facebook.com/app_scoped_user_id/1369288233173370/',
		image: 'https://graph.facebook.com/1369288233173370/picture',
		image_long: 'https://graph.facebook.com/1369288233173370/picture?type=large'
}

function showEvents(event){
    username = document.querySelector('#name').value.trim();

    if(username) {
        usernamePage.classList.add('hidden');
        choosePage.classList.remove('hidden');

        // $.ajax({
        //     type: "GET",
        //     url: "http://later-backend.herokuapp.com/events/getAll",
        //     dataType: "json",
        //     headers: {
        //         'Content-Type': 'application/json',
        //         'X-Requested-With': 'XMLHttpRequest',
        //         'Access-Control-Allow-Origin': '*',
        //         'Access-Control-Allow-Headers': 'origin, content-type, accept, authorization'
        //     },
        //     success: function (data) {
        //         console.log(data);
        //     }, beforeSend: function () {
        //     },
        //     complete: function () {
        //     },
        //     error: function (xhr, status, error) {
        //         console.log("erro");
        //     }
        //
        // });

        // $.ajax({
        //     type: "GET",
        //     url: "http://later-backend.herokuapp.com/events/getAll",
        //     success: function(resp){
        //         console.log(conteudo);
        //     },
        //     headers: {
        //         "Content-Type" : "application/json",
        //         "Cache-Control" : "no-cache"
        //     }
        // });

        $.get("http://later-backend.herokuapp.com/events/getAll", function (data, status) {
            for (var i = 0; i < data.length; i++){
                console.log(data[i].title);
            }
        });
    }
    event.preventDefault();
}

function connect(event) {
    username = document.querySelector('#name').value.trim();

    if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/event');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/event/0', onMessageReceived);
    
    // Tell your username to the server
    stompClient.send("/live/event/0/addUser",
        {},
        JSON.stringify({user: user, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();

    if(messageContent && stompClient) {
        var chatMessage = {
            user: user,
            content: messageInput.value,
            type: 'CHAT'
        };

        stompClient.send("/live/event/0/sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.user.name + ' entrou!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.user.name + ' saiu!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.user.name[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.user.name);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.user.name);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

// usernameForm.addEventListener('submit', connect, true)
usernameForm.addEventListener('submit', showEvents, true)
messageForm.addEventListener('submit', sendMessage, true)
