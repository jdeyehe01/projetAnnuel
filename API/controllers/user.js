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

UserController.Confirmed = function(idUser){
  User.findById(idUser)
  .then((user)=>{
    user.updateAttributes({
      emailConfirmed: 1
    });
  })
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

UserController.decrypt = function(text){
  var decipher = crypto.createDecipher(algorithm,password)
  var dec = decipher.update(text,'hex','utf8')
  dec += decipher.final('utf8');
  return dec;
}

UserController.verifyEmail = function(idUser){

  User.findById(idUser)
  .then((user)=>{

      const transporter = nodemailer.createTransport({
              service: 'Gmail',
              auth: {
                  user: 'no.reply.please.project@gmail.com',
                  pass: 'dupondToto12'
              }
      });

      var msg = "Bonjour veuillez confirmer votre adresse email en cliquant "

       " <ul> <li> <a href='http://sebastiendelbeportfolio.alwaysdata.net/user/confirmed/"+user.id+"> ici </a> </li> ";


      var html = "<html> <body> <p>  "+msg+" </p> </body> </html>"



      var mailOptions = {
      from : 'no.reply.please.project@gmail.com',
      to: user.email,
      subject: "Suite de votre inscription",
      html : html
      };


        transporter.sendMail(mailOptions, (error, info) => {
            if (error) {
                return console.log(error);
            }
          console.log('Message envoyé: '+info.response);
        });

      transporter.close();

    })
    .catch((err)=>{
      console.error(err);
    })


}

module.exports = UserController;
