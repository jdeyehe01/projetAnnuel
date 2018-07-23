const express = require('express');
const bodyParser = require('body-parser');
const controllers = require('../controllers');
const UserController = controllers.UserController;
const notifier = require('node-notifier');

const userRouter = express.Router();
userRouter.use(bodyParser.json());
userRouter.use(bodyParser.urlencoded({ extended: true }));



userRouter.get('/confirmed/:idUser' , function(req,res){
  const idUser = req.params.idUser;
  if(idUser === undefined ) {
    res.status(400).end();
    return;
  }

  UserController.confirmed(idUser)
  .then(()=>{
    res.end('Votre est confirme ');
  })
});

userRouter.post('/signUp', function(req, res) {
  const email = req.body.email;
  const pw = req.body.password;
  if(email === undefined || pw === undefined ) {
    res.status(400).end();

    notifier.notify({
      'title' : 'Champs incorrecte',
      'message' : 'Veuillez remplir tous les champs svp',
      'sound' : false,
      'wait' : true
    });
    return;
  }
  UserController.newUser(email,pw)
  .then((user) => {
    UserController.verifyEmail(user.id)
    res.status(200).json(user);
  })
  .catch((err) => {
    res.status(500).end();
  })
});

userRouter.get('/getAll' , function(req,res){
console.log(req.session);
  UserController.getAllUser()
  .then((users) => {
    res.status(200).json(users);
  })
  .catch((err) => {
    console.error(err);
  })
});

userRouter.post('/auth', function(req,res){
  const email = req.body.email;
  const pw = req.body.password;
  if(email === undefined || pw === undefined ) {
    res.status(400).end();

    notifier.notify({
      'title' : 'Champs incorrecte',
      'message' : 'Veuillez remplir tous les champs...',
      'sound' : false,
      'wait' : true
    });
    return;
  }

  UserController.authent(email,pw)
  .then((user)=>{
    if(user == null){
      res.status(400).end();
      return;
    }
      res.status(200).json(user);
  })
  .catch((err)=>{
    console.error(err);
});

});

userRouter.get('/loggedInUser/:email' , function(req,res){
const email = req.params.email;
if(email === undefined){
  res.status(400).end();
  return;
}

UserController.findByEmail(email)
  .then((user) =>{
    console.log(user);
      res.status(200).json(user);
  })
  .catch((err)=>{
    console.error(err);
  });


});


module.exports = userRouter;
