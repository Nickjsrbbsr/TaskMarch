const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:9099/ws'
});

//2nd function call Inside that setConned function called
stompClient.onConnect = (frame) => {
    console.log("stompClient.onConnect called -> Inside that setConnected function called")
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/hrPortal', (greeting) => {
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
//3rd function called inside  stompClient.onConnect
function setConnected(connected) {
    console.log("function setConnected called")
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        // $("#conversation").show();
        console.log("connected");
    }
    else {
        // $("#conversation").hide();
        console.log(("disconnected"));
    }
    // $("#greetings").html("");
}

//1st called function when Connect button is clicked
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
        destination: "/app/submitRequest",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

function showGreeting(message) {
    console.log("showGreeting() called");
    $("#idField").append("<tr><td>" + message + "</td></tr>");
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