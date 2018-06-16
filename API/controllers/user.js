const ModelIndex = require('../models');
const User = ModelIndex.User;
const Conference = ModelIndex.Conference;

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

UserController.authent = function(login,pw) {
  return User.find({
    where:{
      login: login,
      password: pw
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


module.exports = UserController
