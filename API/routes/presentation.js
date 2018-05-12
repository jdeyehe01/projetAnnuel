const express = require('express');
const bodyParser = require('body-parser');
const controllers = require('../controllers');
const PresentationController = controllers.PresentationController;
const ConferenceController = controllers.ConferenceController;
const notifier = require('node-notifier');
const presentationRouter = express.Router();

presentationRouter.use(bodyParser.json());
presentationRouter.use(bodyParser.urlencoded({ extended: true }));

presentationRouter.post('/', function(req, res) {
  const title = req.body.title;
  const amount =parseFloat(req.body.amount);
  const description = req.body.description;

  if(title === undefined || amount === undefined || description ===undefined) {

    notifier.notify({
      'title' : 'Champs incorrecte',
      'message' : 'Veuillez remplir tous les champs svp',
      'sound' : false,
      'wait' : true
    });

    res.status(400).end();
    return;

  }

  PresentationController.newPresentation(title,amount,description)
  .then((presentation) => {

    notifier.notify({
      'title': 'Information',
      'message': 'Presentation enregistre',
      'sound': false,
      'wait' : false
      });
  })
  .catch((err) => {
    res.status(500).end();
  })
    res.redirect('https://www.google.fr');


});


  presentationRouter.delete('/deletePresentationById/:idPresentation' , function(req,res){

    const idPresentation = parseInt(req.params.idPresentation);
    if(idPresentation === undefined || idPresentation <= 0 ){
      res.status(500).end();
      return;
    }

    PresentationController.deletePresentation(idPresentation)
    .then(()=>{

      notifier.notify({
        'title': 'Information',
        'message': 'présentation supprimé',
        'sound': false,
        'wait' : false
        });

      res.status(200).end();
    })
    .catch((err)=>{
      console.error(err);
    })
  });

  presentationRouter.post('/addConference/:idPresentation/:idConference',function(req,res){
    const idPresentation = parseInt(req.params.idPresentation);
    const idConference = parseInt(req.params.idConference);

    if(idPresentation === undefined || idPresentation <=0 || idConference === undefined || idConference <=0){
      res.status(401).end();
      return;
    }

    PresentationController.addConference(idPresentation , idConference);
    res.status(200).end();
  });


  presentationRouter.get('/getPresentationById/:idPresentation/:idConference' , function(req,res){
    const idPresentation = parseInt(req.params.idPresentation);
    const idConference = parseInt(req.params.idConference);

    if(idPresentation === undefined || idPresentation <=0 || idConference === undefined || idConference <=0){
      res.status(401).end();
      return;
    }

    PresentationController.getPresentationById(idConference,idPresentation)
    .then((presentation) => {
      res.status(200).json(presentation);
    })
    .catch((err)=>{
      console.error(err);
    })

  });

  presentationRouter.get('/getAllPresentationForConference/:idConference', function(req,res) {
    const idConference = parseInt(req.params.idConference);

    if( idConference === undefined || idConference <=0){
      res.status(401).end();
      return;
    }
    PresentationController.getAllPresentationForConference(idConference)
    .then((presentations) => {
      res.status(200).json(presentations);
    })
    .catch((err) => {
      console.error(err);
    })
  });


  presentationRouter.post('/updatePresentation/:idPresentation' , function(req,res){

    const title = req.body.title;
    const amount = parseFloat(req.body.amount);
    const description = req.body.description;
    const idPresentation = parseInt(req.params.idPresentation);

    if( idPresentation === undefined || idPresentation <=0 || amount === undefined || amount <=0 || title ===undefined || description === undefined){
      res.status(403).end();
      return;
    }

    PresentationController.updatePresentation(idPresentation,title,amount,description)
    .then((presentation) => {
      res.status(200).json(presentation);
    })
    .catch((err)=>{
      console.error(err);
    })

  });

module.exports = presentationRouter;
