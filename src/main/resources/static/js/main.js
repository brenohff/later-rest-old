'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var choosePage = document.querySelector('#choose-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var eventsArea = document.querySelector('#eventsArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

var eventSelected = null;

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
};

function showEvents(event) {
    username = document.querySelector('#name').value.trim();

    if (username) {
        usernamePage.classList.add('hidden');
        choosePage.classList.remove('hidden');

        $.get("http://later-backend.herokuapp.com/events/getAll", function (data, status) {
            for (var i = 0; i < data.length; i++) {
                inflateEvents(data[i]);
            }
        });
    }
    event.preventDefault();
}

function inflateEvents(event) {
    var messageElement = document.createElement('li');
    messageElement.setAttribute("id", event.id);
    messageElement.setAttribute("onClick", "liClicked(this)");
    messageElement.classList.add('chat-message');

    var imageElement = document.createElement('img');
    imageElement.setAttribute("src", "http://agendadebrasilia.com/wp-content/uploads/2018/04/IMG-20180516-WA0015.jpg");

    messageElement.appendChild(imageElement);

    var usernameElement = document.createElement('span');
    var usernameText = document.createTextNode(event.title);
    usernameElement.appendChild(usernameText);
    messageElement.appendChild(usernameElement);

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(event.date);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);
    eventsArea.appendChild(messageElement);
}

function liClicked(obj) {
    eventSelected = $(obj).attr('id');
    connect();
}

function connect() {
    username = document.querySelector('#name').value.trim();

    if (username) {
        choosePage.classList.add('hidden')
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/event');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
}

function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/event/2', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/live/event/2/addUser",
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

    if (messageContent && stompClient) {
        var chatMessage = {
            user: user,
            content: messageInput.value,
            type: 'CHAT'
        };

        stompClient.send("/live/event/2/sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    console.log(payload);
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if (message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.user.name + ' entrou!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.user.name + ' saiu!';
    } else {
        messageElement.classList.add('chat-message');

        // var avatarElement = document.createElement('i');
        // var avatarText = document.createTextNode(message.user.name[0]);
        // avatarElement.appendChild(avatarText);
        // avatarElement.style['background-color'] = getAvatarColor(message.user.name);

        var avatarElement = document.createElement('img');
        avatarElement.setAttribute("id", event.title);
        avatarElement.setAttribute("src", event.user.image);

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


// usernameForm.addEventListener('submit', connect, true);
usernameForm.addEventListener('submit', showEvents, true);
messageForm.addEventListener('submit', sendMessage, true);