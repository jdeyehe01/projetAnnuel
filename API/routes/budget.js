const express = require('express');
const bodyParser = require('body-parser');
const controllers = require('../controllers');
const BudgetController = controllers.BudgetController;
const ConferenceController = controllers.ConferenceController;
const notifier = require('node-notifier');
const budgetRouter = express.Router();

budgetRouter.use(bodyParser.json());
budgetRouter.use(bodyParser.urlencoded({ extended: true }));

budgetRouter.post('/', function(req, res) {
  const title = req.body.title;
  const amount = parseFloat(req.body.amount);

  if(title === undefined || amount === undefined) {

    notifier.notify({
      'title' : 'Champs incorrecte',
      'message' : 'Veuillez remplir tous les champs svp',
      'sound' : false,
      'wait' : true
    });

    res.status(400).end();

  }

  BudgetController.newBudget(title,amount)
  .then((budget) => {

    notifier.notify({
      'title': 'Information',
      'message': 'Budget enregistre',
      'sound': false,
      'wait' : false
      });

      res.status(200).end();
  })
  .catch((err) => {
    res.status(500).end();
  })


});


  budgetRouter.delete('/deleteBudgetById/:idBudget' , function(req,res){

    const idBudget = parseInt(req.params.idBudget);
    if(idBudget === undefined || idBudget <= 0 ){
      res.status(500).end();
      return;
    }

    BudgetController.deleteBudget(idBudget)
    .then(()=>{

      notifier.notify({
        'title': 'Information',
        'message': 'Budget supprimé',
        'sound': false,
        'wait' : false
        });

      res.status(200).end();
    })
    .catch((err)=>{
      console.error(err);
    })
  });

  budgetRouter.post('/addConference/:idBudget/:idConference',function(req,res){
    const idBudget = parseInt(req.params.idBudget);
    const idConference = parseInt(req.params.idConference);

    if(idBudget === undefined || idBudget <=0 || idConference === undefined || idConference <=0){
      res.status(401).end();
      return;
    }

    BudgetController.addConference(idBudget , idConference);
    res.status(200).end();
  });


  budgetRouter.get('/getBudgetById/:idBudget/:idConference' , function(req,res){
    const idBudget = parseInt(req.params.idBudget);
    const idConference = parseInt(req.params.idConference);

    if(idBudget === undefined || idBudget <=0 || idConference === undefined || idConference <=0){
      res.status(401).end();
      return;
    }

    BudgetController.getBudgetById(idConference,idBudget)
    .then((budget) => {
      res.status(200).json(budget);
    })
    .catch((err)=>{
      console.error(err);
    })

  });

  budgetRouter.get('/getAllBudgetForConference/:idConference', function(req,res) {
    const idConference = parseInt(req.params.idConference);

    if( idConference === undefined || idConference <=0){
      res.status(401).end();
      return;
    }
    BudgetController.getAllBudgetForConference(idConference)
    .then((budgets) => {
      res.status(200).json(budgets);
    })
    .catch((err) => {
      console.error(err);
    })
  });


  budgetRouter.put('/updateBudget/:idBudget' , function(req,res){

    const title = req.body.title;
    const amount = parseFloat(req.body.amount);
    const idBudget = parseInt(req.params.idBudget);

    if( idBudget === undefined || idBudget <=0 || amount === undefined || amount <=0 || title ===undefined){
      res.status(403).end();
      return;
    }

    BudgetController.updateBudget(idBudget,title,amount)
    .then((budget) => {
      res.status(200).json(budget);
    })
    .catch((err)=>{
      console.error(err);
    })

  });

module.exports = budgetRouter;
