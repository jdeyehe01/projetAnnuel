const ModelIndex = require('../models');
const Controllers = require('../controllers');
const Conference = ModelIndex.Conference;
const GuestController = Controllers.GuestController;
const Guest = ModelIndex.Guest;
const User = ModelIndex.User;

const ConferenceController = function() { };

ConferenceController.newConference = function(name,date,time,description) {
  return Conference.create({
    name: name,
    date: date,
    time: time,
    description: description
  });
};


ConferenceController.updateConference = function(idConference,name,date,time,description) {
  return Conference.findById(idConference)
  .then((conference)=>{
    return conference.updateAttributes({
      name: name,
      date: date,
      time: time,
      description: description
    });
  });
};


ConferenceController.getAllConference = function() {
  return Conference.findAll()
};


ConferenceController.getConferenceById = function(idConference,idUser){
  return Conference.findOne({
    where:{
      id: idConference,
      user_id: idUser
    }
  })
  .then((conference) =>{
    return conference;
  })
  .catch((err)=>{
    console.error(err);
    return undefined;
  })
};

ConferenceController.getAllConferenceByGuest = function(guestId){

  const options = {
  include: [{
      model: Conference,
      as: 'conferences',
    }],
    where : {
      id: guestId
    }
  };


    return Guest.find(options)
    .then((conferences)=>{

      return conferences.conferences;
    })

  };

  ConferenceController.getAllConferenceByUser = function(idUser){
    return Conference.findAll({
      where:{
        user_id: idUser
      }
    })
    .then((conferences) =>{
      return conferences;
    })
    .catch((err)=>{
      console.error(err);
      return undefined;
    })
  };

  ConferenceController.getAllConference = function(){
    return Conference.findAll()
    .then((conferences)=>{
      return conferences;
    })
    .catch((err)=>{
      console.error(err);
    });
  };





ConferenceController.addGuest = function(idConference,fname,lname,email){

  if(idConference===undefined ||fname===undefined ||lname===undefined ||email===undefined  ){
    return;
  }

return Conference.find({
  id: idConference
})
.then((conference) => {

  Guest.create({
    lname: lname,
    fname: fname,
    email: email
  })
  .then((guest) =>{
    return conference.addGuest(guest);
  })
  .catch((err)=>{
    console.error(err);
  })

})
};



ConferenceController.deleteConference = function(conferenceId){

return Conference.destroy({
  where:{
    id: conferenceId
  }
})
.then((conf) => {
  return conf;
});

}

ConferenceController.findLast = function(){
  return Conference.findOne({
      order: [ [ 'created_at', 'DESC' ]]
  });
}

ConferenceController.addUser = function(idConference ,idUser){

  return Conference.findById(idConference)
  .then((conference)=>{
    return User.findById(idUser)
    .then((user) => {
      return conference.updateAttributes({
        user_id: user.id
      });
    })
  })
  };

module.exports = ConferenceController;
