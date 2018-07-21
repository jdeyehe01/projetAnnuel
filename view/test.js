var request = new XMLHttpRequest();
request.open('GET', 'http://sebastiendelbeportfolio.alwaysdata.net/conferences/getAll', true);

request.onload = function () {
  // Begin accessing JSON data here
  var data = JSON.parse(this.response);
  if (request.status >= 200 && request.status < 400) {
    data.forEach(movie => {
      const card = document.createElement('div');
      card.setAttribute('class', 'card');

      const h1 = document.createElement('h1');
      h1.textContent = movie.title;

      const p = document.createElement('p');
      movie.description = movie.description.substring(0, 300);
      p.textContent = `${movie.description}...`;

      container.appendChild(card);
      card.appendChild(h1);
      card.appendChild(p);
    });
  } else {
    const errorMessage = document.createElement('marquee');
    errorMessage.textContent = `Gah, it's not working!`;
    app.appendChild(errorMessage);
  }
}

request.send();





function showListConf(jsonObj) {
  var listconfs = jsonObj['conference'];
      
  for (var i = 0; i < listconfs.length; i++) {
      var newDiv = document.createElement("div"); 
      var myH2 = document.createElement('h2');
      var myPara = document.createElement('p');

      var input = document.createElement("input");
        input.setAttribute("name","voirLaConf");
        input.setAttribute("value","voir");
        input.setAttribute("type","button");
      input.setAttribute("onClick","self.location.href='listTaches.html'")

      myH2.textContent = listconfs[i].name;
      myPara.textContent = 'Elle dure : ' + listconfs[i].time + 'Description : ' + listconfs[i].description;

      newDiv.appendChild(myH2);
      newDiv.appendChild(myPara1);
      newDiv.appendChild(input);

      section.appendChild(myArticle);
      alert("resultat good i think");
  }
}


var request = new XMLHttpRequest();

request.open('GET', "http://sebastiendelbeportfolio.alwaysdata.net/conferences/getAll", false);
request.responseType = 'json';
request.send();

request.onload = function() {
var confs = request.response;
  showListConf(confs);
}

function showListConf(jsonObj) {
  var listconfs = jsonObj['conference'];
      
  for (var i = 0; i < listconfs.length; i++) {
      var newDiv = document.createElement("div"); 
      var myH2 = document.createElement('h2');
      var myPara = document.createElement('p');

      var input = document.createElement("input");
        input.setAttribute("name","voirLaConf");
        input.setAttribute("value","voir");
        input.setAttribute("type","button");
      input.setAttribute("onClick","self.location.href='listTaches.html'")

      myH2.textContent = listconfs[i].name;
      myPara.textContent = 'Elle dure : ' + listconfs[i].time + 'Description : ' + listconfs[i].description;

      newDiv.appendChild(myH2);
      newDiv.appendChild(myPara1);
      newDiv.appendChild(input);

      section.appendChild(myArticle);
      alert("resultat good i think");
  }
}


                  $.ajax({
                    url: "http://sebastiendelbeportfolio.alwaysdata.net/conference/getAll",
                    type: "GET",
                    contentType: "application/json;charset=utf-8",
                    dataType: "json",
                    success:  function (result) {
                      $(result).each( function (index, m){
                        var ligne = $("<tr> </tr>");
                        ligne.append($("<td>" + m.id + "</td>"));
                        ligne.append($("<td>" + m.name + "</td>"));
                        ligne.append($("<td>" + m.date + "</td>"));
                        ligne.append($("<td>" + m.description + "</td>"));
                        ligne.append($("<td> <input<td>"))
                        $('tbody').append(ligne);
                      });
                    },
                    error: function (errormessage) {
                        alert(errormessage.responseText);
                    }
                  });
                });


//LIST conf

    function SubFormTask() {
      var xhttp = new XMLHttpRequest();
      var url = "http://sebastiendelbeportfolio.alwaysdata.net/task/";

      xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
         xhttp.open("POST", url, true);
          xhttp.setRequestHeader(json, myNewTask);
          xhttp.send();
        }
      };
    }

    var request = new XMLHttpRequest();

    request.open('GET', "http://sebastiendelbeportfolio.alwaysdata.net/conferences/getAll", false);
    request.responseType = 'json';
    request.send();

    request.onload = function() {
    var confs = request.response;
      showListConf(confs);
    }

    function showListConf(jsonObj) {
      var listconfs = jsonObj['conference'];
          
      for (var i = 0; i < listconfs.length; i++) {
        var nameCon = document.createElement('h2');
        var myPara1 = document.createElement('p');
        var myPara2 = document.createElement('p');
        var myPara3 = document.createElement('p');

        var myList = document.createElement('ul');

        var input = document.createElement("input");
        input.setAttribute("name","voirLaConf");
        input.setAttribute("value","voir");
        input.setAttribute("type","button");
        input.setAttribute("onClick","self.location.href='listTaches.html'")
        paragraphe.appendChild(input);

        myH2.textContent = listconfs[i].name;
        myPara1.textContent = 'Secret identity: ' + listconfs[i].secretIdentity;
        myPara2.textContent = 'Age: ' + listconfs[i].age;
        myPara3.textContent = 'Superpowers:';
            
        var superPowers = listconfs[i].powers;
        for (var j = 0; j < superPowers.length; j++) {
          var listItem = document.createElement('li');
          listItem.textContent = superPowers[j];
          myList.appendChild(listItem);
        }

        

        myArticle.appendChild(myH2);
        myArticle.appendChild(myPara1);
        myArticle.appendChild(myPara2);
        myArticle.appendChild(myPara3);
        myArticle.appendChild(myList);

        section.appendChild(myArticle);
      }
    }

