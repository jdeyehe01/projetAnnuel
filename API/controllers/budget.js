const ModelIndex = require('../models');
const Budget = ModelIndex.Budget;
const Conference = ModelIndex.Conference;

const BudgetController = function() {};

BudgetController.newBudget = function(title,amount) {
  return Budget.create({
    title: title,
    amount : amount
  });
};

BudgetController.getAllBudgetForConference = function(idConference) {

  return Budget.findAll({
    where : {
      conference_id : idConference
    }
  })
};


BudgetController.deleteBudget = function(idBudget){
  return Budget.destroy({
    where: {
      id: idBudget
    }
  })
  .then((budget) => {
    return budget;
  })
  .catch((err)=>{
    console.error(err);
  })
}

BudgetController.addConference = function(idBudget , idConference){

  return Budget.findById(idBudget)
  .then((budget)=>{
    return Conference.findById(idConference)
    .then((conference) => {
      return budget.updateAttributes({
        conference_id: idConference
      });
    })
  })
};


BudgetController.updateBudget = function(idBudget,title,amount){
  return  Budget.findById(idBudget)
    .then((budget) => {
        return budget.updateAttributes({
        title: title,
        amount: amount
      });
    })
    .catch((err) => {
      console.error(err);
    })
}

BudgetController.getBudgetById = function(idConference,idBudget){
  return Budget.find({
    where : {
      id: idBudget,
      conference_id : idConference
    }
  })
}

module.exports = BudgetController;
