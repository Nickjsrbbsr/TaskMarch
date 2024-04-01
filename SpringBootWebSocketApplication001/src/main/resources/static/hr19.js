// Global array to store approved messages
var approvedMessages = [];
var rejectedMessages =[];

const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:9098/gs-guide-websocket'
});
//2nd function call
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
//3rd function call
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
//first function call
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

// function sendName() {
//     console.log("function sendName() called ->  ");
//
//     console.log("stompClient.publish() called with destination '/app/hello' and etc");
//     stompClient.publish({
//         destination: "/app/hello2/",
//         body: JSON.stringify({'name': $("#name").val()})
//     });
// }
function sendName() {
    console.log("function sendName() called ->  ");

    var name = $("#name").val(); // Get the value from the name input field

    // Construct the dynamic destination URL using the form value
    var destinationUrl = "/app/hello2/" + name;

    console.log("stompClient.publish() called with destination '" + destinationUrl + "' and etc");
    stompClient.publish({
        destination: destinationUrl,
        body: JSON.stringify({'name': name})
    });
}


// function showGreeting(message) {
//     console.log("showGreeting() called of hr js updated");
//     $("#greetings").append("<tr><td>" + message + "</td></tr>");
// }
function showGreeting(message) {
    console.log("showGreeting() called and kritik changed some data");
    $("#greetings").append("<tr><td>" + message + " 'Hii from JS bro !!!!' " + "<button class='btn btn-success btn-sm approve-btn' id='approve-" + message + "'>Approve</button> <button class='btn btn-danger btn-sm reject-btn' id='reject-" + message + "'>Reject</button></td></tr>");
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

    // Click event for approve button
    $(document).on('click', '.approve-btn', function() {
        // Extract the message ID from the button ID
        var messageId = $(this).attr('id').split('-')[1];
        // Add the message to the approvedMessages array
        approvedMessages.push(messageId);
        console.log(messageId + " id is approved");
        // You can perform additional actions here if needed
    });

    // Click event for reject button
    $(document).on('click', '.reject-btn', function() {
        // Extract the message ID from the button ID
        var messageId = $(this).attr('id').split('-')[1];
        rejectedMessages.push(messageId);
        console.log(messageId + " id is rejected");
        // You can perform additional actions here if needed
    });
});