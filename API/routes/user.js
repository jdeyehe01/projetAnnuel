const express = require('express');
const bodyParser = require('body-parser');
const controllers = require('../controllers');
const UserController = controllers.UserController;
const notifier = require('node-notifier');

//const jws = require('express-jwt-session');
//const secret = 'mysecret';
//const isAuthent = jws.isAuthenticated(secret);


const userRouter = express.Router();
userRouter.use(bodyParser.json());
//userRouter.use(jwt({ secret: secret});

userRouter.post('/signUp', function(req, res) {
  const login = req.body.login;
  const pw = req.body.password;
  if(login === undefined || pw === undefined ) {
    res.status(400).end();

    notifier.notify({
      'title' : 'Champs incorrecte',
      'message' : 'Veuillez remplir tous les champs svp',
      'sound' : false,
      'wait' : true
    });
    return;
  }
  UserController.newUser(login,pw)
  .then((user) => {
    res.status(201).json(user);
  })
  .catch((err) => {
    res.status(500).end();
  })
});


userRouter.get('/getAll' , function(req,res){
  UserController.getAllUser()
  .then((users) => {
    res.status(201).json(users);
  })
  .catch((err) => {
    console.error(err);
  })
});

  userRouter.post('/auth', function(req,res){
      const login = req.body.login;
      const pw = req.body.password;
      if(login === undefined || pw === undefined ) {
        res.status(400).end();

        notifier.notify({
          'title' : 'Champs incorrecte',
          'message' : 'Veuillez remplir tous les champs svp',
          'sound' : false,
          'wait' : true
        });
        return;
      }

    UserController.authent(login,pw)
    .then((user)=>{
      if(user == null){
        res.status(404).end();
        notifier.notify({
          'title' : "Combinaison incorrecte",
          'message' : 'La combinaisont est incorrecte',
          'sound' : false,
          'wait' : true
        });
        return;
      }
       res.status(200).json(user)
    })
    .catch((err)=>{
      console.error(err);
});
/*
    if(userAuth === undefined ){
      res.status(400).end();

      notifier.notify({
        'title' : 'Echec de connection',
        'message' : "La combinaison login/password n'est pas correcte",
        'sound' : false,
        'wait' : true
      });
      return;
    }

    res.status(200).json(userAuth);
*/
  });

  userRouter.get('/lastUser' , function(req,res){

    UserController.findLast()
    .then((user) =>{
        res.status(200).json(user);
    })
    .catch((err)=>{
      console.error(err);
    });

  } );



module.exports = userRouter;
