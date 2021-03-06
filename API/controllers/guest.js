const ModelIndex = require('../models');
const Guest = ModelIndex.Guest;
const Conference = ModelIndex.Conference;

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
/*  .then((guest)=>{
    return guest;
  })
  .catch((err)=>{
    return undefined;
    console.error(err);
  })*/
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



module.exports = GuestController;
