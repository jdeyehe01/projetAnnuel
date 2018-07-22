const express = require('express');
const bodyParser = require('body-parser');
const controllers = require('../controllers');
const TaskController = controllers.TaskController;
const ConferenceController = controllers.ConferenceController;
const notifier = require('node-notifier');
const taskRouter = express.Router();
const path = require("path");

taskRouter.use(bodyParser.json());
taskRouter.use(bodyParser.urlencoded({ extended: true }));
taskRouter.use(express.static(path.join(__dirname + '../../../style')));

taskRouter.post('/', function(req, res) {
  const title = req.body.title;
  const duration = req.body.duration;

  if(title === undefined || duration ===undefined) {

    notifier.notify({
      'title' : 'Champs incorrecte',
      'message' : 'Veuillez remplir tous les champs svp',
      'sound' : false,
      'wait' : true
    });

    res.status(400).end();
    return;

  }

  TaskController.newTask(title,duration)
  .then((task) => {

    notifier.notify({
      'title': 'Information',
      'message': 'Task enregistre',
      'sound': false,
      'wait' : false
      });

      ConferenceController.findLast()
      .then((conference) => {
        TaskController.addConference(task.id , conference.id);
      });
  })
  .catch((err) => {
    res.status(500).end();
  })


});

taskRouter.post('/:idConference', function(req, res) {
  const title = req.body.title;
  const duration = req.body.duration;
  const idConf = req.params.idConference

  if(title === undefined || idConf === undefined || duration ===undefined) {
    res.status(400).end();
    return;
  }

  TaskController.newTask(title,duration)
  .then((task) => {

    ConferenceController.getOneConference(idConf)
    .then((conference) => {
        TaskController.addConference(task.id , conference.id);
      });
  })
  .catch((err) => {
    res.status(500).end();
  })


});




  taskRouter.delete('/deleteTaskById/:idTask' , function(req,res){

    const idTask = parseInt(req.params.idTask);
    if(idTask === undefined || idTask <= 0 ){
      res.status(500).end();
      return;
    }

    TaskController.deleteTask(idTask)
    .then(()=>{

      notifier.notify({
        'title': 'Information',
        'message': 'Tâche supprimé',
        'sound': false,
        'wait' : false
        });

      res.status(200).end();
    })
    .catch((err)=>{
      console.error(err);
    })
  });

  taskRouter.post('/addConference/:idTask/:idConference',function(req,res){
    const idTask = parseInt(req.params.idTask);
    const idConference = parseInt(req.params.idConference);

    if(idTask === undefined || idTask <=0 || idConference === undefined || idConference <=0){
      res.status(401).end();
      return;
    }

    TaskController.addConference(idTask , idConference);
    res.status(200).end();
  });


  taskRouter.get('/getTaskById/:idTask/:idConference' , function(req,res){
    const idTask = parseInt(req.params.idTask);
    const idConference = parseInt(req.params.idConference);

    if(idTask === undefined || idTask <=0 || idConference === undefined || idConference <=0){
      res.status(401).end();
      return;
    }

    TaskController.getTaskById(idConference,idTask)
    .then((task) => {
      res.status(200).json(task);
    })
    .catch((err)=>{
      console.error(err);
    })

  });

  taskRouter.get('/getAllTaskForConference/:idConference', function(req,res) {
    const idConference = parseInt(req.params.idConference);

    if( idConference === undefined || idConference <=0){
      res.status(401).end();
      return;
    }
    TaskController.getAllTaskForConference(idConference)
    .then((tasks) => {
      res.status(200).json(tasks);
    })
    .catch((err) => {
      console.error(err);
    })
  });


  taskRouter.put('/updateTask/:idTask' , function(req,res){

    const title = req.body.title;
    const duration = req.body.duration;
    const idTask = parseInt(req.params.idTask);

    if( idTask === undefined || idTask <=0 || title ===undefined || duration === undefined){
      res.status(403).end();
      return;
    }

    TaskController.updateTask(idTask,title,duration)
    .then((task) => {
      res.status(200).json(task);
    })
    .catch((err)=>{
      console.error(err);
    })

  });

module.exports = taskRouter;
