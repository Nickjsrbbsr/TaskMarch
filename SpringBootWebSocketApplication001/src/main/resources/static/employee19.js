const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:9098/gs-guide-websocket'
});
//2nd function call
// stompClient.onConnect = (frame) => {
//     console.log("stompClient.onConnect called")
//     setConnected(true);
//     console.log('Connected: ' + frame);
//     console.log("subescribed to /topic/greetings");
//
//
//     stompClient.subscribe('/topic/greetings', (greeting) => {
//         showGreeting(JSON.parse(greeting.body).content);
//     });
// };

stompClient.onConnect = (frame) => {
    console.log("stompClient.onConnect called");
    setConnected(true);
    console.log('Connected: ' + frame);
    console.log("Subscribed to dynamic URL /topic/greeting/($id)");

    stompClient.subscribe('/topic/greeting/**', (greeting) => {
        // Extract the ID from the subscribed destination
        const id = greeting.headers.destination.split('/').pop();
        const messageContent = JSON.parse(greeting.body).content;
        // Pass both ID and message content to your handler function
        // showGreetings(id, messageContent);
        showGreeting(message);
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
//3rd function called
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
    $("#dataStructure").html("")
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
//4th function call
// function sendName() {
//     console.log("function sendName() called ->  ");
//
//     console.log("stompClient.publish() called with destination '/app/hello2' and etc");
//     stompClient.publish({
//         destination: "/app/hello2",
//         body: JSON.stringify({'name': $("#name").val() ,'id':$("#eid").val()})
//     });
// }
function sendName() {
    console.log("function sendName() called ->  ");

    var name = $("#name").val(); // Get the value from the name input field
    var eid = $("#eid").val(); // Get the value from the eid input field

    // Construct the dynamic destination URL using the form values
    var destinationUrl = "/app/hello";

    console.log("stompClient.publish() called with destination '" + destinationUrl + "' and etc");
    stompClient.publish({
        destination: destinationUrl,
        body: JSON.stringify({'name': name, 'id': eid})
    });
}

//5th function call

function showGreeting(message) {
    console.log("showGreeting() called and kritik changed some data");
    // Check if message is not null or undefined
    if (message && message.id && message.name) {
        $("#dataStructure").html("<tr><td>" + message.name +" 'Hii from JS' "  + "</td></tr>"+"<tr><td></td></tr>")
        $("#greetings").html("<tr><td>" + message.id + " 'Hii from JS' " + "</td></tr>" + "<tr><td></td></tr>");
    }else{
        console.error("Invalid message object:", message);

    }

}
function showGreetings(id, messageContent) {
    console.log("showGreeting() called");
    // Your logic to handle the greeting message
    console.log("Received greeting for ID: " + id + ", Content: " + messageContent);
    // Update your UI or perform other actions as needed
    console.log("showGreeting() called and kritik changed some data");
    //check if message is not null or undefined
    // if (messageContent && messageContent.id && messageContent.name) {
        $("#dataStructure").html("<tr><td>" + messageContent +" 'Hii from JS' "  + "</td></tr>"+"<tr><td></td></tr>")
        $("#greetings").html("<tr><td>" + messageContent + " 'Hii from JS' " + "</td></tr>" + "<tr><td></td></tr>");
    // }else{
    //     console.error("Invalid message object:", messageContent);


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