const ModelIndex = require('../models');
const User = ModelIndex.User;

const UserController = function() { };

UserController.newUser = function(login,password) {
  return User.create({
    login: login,
    password: password
  });
};

UserController.getAllUser = function() {
  return User.findAll();
};

module.exports = UserController
