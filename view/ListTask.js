$(document).ready(function() {
	var url = window.location.href;
	var idconf = url.split("?");
	$.ajax({
	    type: "GET",
	     url: "http://sebastiendelbeportfolio.alwaysdata.net/task/getAllTaskForConference/"+idconf,
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
		success: function( result){
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
});



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