const ModelIndex = require('../models');
const Budget = ModelIndex.Budget;
const Conference = ModelIndex.Conference;

const BudgetController = function() {};

BudgetController.newBudget = function(title,amount) {
  return Budget.create({
    title: name,
    amount : address
  });
};

BudgetController.getBudgetForConference = function(conferenceId) {

const options = {
include: [{
    model: Budget,
    as: 'budgets',
  }],
  where : {
    id: conferenceId
  }
};


return Conference.find(options)
  .then((budgets)=>{

    return budgets.budgets;
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


module.exports = BudgetController;
