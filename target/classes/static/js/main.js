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

        $.get("http://142.93.192.165:8080/events/getAll", function (data, status) {
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
    imageElement.setAttribute("src", event.image);

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

        loadOldMessages();

    }
}

function onConnected() {

    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/event/' + eventSelected, onMessageReceived);

    // Tell your username to the server
    stompClient.send("/live/event/" + eventSelected + "/addUser",
        {},
        JSON.stringify({users: user, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = 'Não foi possível conectar ao evento, tente novamente!';
    connectingElement.style.color = 'red';
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();

    if (messageContent && stompClient) {
        var chatMessage = {
            users: user,
            content: messageContent,
            type: 'CHAT'
        };

        stompClient.send("/live/event/" + eventSelected + "/sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if (message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.users.name + ' entrou!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.users.name + ' saiu!';
    } else {
        messageElement.classList.add('chat-message');

        var imageElement = document.createElement('img');
        imageElement.setAttribute("src", message.users.image);

        messageElement.appendChild(imageElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.users.name);
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

function loadOldMessages() {
    $.get("http://142.93.192.165:8080/chat/getChatByEventId?eventId=" + eventSelected, function (data, status) {
        for (var i = 0; i < data.length; i++) {
            var e = data[i];

            var messageElement = document.createElement('li');
            messageElement.classList.add('chat-message');

            var imageElement = document.createElement('img');
            imageElement.setAttribute("src", e.users.image);

            messageElement.appendChild(imageElement);

            var usernameElement = document.createElement('span');
            var usernameText = document.createTextNode(e.users.name);
            usernameElement.appendChild(usernameText);
            messageElement.appendChild(usernameElement);

            var textElement = document.createElement('p');
            var messageText = document.createTextNode(e.content);
            textElement.appendChild(messageText);

            messageElement.appendChild(textElement);

            messageArea.appendChild(messageElement);
            messageArea.scrollTop = messageArea.scrollHeight;
        }

        var socket = new SockJS('/event');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
    });
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

usernameForm.addEventListener('submit', showEvents, true);
messageForm.addEventListener('submit', sendMessage, true);