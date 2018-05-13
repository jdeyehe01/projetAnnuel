const express = require('express');
const bodyParser = require('body-parser');
const controllers = require('../controllers');
const notifier = require('node-notifier');
const GuestController = controllers.GuestController;
const ConferenceController = controllers.ConferenceController;
const path = require("path");
const guestRouter = express.Router();
guestRouter.use(bodyParser.json());
guestRouter.use(bodyParser.urlencoded({ extended: true }));
guestRouter.use(express.static(path.join(__dirname + '../../../style')));


guestRouter.post('/', function(req, res) {
  const fname = req.body.fname;
  const lname = req.body.lname;
  const email = req.body.email;
  if(fname === undefined || lname === undefined || email === undefined ) {
    res.status(400).end();
    notifier.notify({
      'title' : 'Champs incorrecte',
      'message' : 'Veuillez remplir tous les champs svp',
      'sound' : false,
      'wait' : true
    });
    return;
  }
  GuestController.newGuest(lname,fname,email)
  .then((guest) => {

    notifier.notify({
      'title' : 'Information',
      'message' : 'Invité créé',
      'sound' : false,
      'wait' : true
    });

    ConferenceController.findLast()
    .then((conference) =>{
      GuestController.addConference(guest.id ,conference.id);
    });


    res.sendFile(path.join(__dirname,'../../view/index.html'));
  })
  .catch((err) => {
    res.status(500).end();
  })
});


guestRouter.get('/getAllConference/:idGuest' , function(req,res){
  const guestId = req.params.idGuest;
  if(guestId === undefined){
    res.status(400).end();
    return;
  }

ConferenceController.getAllConferenceByGuest(guestId)
  .then((conferences) => {
    res.status(201).json(conferences);
  })
  .catch((err) => {
      res.status(500).end();
    });
  });


  guestRouter.delete('/deleteGuest/:idGuest' , function(req,res){

    const idGuest = req.params.idGuest;
    if(idGuest === undefined ){
      res.status(500).end();
      return;
    }

    GuestController.deleteGuest(idGuest)
    .then(()=>{
      console.log('Delete');
      res.status(202).end();
    })
    .catch((err)=>{
      console.error(err);
    })
  });

module.exports = guestRouter;
