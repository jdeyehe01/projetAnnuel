const express = require('express');
const bodyParser = require('body-parser');
const controllers = require('../controllers');
const UserController = controllers.UserController;
const jws = require('express-jwt-session');
const secret = 'mysecret';
const isAuthent = jws.isAuthenticated(secret);


const userRouter = express.Router();
userRouter.use(bodyParser.json());
userRouter.use(jwt({ secret: secret});

userRouter.post('/', function(req, res) {
  const login = req.body.login;
  const pw = req.body.password;
  if(login === undefined || pw === undefined ) {
    res.status(400).end();
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

userRouter.get('/setAuth', function(req,res){
    var token = jws.signToken({login : 'jean',mdp:'toto'}, secret, 150);
    res.json({token: token});

});

userRouter.get('/getAuth',isAuthent, function(req,res){
  res.send(req.user);

});

userRouter.get('/jean',function(req,res){
  console.log('Jean');
})
module.exports = userRouter;
