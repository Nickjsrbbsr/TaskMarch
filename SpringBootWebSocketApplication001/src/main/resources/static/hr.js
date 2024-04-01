const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:9098/gs-guide-websocket'
});

stompClient.onConnect = (frame) => {
    console.log("stompClient.onConnect called")
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/greetings', (greeting) => {
        showGreeting(JSON.parse(greeting.body).content);
    });
};

stompClient.onWebSocketError = (error) => {
    console.log("stompClient.onWebSocketError");
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.log("stompClient.onStompError called")
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    console.log("function setConnected called")
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    console.log("function connect() called inside that -> stompClient.activate() called ");
    stompClient.activate();
}

function disconnect() {
    console.log("function disconnect called -> stompClient.deactivate() called ");

    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    console.log("function sendName() called ->  ");

    console.log("stompClient.publish() called with destination '/app/hello' and etc");
    stompClient.publish({
        destination: "/app/hello2",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

function showGreeting(message) {
    console.log("showGreeting() called");
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

// window.onload = () =>{
//     $("form").on('submit', (e) => e.preventDefault());
//     $( "#connect" ).click(() => connect());
//     $( "#disconnect" ).click(() => disconnect());
//     $( "#send" ).click(() => sendName());
// };





$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});