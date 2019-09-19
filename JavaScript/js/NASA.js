
var dateSelected;

function changeDate() {
	dateSelected = true;
	getData();
}

function getData(){
	const Http = new XMLHttpRequest();
	var url = "https://api.nasa.gov/planetary/apod?api_key=jT9a9djmlXvr6QGYLXet52uTP4ycNUGYnqdQeCA7";
	if(dateSelected){
		url += "&date=" + document.getElementById("dateField").value;
	}
	Http.open("GET", url);
	Http.send();
	
	Http.onreadystatechange = (e) => {
		
		var data = JSON.parse(Http.responseText);
		var urlName = data.url.split("/");
		
		document.getElementById("imagePicture").src = data.url;
		document.getElementById("imageTitle").innerHTML = data.title;
		document.getElementById("imageExplanation").innerHTML = data.explanation;
		document.getElementById("imageName").innerHTML = urlName[urlName.length - 1];
		document.getElementById("imageCopyright").innerHTML = data.copyright;
	}
}

