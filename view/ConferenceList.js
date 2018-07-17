

var articlesElt = document.getElementById("articles");
	ajaxGet("http://sebastiendelbeportfolio.alwaysdata.net/getAllByUser/1", function (reponse) {

	var conf = JSON.parse(reponse);
	conf.forEach(function (conferences) {
		
	    var titreElt = document.createElement("h2");
	    titreElt.textContent = conf.name;
	    var contenuElt = document.createElement("p");
	    contenuElt.textContent = conf.description;
	    var contenuElt = document.conf.description;
	    articlesElt.appendChild(titreElt);
	    articlesElt.appendChild(contenuElt);
	});
});
