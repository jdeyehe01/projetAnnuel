const ModelIndex = require('../models');
const Guest = ModelIndex.Guest;
const Conference = ModelIndex.Conference;
const nodemailer = require('nodemailer');

const GuestController = function() { };

GuestController.newGuest = function(lname,fname,email) {
  return Guest.create({
    lname: lname,
    fname: fname,
    email: email
  });
};

GuestController.findById = function(idGuest){
  return Guest.findById(idGuest)
};

GuestController.updateGuest = function(idGuest,lname,fname,email) {

 return Guest.findById(idGuest)
 .then((guest)=>{
  return guest.updateAttributes({
     lname: lname,
     fname: fname,
     email: email
   })
 })
 .catch((err)=>{
   console.error(err);
 });

}

GuestController.getAllGuest = function(conferenceId) {

const options = {
include: [{
    model: Guest,
    as: 'guests',
  }],
  where : {
    id: conferenceId
  }
};


return Conference.find(options)
  .then((guests)=>{

    return guests.guests;
  })
};


GuestController.deleteGuest = function(idGuest){
  return Guest.destroy({
    where: {
      id: idGuest
    }
  })
  .then((guest) => {
    return guest;
  })
  .catch((err)=>{
    console.error(err);
  })
}

GuestController.addConference = function(idGuest , idConference){

  return Guest.findById(idGuest)
  .then((guest)=>{
    return Conference.findById(idConference)
    .then((conference) => {
      return guest.addConference(conference);
    })
  })
};

GuestController.respond = function(idGuest,res){
  Guest.findById(idGuest)
  .then((guest)=>{
    guest.updateAttributes({
      isPresent:res
    });
  });

};


GuestController.findLast = function(idConference){
return Guest.findOne({
  include: [{
        model: Conference,
        as: 'conferences',
        where:{
          id: idConference
        }
    }],
    order: [ [ 'created_at', 'DESC' ]]
});

};


GuestController.sendMail = function(idGuest,idConference){

  Guest.findById(idGuest)
  .then((guest)=>{
    Conference.findById(idConference)
    .then((conference)=>{

      const transporter = nodemailer.createTransport({
              service: 'Gmail',
              auth: {
                  user: 'no.reply.please.project@gmail.com',
                  pass: 'dupondToto12'
              }
      });

      var msg = "Bonjour " + guest.fname +" "+ guest.lname+", \n"+ "Vous avez été invité à la conférence "
      +conference.name + " qui au lieu le " + conference.date +" à "+ conference.time+

       " <ul> <li> <a href='http://sebastiendelbeportfolio.alwaysdata.net/guest/responsGuest/"+guest.id+"/1'> Je serai présent </a> </li>  <li><a href='http://sebastiendelbeportfolio.alwaysdata.net/guest/responsGuest/"+guest.id+"/0'> Je ne serai pas présent </a> </ul>";


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

    })
    .catch((err)=>{
      console.error(err);
    })

  })
  .catch((err)=>{
    console.error(err);
  });



}

module.exports = GuestController;
