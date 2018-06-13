const express = require('express');
const bodyParser = require('body-parser');
const controllers = require('../controllers');
const ConferenceController = controllers.ConferenceController;
const GuestController = controllers.GuestController;
const notifier = require('node-notifier');
const path = require("path");

const conferenceRouter = express.Router();
conferenceRouter.use(bodyParser.json());
conferenceRouter.use(bodyParser.urlencoded({ extended: true }));
conferenceRouter.use(express.static(path.join(__dirname + '../../../style')));



conferenceRouter.post('/', function(req, res) {
  const name = req.body.name;
  const date = req.body.date;
  const time = req.body.time;
  const description = req.body.description;

  if(name === undefined || date === undefined || description === undefined || time === undefined) {
    res.status(400).end();

    notifier.notify({
      'title' : 'Champs incorrecte',
      'message' : 'Veuillez remplir tous les champs svp',
      'sound' : false,
      'wait' : true
    });
    return;
  }
  ConferenceController.newConference(name,date,time,description)
  .then((conference) => {

    notifier.notify({
      'title' : 'Information',
      'message' : 'Conférence enregistré',
      'sound' : false,
      'wait' : false
    });
    res.sendFile(path.join(__dirname,'../../view/presentation.html'));

  })
  .catch((err) => {
    res.status(500).end();
  })
});


conferenceRouter.get('/getAll' , function(req,res){
  ConferenceController.getAllConference()
  .then((conferences) => {
    res.status(201).json(conferences);
  })
  .catch((err) => {
    console.error(err);
  })
});


conferenceRouter.get('/getById/:id' , function(req,res){
  const idConference = req.params.id;
  if(idConference === undefined) {
    res.status(400).end();

    notifier.notify({
      'title' : 'Champs incorrecte',
      'message' : "Veuillez renseigner l'id de la conference svp",
      'sound' : false,
      'wait' : true
    });
    return;
  }

  ConferenceController.getConferenceById(idConference)
  .then((conference)=>{
    res.status(200).json(conference);
  })
  .catch((err)=>{
    res.status(200).end();
    console.error(err);
  })


});

conferenceRouter.get('/getAllGuest/:conferenceId' , function(req,res){
  const conferenceId = req.params.conferenceId;
  if(conferenceId === undefined){
    res.status(400).end();
    return;
  }
  GuestController.getAllGuest(conferenceId)
  .then((guests) => {
    res.status(201).json(guests);
  })
  .catch((err) => {
    console.error(err);
  })
});


conferenceRouter.post('/addGuest' , function(req,res){
  const idConf = req.body.idConf;
  const lname = req.body.lname;
  const fname = req.body.fname;
  const email = req.body.email;

  ConferenceController.addGuest(idConf,lname,fname,email)
  .then(() => {
    res.status(201);
    console.log("L'invité a été ajouté");
  })
  .catch((err) => {
    console.error(err);
  })
});


conferenceRouter.delete('/deleteConference/:idConf' , function(req,res){
  const idConf = req.params.idConf;
  if(idConf === undefined ){
    res.status(400).end();
    return;
  }
  ConferenceController.deleteConference(idConf)
  .then((conf) =>{

    notifier.notify({
      'title' : 'Champs incorrecte',
      'message' : 'Conference supprimé',
      'sound' : false,
      'wait' : true
    });
    res.status(201).end();
  });

});



conferenceRouter.get('/nameConf' , function(req,res){

  ConferenceController.findLast()
  .then((conference) =>{
      console.log('Lecture...');
      res.render('ListConference.ejs' , {nameConf: conference.name  });
      res.status(200).end();
  })
  .catch((err)=>{
    console.error(err);
  });

} );


conferenceRouter.get('/lastConf' , function(req,res){

  ConferenceController.findLast()
  .then((conference) =>{
      res.status(200).json(conference);
  })
  .catch((err)=>{
    console.error(err);
  });

} );


module.exports = conferenceRouter;
