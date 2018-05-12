const express = require('express');
const bodyParser = require('body-parser');
const controllers = require('../controllers');
const TaskController = controllers.TaskController;
const ConferenceController = controllers.ConferenceController;
const notifier = require('node-notifier');
const taskRouter = express.Router();

taskRouter.use(bodyParser.json());
taskRouter.use(bodyParser.urlencoded({ extended: true }));

taskRouter.post('/', function(req, res) {
  const title = req.body.title;
  const amount =parseFloat(req.body.amount);
  const duration = req.body.duration;

  if(title === undefined || amount === undefined || duration ===undefined) {

    notifier.notify({
      'title' : 'Champs incorrecte',
      'message' : 'Veuillez remplir tous les champs svp',
      'sound' : false,
      'wait' : true
    });

    res.status(400).end();
    return;

  }

  TaskController.newTask(title,amount,duration)
  .then((task) => {

    notifier.notify({
      'title': 'Information',
      'message': 'Task enregistre',
      'sound': false,
      'wait' : false
      });
  })
  .catch((err) => {
    res.status(500).end();
  })
    res.redirect('https://www.google.fr');


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


  taskRouter.post('/updateTask/:idTask' , function(req,res){

    const title = req.body.title;
    const amount = parseFloat(req.body.amount);
    const duration = req.body.duration;
    const idTask = parseInt(req.params.idTask);

    if( idTask === undefined || idTask <=0 || amount === undefined || amount <=0 || title ===undefined || duration === undefined){
      res.status(403).end();
      return;
    }

    TaskController.updateTask(idTask,title,amount,duration)
    .then((task) => {
      res.status(200).json(task);
    })
    .catch((err)=>{
      console.error(err);
    })

  });

module.exports = taskRouter;
