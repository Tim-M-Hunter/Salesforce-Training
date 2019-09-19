
$(document).ready(function(){
	var numUsers = 10;
	dummyData(numUsers);
	
	$(".modal-content").resizable();
	$(".modal-content").draggable();
});

function showUserMenu(){
	$(".modal").css("display", "block");
}

function closeModal(){
	$(".modal").css("display", "none");
}

function submitNewUser(){
	var name = $("#nameField").val();
	var email = $("#emailField").val();
	var password = $("#passwordField").val();
	
	$("#errorField").html("");
	var error = false;
	if(!validateLength(name)){
		error = true;
		displayError("Invalid Name Length");
	}
	if(!validateLength(email)){
		error = true;
		displayError("Invalid Email Length");
	}
	if(!validateEmail(email)){
		error = true;
		displayError("Invalid Email Format");
	}
	if(!validateLength(password)){
		error = true;
		displayError("Invalid Password Length");
	}
	
	if(!error){
		insertUser(name, email, password);
		$("#nameField").val("");
		$("#emailField").val("");
		$("#passwordField").val("");
		$("#errorField").css("display", "none");
		closeModal();
	}
}

function validateLength(value){
	return value.length > 2;
}

function validateEmail(value){
	var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(String(value).toLowerCase());
}

function displayError(message){
	$("#errorField").css("display", "block");
	$("#errorField").append("<p>" + message + "</p>");
}

function insertUser(name, email, password){
	var row, cell;
	row = getTableBody().insertRow(-1);
	cell = row.insertCell(-1);
	cell.innerHTML = name;
	cell = row.insertCell(-1);
	cell.innerHTML = email;
	cell = row.insertCell(-1);
	cell.innerHTML = password;
}

//jQuery was being annoying about getting the tbody so I used basic JS
function getTableBody(){
	var body = document.getElementById("usersTableBody");
	if(body == null){
		var table = document.getElementById("usersTable");
		body = table.appendChild(document.createElement("tbody"));
		body.id = "usersTableBody";
		return body;
	} else {
		return body;
	}
}

function dummyData(numUsers){
	var dummyNames = ["John", "Jill", "Rick", "Bob", "Susan", "Lilly", "George", "Fred", "Winston"];
	var dummyPasswords = ["asdfa", "gawxcs", "gsadfw", "wefsve", "qdfxve", "bsdgjjs"];
	
	for(var i = 0; i < numUsers; i++){
		var nameIndex = Math.floor(Math.random() * dummyNames.length);
		var passwordIndex = Math.floor(Math.random() * dummyPasswords.length);
		insertUser(dummyNames[nameIndex], dummyNames[nameIndex] + "@fake.com", dummyPasswords[passwordIndex]);
	}
}