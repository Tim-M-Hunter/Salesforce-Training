var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
var dayNames = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];

//Holds the month and year to display
var month, year;

function initialize(){
	var today = new Date();
	month = today.getMonth();
	year = today.getFullYear();
	buildCalendar();
}

function increaseMonth(){
	month++;
	if(month > 11){
		year++;
		month = 0;
	}
	buildCalendar();
}

function decreaseMonth(){
	month--;
	if(month < 0){
		year--;
		month = 11;
	}
	buildCalendar();
}

function buildCalendar(){
	var oldCalendar = document.getElementById("calendarTable");
	if(oldCalendar != null){
		oldCalendar.remove();
	}
	
	var calendar = document.createElement("table");
	calendar.id = "calendarTable";
	
	buildHeader(calendar);
	buildBody(calendar);
	
	document.getElementById("container").appendChild(calendar);
	
	highlightToday();
}

function buildHeader(table){
	var header, row, cell;
	
	header = table.createTHead();
	row = header.insertRow(0);
	
	cell = row.insertCell(-1);
	cell.colSpan = "2";
	cell.onclick = decreaseMonth;
	cell.innerHTML = "<< Previous";
	cell.id = "decreaseMonthCell";
	
	cell = row.insertCell(-1);
	cell.colSpan = "3";
	cell.innerHTML = monthNames[month] + " - " + year;
	cell.id = "monthYearCell";
	
	cell = row.insertCell(-1);
	cell.colSpan = "2";
	cell.onclick = increaseMonth;
	cell.innerHTML = "Next >>";
	cell.id = "increaseMonthCell";
	
	row = header.insertRow(-1);
	for(var day in dayNames){
		cell = row.insertCell(-1);
		cell.innerHTML = dayNames[day];
		cell.className = "dayNames";
	}
}

function buildBody(table) {
	var body, row, cell;
	var firstOfMonth = new Date(year, month, 1).getDay();
	var numDaysInMonth = daysInMonth(year, month);
	
	body = table.createTBody();
	row = body.insertRow(0);
	for(var i = 0; i < firstOfMonth; i++){
		cell = row.insertCell(-1);
		cell.className = "fillerCell";
	}
	
	for(var i = 0; i < numDaysInMonth; i++){
		var newWeek = (i + firstOfMonth) % 7;
		if(newWeek == 0){
			row = body.insertRow(-1);
		}
		
		cell = row.insertCell(-1);
		cell.className = "dayCell";
		cell.id = "day" + (i + 1);
		cell.innerHTML = i + 1;
	}
}

function highlightToday(){
	var today = new Date();
	if(today.getMonth() == month && today.getFullYear() == year){
		var id = "day" + today.getDate();
		var day = document.getElementById(id);
		day.style.background = 'yellow';
	}
}

function daysInMonth(year, month) {
	return new Date(year, month + 1, 0).getDate();
}