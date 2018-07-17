const ModelIndex = require('../models');
const User = ModelIndex.User;
const Conference = ModelIndex.Conference;
const crypto = require('crypto'), algorithm = 'aes-256-ctr', password = 'd6F3Efeq';

const UserController = function() { };

UserController.newUser = function(email,password) {
  return User.create({
    email: email,
    password: UserController.encrypt(password)
  });
};

UserController.getAllUser = function() {
  return User.findAll();
};

UserController.authent = function(email,pw) {
  return User.find({
    where:{
      email: email,
      password: UserController.encrypt(pw)
    }
  })
  .then((user)=>{
    return user;
  })
  .catch((err)=>{
    console.error(err);
    return undefined;
  })

}


UserController.getAllConference = function(idUser){
  return Conference.findAll({
    where:{
      user_id: idUser
    }
  })
  .then((conferences)=>{
    return conferences;
  })
  .catch((err)=>{
    console.error(err);
    return undefined;
  });
}


UserController.findLast = function(){
  return User.findOne({
      order: [ [ 'created_at', 'DESC' ]]
  });
}

UserController.encrypt = function(text){
  var cipher = crypto.createCipher(algorithm,password)
  var crypted = cipher.update(text,'utf8','hex')
  crypted += cipher.final('hex');
  return crypted;
}

/*
UserController.decrypt = function(text){
  var decipher = crypto.createDecipher(algorithm,password)
  var dec = decipher.update(text,'hex','utf8')
  dec += decipher.final('utf8');
  return dec;
}*/

module.exports = UserController;
