const RouteManager = function() { };

RouteManager.attach = function(app) {
  app.use('/user', require('./user'));
  app.use('/conference',require('./conference'));
  app.use('/guest' , require('./guest'));
  app.use('/locate' , require('./locate'));
  app.use('/budget', require('./budget'));

};

module.exports = RouteManager;
