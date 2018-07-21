$(document).ready(function() {
    function lesListConf() {
	$.ajax({
	    type: "GET",
	    url: "http://sebastiendelbeportfolio.alwaysdata.net/conference/getAll",
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
		success: function( result){
	    	$(result).each(function (index, m){
		    	var ligne = $("<tr> </tr>");
		    	ligne.append($("<td>" + m.id + "</td>"));
		        ligne.append($("<td>" + m.name + "</td>"));
		        ligne.append($("<td>" + m.date + "</td>"));
		        ligne.append($("<td>" + m.description + "</td>"));
		        ligne.append($("<td> <input id=\"btntask\"  type=\"button\" value=\"details\" onClick=\"taskPerId("+m.id+")\"> </td>"));
		        $('#displayconf').append(ligne);
	      	});
	    },
	    error: function (errormessage) {
	        alert(errormessage.responseText);
	    }
	});
};
});
  
$('#btntask').on("click", function () {
  var result = $('tr:has(.clicked)');
  if (result.lenght != 0) {
  	document.location.href="ListTask.html?123";
	var ligne = $("<tr> </tr>");
	ligne.append($("<td>e</td>"));
    ligne.append($("<td>e</td>"));
    ligne.append($("<td>h</td>"));
    ligne.append($("<td> g</td>"));
    //alert(ligne).html().text;
   // ligne.append($("<td> <input id=\"btntask\"  type=\"button\" value=\"details\" onClick=\"taskPerId()\"> </td>"));
    $('#displaytask').append(ligne);
    //var idconf = $(this).parent().siblings(":first").text()
    //taskPerId(idconf);
    
  }
});

function taskPerId(idconf){
	$.ajax({
	    type: "GET",
	     url: "http://sebastiendelbeportfolio.alwaysdata.net/task/getAllTaskForConference/"+idconf,
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
		success: function( result){
			document.location.href="ListTask.html";
	    	$(result).each(function (index, m){
		    	var ligne = $("<tr> </tr>");
		        ligne.append($("<td>" + m.title + "</td>"));
		        ligne.append($("<td>" + m.amount + "</td>"));
		        ligne.append($("<td>" + m.duration + "</td>"));
		        ligne.append($("<td> <input type=\"button\" value=\"details\" onClick=\"DeleteTask("+m.id+','+idconf+")\"> </td>"));
		        $('#displaytask').append(ligne);
	      	});
	    },
	    error: function (errormessage) {
	        alert(errormessage.responseText);
	    }
	});
};


function DeleteTask(idtask,idconf){
	$.ajax({
	    type: "POST",
	     url: "http://sebastiendelbeportfolio.alwaysdata.net/task/deleteTaskById/"+idtask,
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
		success: function( result){
			alert("Tâche suppimée !");
			reload(idconf);
	    },
	    error: function (errormessage) {
	        alert(errormessage.responseText);
	    }
	});
};



function reload(idconf){
	$.ajax({
	    type: "GET",
	    url: "http://sebastiendelbeportfolio.alwaysdata.net/task/getAllTaskForConference/"+idconf,
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
		success: function( result){
			document.location.href="ListTask.html";
	    	$(result).each(function (index, m){
		    	var ligne = $("<tr> </tr>");
		        ligne.append($("<td>" + m.title + "</td>"));
		        ligne.append($("<td>" + m.amount + "</td>"));
		        ligne.append($("<td>" + m.duration + "</td>"));
		        ligne.append($("<td> <input type=\"button\" value=\"details\" onClick=\"DeleteTask("+m.id+")\"> </td>"));
		        $('#displaytask').append(ligne);
	      	});
	    },
	    error: function (errormessage) {
	        alert(errormessage.responseText);
	    }
	});
};


