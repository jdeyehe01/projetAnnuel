$(document).ready(function() {
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
});
 
function taskPerId(id){
	document.location.href="ListTask.html?"+id;
};
