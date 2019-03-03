var stompClient = null;

//控制連線按鈕與聊天視窗顯示
function setConnected(connected) {
    //將id = connect 按鈕屬性改為disabled
    $("#connect").prop("disabled", connected);
    //將id = disconnect 按鈕屬性改為disabled
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        //當連接上Websocket id = conversation 顯示
        $("#conversation").show();
    }
    else {
        // 將 conversation隱藏
        $("#conversation").hide();
    }
    //將chatRoom內容清空
    $("#greetings").html("");
}
//連結WebSocket方法
function connect() {
    //建立一個socket物件 名稱為:/gs-guide-websocket
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}
//關閉WebSocket方法
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}
//傳送訊息方法
function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}
//顯示接收回來的訊息方法
function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});