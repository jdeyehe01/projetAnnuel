const express = require('express');
const bodyParser = require('body-parser');
const controllers = require('../controllers');
const ConferenceController = controllers.ConferenceController;
const GuestController = controllers.GuestController;
const UserController = controllers.UserController;
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
    UserController.findLast()
    .then((user)=>{
      ConferenceController.addUser(conference.id ,user.id);

    })
    .catch((err)=>{
      console.error(err);
    });


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

conferenceRouter.get('/getAllByUser/:idUser' , function(req,res){
  const userId = req.params.idUser;
  if(userId === undefined) {
    res.status(400).end();

    notifier.notify({
      'title' : 'Parametre incorrecte',
      'message' : "Veuillez renseigner l'id du user svp ",
      'sound' : false,
      'wait' : true
    });
    return;
  }
  ConferenceController.getAllConferenceByUser(userId)
  .then((conferences) => {
    res.status(201).json(conferences);
  })
  .catch((err) => {
    console.error(err);
  })
});

conferenceRouter.get('/getAllByGuest/:idGuest' , function(req,res){
  const guestId = req.params.idGuest;
  if(guestId === undefined) {
    res.status(400).end();

    notifier.notify({
      'title' : 'Parametre incorrecte',
      'message' : "Veuillez renseigner l'id de l'invité svp ",
      'sound' : false,
      'wait' : true
    });
    return;
  }
  ConferenceController.getAllConferenceByGuest(guestId)
  .then((conferences) => {
    res.status(201).json(conferences);
  })
  .catch((err) => {
    console.error(err);
  })
});

conferenceRouter.get('/getById/:idConf' , function(req,res){
  const idConference = req.params.idConf;
    if(idConference === undefined ) {
    res.status(400).end();

    notifier.notify({
      'title' : 'Champs incorrecte',
      'message' : "Veuillez renseigner l'id de la conference ",
      'sound' : false,
      'wait' : true
    });
    return;
  }

  UserController.findLast()
  .then((user)=>{
    ConferenceController.getConferenceById(idConference,user.id)
    .then((conference)=>{
      res.status(200).json(conference);
    })
    .catch((err)=>{
      res.status(400).end();
      console.error(err);
    })
  })
  .catch((err)=>{
    res.status(400).end();
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
    res.status(200).end();
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


conferenceRouter.get('/conferenceById/:idConference' , function(req,res){
  const idConf = req.params.idConference;
  if(idConf === undefined ){
    res.status(400).end();
    return;
  }

  ConferenceController.getOneConference(idConf)
  .then((conf)=>{
    res.status(200).json(conf);
  })
  .catch((err)=>{
    res.status(404).end();
  })

});


conferenceRouter.put('/update/:idConference',function(req,res){
  const idConf = req.params.idConference;
  const name = req.body.name;
  const date = req.body.date;
  const time = req.body.time;
  const description = req.body.description;

  if(idConf === undefined ){
    res.status(400).end();
    return;
  }

  ConferenceController.updateConference(idConf,name,date,time,description)
  .then((conference)=> {
    res.status(200).json(conference);
  })
  .catch((err)=>{
    console.error(err);
  });

});


conferenceRouter.get('/getFiveLast/:idUser' , function(req,res){

const idUser = req.params.idUser;
if(idUser === undefined ) {
  res.status(400).end();
  return;
}

  ConferenceController.findFiveLast(idUser)
  .then((conferences)=>{
    res.status(200).json(conferences);
  })
  .catch((err)=>{
    console.error(err);
  })
});


module.exports = conferenceRouter;
