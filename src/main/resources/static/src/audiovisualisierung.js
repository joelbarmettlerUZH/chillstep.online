var rafID = null;
var analyser = null;
var c = null;
var cDraw = null;
var ctx = null;
var microphone = null;
var ctxDraw = null;

var loader;
var filename;
var fileChosen = false;
var hasSetupUserMedia = false;

//handle different prefix of the audio context
var AudioContext = AudioContext || webkitAudioContext;
//create the context.
var context = new AudioContext();

//using requestAnimationFrame instead of timeout...
if (!window.requestAnimationFrame)
    window.requestAnimationFrame = window.webkitRequestAnimationFrame;

$(function () {
    "use strict";
    loader = new BufferLoader();
    initBinCanvas();
});

const apiURL = "http://localhost:8080/api/v1/songs";

var start = 0;
var length = 0;
var played = 0;

function playSample() {

    context = new AudioContext();

    played = context.currentTime;
    length = 0;

    console.log("Playing new song");

    fileChosen = true;
    setupAudioNodes();

    var request = new XMLHttpRequest();


    request.addEventListener("progress", updateProgress);
    request.addEventListener("load", transferComplete);
    request.addEventListener("error", transferFailed);
    request.addEventListener("abort", transferCanceled);


    const songObject = getSongInformation();
    var startDate = new Date();
    const songname = songObject.songname;
    const url = songObject.url;
    const artist = songObject.artist;
    const creditsUrl = songObject.creditsUrl;
    const songlength = songObject.length;
    const currentTime = songObject.currentTime;

    console.log(songObject);

    console.log("getting "+url);
    request.open('GET', url, true);

    //request.setRequestHeader('Access-Control-Allow-Origin', '*');
    //request.setRequestHeader('Access-Control-Allow-Methods', 'GET');

    request.responseType = 'arraybuffer';

    // When loaded decode the data
    request.onload = function() {

        $("#title").html(songname);
        $("#album").attr("href", creditsUrl);
        $("#artist").html(artist);
        onWindowResize();
        $("#title, #artist, #album").css("visibility", "visible");

        // decode the data
        context.decodeAudioData(request.response, function(buffer) {
            // when the audio is decoded play the sound
            sourceNode.buffer = buffer;
            var endDate   = new Date();
            var seconds = (endDate.getTime() - startDate.getTime()) / 1000;
            start = currentTime; // + seconds;
            length = songlength;
            sourceNode.start(0, start);

            $("#freq").addClass("animateHue");
            //on error
        }, function(e) {
            console.log(e);
        });
    };
    request.send("");

}

function getSongInformation() {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", apiURL, false);
    xhttp.send(null);
    return JSON.parse(xhttp.responseText);
}


// progress on transfers from the server to the client (downloads)
function updateProgress (oEvent) {
    if (oEvent.lengthComputable) {
        var percentComplete = oEvent.loaded / oEvent.total;
        console.log("Loading music file... " + Math.floor(percentComplete * 100) + "%");
        // $("#loading").html("Loading... " + Math.floor(percentComplete * 100) + "%");
    } else {
        // Unable to compute progress information since the total size is unknown
        console.log("Unable to compute progress info.");
    }
}

function transferComplete(evt) {
    console.log("The transfer is complete.");
    $("#loading").html("");
    //$("button, input").prop("disabled",false);
}

function transferFailed(evt) {
    console.log("An error occurred while transferring the file.");
    $("#loading").html("Loading failed.");
}

function transferCanceled(evt) {
    console.log("The transfer has been canceled by the user.");
    $("#loading").html("Loading canceled.");
}

function initBinCanvas () {

    //add new canvas
    "use strict";
    c = document.getElementById("freq");
    c.width = window.innerWidth;
    c.height = window.innerHeight;
    //get context from canvas for drawing
    ctx = c.getContext("2d");

    ctx.canvas.width  = window.innerWidth;
    ctx.canvas.height = window.innerHeight;

    window.addEventListener( 'resize', onWindowResize, false );

    //create gradient for the bins
    var gradient = ctx.createLinearGradient(0, c.height - 300,0,window.innerHeight - 25);
    gradient.addColorStop(1,'#00f'); //black
    gradient.addColorStop(0.75,'#f00'); //red
    gradient.addColorStop(0.25,'#f00'); //yellow
    gradient.addColorStop(0,'#ffff00'); //white


    ctx.fillStyle = "#9c0001";
}


