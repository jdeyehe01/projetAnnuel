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

userRouter.post('/', function(req, res) {
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

userRouter.post('/login', function(req, res){
  const email = req.body.email;
  const password = req.body.password;

  const user = UserController.login(email, password)
  .then((user) => {
    if(user == null){
      res.send('Accès refusé').end();
      return;
    }

    jwt.sign({user}, 'secretkey', {expiresIn: '1h'}, (err, token) =>{
      res.json({
        token
      });
    });
  })
  .catch((err) => {
    console.error(err);
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

userRouter.get('/setAuth', function(req,res){
  //  var token = jws.signToken({login : 'jean',mdp:'toto'}, secret, 150);
  //  res.json({token: token});

});
/*
userRouter.get('/getAuth',isAuthent, function(req,res){
  res.send(req.user);

});
*/
userRouter.get('/jean',function(req,res){
  console.log('Jean');
})
module.exports = userRouter;
