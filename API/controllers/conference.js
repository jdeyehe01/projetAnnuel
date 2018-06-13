const ModelIndex = require('../models');
const Controllers = require('../controllers');
const Conference = ModelIndex.Conference;
const GuestController = Controllers.GuestController;
const Guest = ModelIndex.Guest;

const ConferenceController = function() { };

ConferenceController.newConference = function(name,date,time,description) {
  return Conference.create({
    name: name,
    date: date,
    time: time,
    description: description
  });
};

ConferenceController.getAllConference = function() {
  return Conference.findAll()
};


ConferenceController.getConferenceById = function(id){
  return Conference.findById(id)
  .then((conference) =>{
    return conference;
  })
  .catch((err)=>{
    console.error(err);
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

module.exports = ConferenceController;
