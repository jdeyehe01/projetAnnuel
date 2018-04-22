const express = require('express');
const bodyParser = require('body-parser');
const controllers = require('../controllers');
const UserController = controllers.UserController;

const userRouter = express.Router();
userRouter.use(bodyParser.json());

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

module.exports = userRouter;