function onWindowResize()
{
    ctx.canvas.width  = window.innerWidth;
    ctx.canvas.height = window.innerHeight;

    var containerHeight = $("#song_info_wrapper").height();
    var topVal = $(window).height() / 2 - containerHeight / 2;
    $("#song_info_wrapper").css("top", topVal);

    if($(window).width() <= 500) {
        //TODO: not yet working
        $("#title").css("font-size", "40px");
    }
}

var sourceNode;
function setupAudioNodes() {
    // setup a analyser
    analyser = context.createAnalyser();
    // create a buffer source node
    sourceNode = context.createBufferSource();
    //connect source to analyser as link
    sourceNode.connect(analyser);
    // and connect source to destination
    sourceNode.connect(context.destination);
    //start updating
    rafID = window.requestAnimationFrame(updateVisualization);

    sourceNode.onended = function() {
        console.log("Song ended. Playing next one");
        playSample();
    }
}

function playPause() {
    if(context.state === 'running') {
        console.log("Pause");
        audio.pause();
        context.suspend();
    } else {
        console.log("Restart");
        context.close();
        audio.play();
        playSample();
    }
}


function reset () {
    if (typeof sourceNode !== "undefined") {
        sourceNode.stop(0);
    }
    if (typeof microphone !== "undefined") {
        microphone = null;
    }
}


function updateVisualization () {

    // get the average, bincount is fftsize / 2
    if (fileChosen || hasSetupUserMedia) {
        var array = new Uint8Array(analyser.frequencyBinCount/4);
        analyser.getByteFrequencyData(array);

        drawBars(array);
    }

    rafID = window.requestAnimationFrame(updateVisualization);
}

function drawBars (array) {

    //just show bins with a value over the treshold
    var threshold = 0;
    // clear the current state
    ctx.clearRect(0, 0, c.width, c.height);
    //the max count of bins for the visualization
    var maxBinCount = array.length;
    //space between bins
    var space = 3;

    ctx.save();


    ctx.globalCompositeOperation='source-over';

    //console.log(maxBinCount); //--> 1024
    ctx.scale(0.5, 0.5);
    ctx.translate(window.innerWidth, window.innerHeight);


    var bass = Math.floor(array[1]); //1Hz Frequenz
    var radius = 0.45 * $(window).width() <= 450 ? -(bass * 0.25 + 0.45 * $(window).width()) : -(bass * 0.25 + 450);

    var bar_length_factor = 1;
    if ($(window).width() >= 785) {
        bar_length_factor = 1.0;
    }
    else if ($(window).width() < 785) {
        bar_length_factor = 1.5;
    }
    else if ($(window).width() < 500) {
        bar_length_factor = 20.0;
    }
    const currenttime = context.currentTime + start - played;
    const duration = length;


    for ( var i = 0; i < maxBinCount; i++ ){
        ctx.fillStyle = "#a0a0a0";
        if (1-i/(maxBinCount-1) < currenttime/duration){
            ctx.fillStyle = "#ffffff";
        }
        var value = array[i];
        if (value >= threshold) {
            ctx.rotate(-(180 / 128) * Math.PI/180);
            ctx.fillRect(0, radius, $(window).width() <= 450 ? 2 : 3, -value / bar_length_factor);
        }
    }

    for ( var i = 0; i < maxBinCount; i++ ){
        ctx.fillStyle = "#ffffff";
        if (i/maxBinCount >= currenttime/duration){
            ctx.fillStyle = "#a0a0a0";
        }
        var value = array[i];
        if (value >= threshold) {
            ctx.rotate((180 / 128) * Math.PI/180);
            ctx.fillRect(0, radius, $(window).width() <= 450 ? 2 : 3, -value / bar_length_factor);
        }
    }

    ctx.restore();
}
