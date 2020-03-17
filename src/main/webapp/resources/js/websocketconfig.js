/**
 * Created by readlearncode.com on 29/11/2016.
 */

$(document).ready(function () {
    connectToChatServer();
});

function sendMessage() {
    // Construct message to send to server
    var msg = '{"content":"' + $message.val() + '", "sender":"' + user + '", "received":"' + '"}';
    wsocket.send(msg);

    // Put back focus
    $message.val('').focus();
}

function connectToChatServer() {
    // Create websocket connection to server endpoint URI
    wsocket = new WebSocket(serviceLocation);

    // Set message and error handlers
    wsocket.onerror = onConnectionError;
    wsocket.onmessage = onMessageReceived;
}

function onConnectionError(evt) {
    // Print error to chat window
    $alert.append($(evt));
}

function onMessageReceived(evt) {
    // Parse JSON String to JavaScript Object
    var msg = JSON.parse(evt.data);
    handleMessage(evt);
    updatePageInfo();
}

window.addEventListener("beforeunload", function (event) {
    wsocket.close();
});
