let rafID = null;
let analyser = null;
let c = null;
let ctx = null;

let loader;
let filename;
let fileChosen = false;
let hasSetupUserMedia = false;

//handle different prefix of the audio context
var AudioContext = AudioContext || webkitAudioContext;
//create the context.
var context = new AudioContext();

//Reduce canvas drawing stripes
let reduction = 1;

//using requestAnimationFrame instead of timeout...
if (!window.requestAnimationFrame)
    window.requestAnimationFrame = window.webkitRequestAnimationFrame;

$(function () {
    "use strict";
    loader = new BufferLoader();
    initBinCanvas();
});

function isTabletOrMobile() {
    var check = false;
    (function (a) {
        if (/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino|android|ipad|playbook|silk/i.test(a) || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0, 4))) check = true;
    })(navigator.userAgent || navigator.vendor || window.opera);
    return check;
}

function isMobile(){
    var check = false;
    (function(a){if(/(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino/i.test(a)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0,4))) check = true;})(navigator.userAgent||navigator.vendor||window.opera);
    return check;
}

if(isTabletOrMobile()){
    reduction = 2;
}
if(isMobile()){
    reduction = 4;
}

const apiURL = "/api/v1/songs";

let start = 0;
let length = 0;
let played = 0;

function playSong() {

    context = null;
    context = new AudioContext();

    played = context.currentTime;
    length = 0;

    console.log("Playing new song");

    fileChosen = true;
    setupAudioNodes();

    let request = new XMLHttpRequest();


    request.addEventListener("progress", updateProgress);
    request.addEventListener("load", transferComplete);
    request.addEventListener("error", transferFailed);
    request.addEventListener("abort", transferCanceled);


    const songObject = getSongInformation();
    let startDate = new Date();
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
            let endDate   = new Date();
            let seconds = (endDate.getTime() - startDate.getTime()) / 1000;
            start = currentTime; // + seconds;
            length = songlength;
            sourceNode.start(0, start);

            $("#freq").addClass("animateHue");
        }, function(e) {
            console.log(e);
        });
    };
    request.send("");

}

function getSongInformation() {
    let xhttp = new XMLHttpRequest();
    xhttp.open("GET", apiURL, false);
    xhttp.send(null);
    return JSON.parse(xhttp.responseText);
}


// progress on transfers from the server to the client (downloads)
function updateProgress (oEvent) {
    if (oEvent.lengthComputable) {
        let percentComplete = oEvent.loaded / oEvent.total;
        console.log("Loading music file... " + Math.floor(percentComplete * 100) + "%");
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
    let gradient = ctx.createLinearGradient(0, c.height - 300,0,window.innerHeight - 25);
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

    let containerHeight = $("#song_info_wrapper").height();
    let topVal = $(window).height() / 2 - containerHeight / 2;
    $("#song_info_wrapper").css("top", topVal);

    if($(window).width() <= 500) {
        $("#title").css("font-size", "40px");
    }
}

let sourceNode;
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
        //Restet everything
        analyser = null;
        sourceNode = null;
        rafID = null;

        console.log("Song ended. Playing next one");
        playSong();
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
        playSong();
    }
}

function updateVisualization () {
    // get the average, bincount is fftsize / 2
    if (fileChosen || hasSetupUserMedia) {
        let array = new Uint8Array(analyser.frequencyBinCount/4);
        analyser.getByteFrequencyData(array);

        drawBars(array);
    }

    rafID = window.requestAnimationFrame(updateVisualization);
}

function drawBars (array) {

    array = array.filter(function(value, index) {
        return index % reduction == 0 && index <= array.length / 2;
    });

    //just show bins with a value over the treshold
    let threshold = 0;
    // clear the current state
    ctx.clearRect(0, 0, c.width, c.height);
    //the max count of bins for the visualization
    let maxBinCount = array.length;
    //space between bins
    let space = 3;

    ctx.save();


    ctx.globalCompositeOperation='source-over';

    ctx.scale(0.5, 0.5);
    ctx.translate(window.innerWidth, window.innerHeight);


    let bass = Math.floor(array[1]); //1Hz Frequenz
    let radius = 0.45 * $(window).width() <= 450 ? -(bass * 0.25 + 0.45 * $(window).width()) : -(bass * 0.25 + 450);

    let bar_length_factor = 1;
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

    const stripeWidth = $(window).width() <= 450 ? 2*reduction : 3*reduction;
    const rotationFactor = (180 / 128) * Math.PI/180 * reduction;

    for ( let i = 0; i < maxBinCount; i++ ){
        ctx.fillStyle = "#ffffff";
        if (i/(maxBinCount*2) > currenttime/duration){
            ctx.fillStyle = "#a0a0a0";
        }
        let value = array[i];
        if (value >= threshold) {
            ctx.fillRect(0, radius, stripeWidth, -value / bar_length_factor);
            ctx.rotate(rotationFactor);
        }
    }

    for ( let i = maxBinCount; i > 0 ; i-- ){
        ctx.fillStyle = "#ffffff";
        if (((maxBinCount*2)-i)/(maxBinCount*2) > currenttime/duration){
            ctx.fillStyle = "#a0a0a0";
        }
        let value = array[i];
        if (value >= threshold) {
            ctx.fillRect(0, radius, stripeWidth, -value / bar_length_factor);
            ctx.rotate(rotationFactor);
        }
    }

    ctx.restore();
}
