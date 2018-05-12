const ModelIndex = require('../models');
const Task = ModelIndex.Task;
const Conference = ModelIndex.Conference;

const TaskController = function() {};

TaskController.newTask = function(title,amount,duration) {
  return Task.create({
    title: title,
    amount : amount,
    duration: duration
  });
};

TaskController.getAllTaskForConference = function(idConference) {

  return Task.findAll({
    where : {
      conference_id : idConference
    }
  })
};


TaskController.deleteTask = function(idTask){
  return Task.destroy({
    where: {
      id: idTask
    }
  })
  .then((task) => {
    return task;
  })
  .catch((err)=>{
    console.error(err);
  })
}

TaskController.addConference = function(idTask , idConference){

  return Task.findById(idTask)
  .then((task)=>{
    return Conference.findById(idConference)
    .then((conference) => {
      return task.updateAttributes({
        conference_id: idConference
      });
    })
  })
};


TaskController.updateTask = function(idTask,title,amount,duration){
  return  Task.findById(idTask)
    .then((task) => {
        return task.updateAttributes({
        title: title,
        amount: amount,
        duration: duration
      });
    })
    .catch((err) => {
      console.error(err);
    })
}

TaskController.getTaskById = function(idConference,idTask){
  return Task.find({
    where : {
      id: idTask,
      conference_id : idConference
    }
  })
}

module.exports = TaskController;
