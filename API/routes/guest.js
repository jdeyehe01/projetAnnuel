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
const nodemailer = require('nodemailer');

guestRouter.post('/sendMail/:idGuest/:idConference', function(req, res) {

const idGuest = req.params.idGuest;
const idConference = req.params.idConference;

if(idGuest === undefined || idConference === undefined){
  res.status(400).end();
  return;
}
 GuestController.findById(idGuest)
.then((guest)=>{
  ConferenceController.getOneConference(idConference)
  .then((conference)=>{


    const transporter = nodemailer.createTransport({
            service: 'Gmail',
            auth: {
                user: 'no.reply.please.project@gmail.com',
                pass: 'dupondToto12'
            }
    });

    var msg = "Bonjour " + guest.fname +" "+ guest.lname+", \n"+ "Vous avez été invité à la conférence "
    +conference.name + " qui au lieu le " + conference.date +" à "+ conference.time+ " \n <a href='http://localhost:8080/guest/responsGuest/"+guest.id+"/1'> Je serai présent </a> \n\n <p> <a href='http://localhost:8080/guest/responsGuest/"+guest.id+"/0'> Je ne serai pas présent </a> </p>";


    var html = "<html> <body> <p>  "+msg+" </p> </body> </html>"



    var mailOptions = {
    from : 'no.reply.please.project@gmail.com',
    to: guest.email,
    subject: "Invitation à la conférence "+conference.name,
    html : html
    };


      transporter.sendMail(mailOptions, (error, info) => {
          if (error) {
              return console.log(error);
          }
        console.log('Message envoyé: '+info.response);
      });

    transporter.close();
    res.status(200).end();


  })
  .catch((err)=>{
    console.error(err);
  })

})
.catch((err)=>{
  console.error(err);
});
});

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

guestRouter.get('/getAllGuest/:idConference' , function(req,res){
  const idConf = req.params.idConference;
  if(idConf === undefined){
    res.status(400).end();
    return;
  }

  GuestController.getAllGuest(idConf)
  .then((guests) => {
    res.status(201).json(guests);
  })
  .catch((err) => {
      res.status(500).end();
    });
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

  guestRouter.get('/guestById/:idGuest' , function(req,res){
    const idGuest = req.params.idGuest;

    if(idGuest === undefined ){
      res.status(400).end();
      return;
    }

    GuestController.findById(idGuest)
    .then((guest)=>{
      res.status(200).json(guest);
    })
    .catch((err)=>{
      res.status(404).end();
    })

  });

  guestRouter.put('/update/:idGuest' , function(req,res){
    const guestId = req.params.idGuest;
    const fname = req.body.fname;
    const lname = req.body.lname;
    const email = req.body.email;

    if(guestId === undefined || fname === undefined || lname === undefined || email === undefined ){
      res.status(400).end();
      return;
    }

    GuestController.updateGuest(guestId,lname,fname,email)
    .then((guest) => {
      res.status(200).json(guest);
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


  guestRouter.get('/responsGuest/:idGuest/:res', function(req,res){

    const idGuest = req.params.idGuest;
    const respons = req.params.res;

    if(idGuest === undefined || respons === undefined){
      res.status(400).end();
      return;
    }

    GuestController.respond(idGuest,respons);
    res.status(200).send('Merci de votre réponse');


  });

module.exports = guestRouter;
