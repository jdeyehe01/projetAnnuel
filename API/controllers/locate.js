const ModelIndex = require('../models');
const Locate = ModelIndex.Locate;
const Conference = ModelIndex.Conference;

const LocateController = function() { };

LocateController.newLocate = function(name,address,cityCode,city) {
  return Locate.create({
    name: name
    address : address,
    cityCode: cityCode,
    city: city
  });
};

LocateController.getAllLocate = function(conferenceId) {

const options = {
include: [{
    model: Locate,
    as: 'locates',
  }],
  where : {
    id: conferenceId
  }
};


return Conference.find(options)
  .then((locates)=>{

    return locates.locates;
  })
};


LocateController.deleteLocate = function(idLocate){
  return Locate.destroy({
    where: {
      id: idLocate
    }
  })
  .then((locate) => {
    return locate;
  })
  .catch((err)=>{
    console.error(err);
  })
}

LocateController.addConference = function(idLocate , idConference){

  return Locate.findById(idLocate)
  .then((locate)=>{
    return Conference.findById(idConference)
    .then((conference) => {
      return locate.addConference(conference);
    })
  })
};


module.exports = LocateController;
