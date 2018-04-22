const express = require('express');
const bodyParser = require('body-parser');
const controllers = require('../controllers');
const ConferenceController = controllers.ConferenceController;
const GuestController = controllers.GuestController;

const conferenceRouter = express.Router();
conferenceRouter.use(bodyParser.json());

conferenceRouter.post('/', function(req, res) {
  const name = req.body.name;
  const dateDebut = req.body.dateDebut;
  const adresse = req.body.adresse;
  const codePostal = req.body.codePostal;
  const ville = req.body.ville;
  const heureDebut = req.body.heureDebut;
  const description = req.body.description;


  if(name === undefined || dateDebut === undefined || adresse === undefined || codePostal === undefined || ville === undefined || description === undefined) {
    res.status(400).end();
    return;
  }
  ConferenceController.newConference(name,dateDebut,adresse,codePostal,ville,heureDebut,description)
  .then((conference) => {
    res.status(201).json(conference);
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
    console.log('Delete ! ');
    res.status(201).end();
  });

});





module.exports = conferenceRouter;
