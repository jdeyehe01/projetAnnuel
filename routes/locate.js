const express = require('express');
const bodyParser = require('body-parser');
const controllers = require('../controllers');
const LocateController = controllers.LocateController;
const ConferenceController = controllers.ConferenceController;
const notifier = require('node-notifier');
const locateRouter = express.Router();

locateRouter.use(bodyParser.json());
locateRouter.use(bodyParser.urlencoded({ extended: true }));

locateRouter.post('/', function(req, res) {
  const name = req.body.name;
  const address = req.body.address;
  const city = req.body.city;
  const cityCode = parseInt(req.body.cityCode);


  if(name === undefined || address === undefined || city === undefined || cityCode === undefined ) {

    notifier.notify({
      'title' : 'Champs incorrecte',
      'message' : 'Veuillez remplir tous les champs svp',
      'sound' : false,
      'wait' : true
    });

    res.status(400).end();

  }

  LocateController.newLocate(name,address,cityCode,city)
  .then((locate) => {

    //res.status(201).json(locate);
    notifier.notify({
      'title': 'Information',
      'message': 'Lieu enregistre',
      'sound': false,
      'wait' : false
      });
  })
  .catch((err) => {
    res.status(500).end();
  })
    res.redirect('https://www.google.fr');


});


locateRouter.get('/getLocateById/:idLocate' , function(req,res){
  const locateId = parseInt(req.params.idLocate);
  if(locateId === undefined  || locateId <= 0 ){
    res.status(400).end();
    return;
  }

LocateController.getAllLocate(locateId)
  .then((locates) => {
    res.status(201).json(locates);
  })
  .catch((err) => {
      res.status(500).end();
    });
  });


  locateRouter.delete('/deleteLocateById/:idLocate' , function(req,res){

    const idLocate = parseInt(req.params.idLocate);
    if(idLocate === undefined || idLocate <= 0 ){
      res.status(500).end();
      return;
    }

    LocateController.deleteLocate(idLocate)
    .then(()=>{
      console.log('Delete');
      res.status(202).end();
    })
    .catch((err)=>{
      console.error(err);
    })
  });

  locateRouter.post('/addConference/:idLocate/:idConference',function(req,res){
    const idLocate = parseInt(req.params.idLocate);
    const idConference = parseInt(req.params.idConference);

    if(idLocate === undefined || idLocate <=0 || idConference === undefined || idConference <=0){
      res.status(401).end();
      return;
    }

    LocateController.addConference(idLocate , idConference);
    res.status(201).end();
  });

module.exports = locateRouter;
