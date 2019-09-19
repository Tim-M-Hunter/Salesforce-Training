var sortValue;

$(document).ready(function(){
	
	$("#greenList, #redList").sortable({
		connectWith:'#greenList, #redList',
		receive: sortIn,
		over: sortIn,
		out: sortOut,
		beforeStop: function(event, ui){
			if(sortValue == 0){
				ui.item.remove();
			}
		}
	});
	
});

function sortIn(){
	sortValue = 1;
}

function sortOut(){
	sortValue = 0;
}

function addGreenItem(){
	var itemName = $("#newItem").val();
	$("#greenList").append("<li>" + itemName + "</li>");
}

function addRedItem(){
	var itemName = $("#newItem").val();
	$("#redList").append("<li>" + itemName + "</li>");
}