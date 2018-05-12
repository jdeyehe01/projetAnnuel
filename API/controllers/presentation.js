const ModelIndex = require('../models');
const Presentation = ModelIndex.Presentation;
const Conference = ModelIndex.Conference;

const PresentationController = function() {};

PresentationController.newPresentation = function(title,amount,description) {
  return Presentation.create({
    title: title,
    amount : amount,
    description: description
  });
};

PresentationController.getAllPresentationForConference = function(idConference) {

  return Presentation.findAll({
    where : {
      conference_id : idConference
    }
  })
};

PresentationController.deletePresentation = function(idPresentation){
  return Presentation.destroy({
    where: {
      id: idPresentation
    }
  })
  .then((presentation) => {
    return presentation;
  })
  .catch((err)=>{
    console.error(err);
  })
}

PresentationController.addConference = function(idPresentation , idConference){

  return Presentation.findById(idPresentation)
  .then((presentation)=>{
    return Conference.findById(idConference)
    .then((conference) => {
      return presentation.updateAttributes({
        conference_id: idConference
      });
    })
  })
};


PresentationController.updatePresentation = function(idPresentation,title,amount,description){
  return  Presentation.findById(idPresentation)
    .then((presentation) => {
        return presentation.updateAttributes({
        title: title,
        amount: amount,
        description: description
      });
    })
    .catch((err) => {
      console.error(err);
    })
}

PresentationController.getPresentationById = function(idConference,idPresentation){
  return Presentation.find({
    where : {
      id: idPresentation,
      conference_id : idConference
    }
  })
}

module.exports = PresentationController;
