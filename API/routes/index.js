const RouteManager = function() { };

RouteManager.attach = function(app) {
  app.use('/user', require('./user'));
  app.use('/conference',require('./conference'));
  app.use('/guest' , require('./guest'));
  app.use('/locate' , require('./locate'));
  app.use('/budget', require('./budget'));
  app.use('/task' , require('./task'));
  app.use('/presentation' , require('./presentation'));

};

module.exports = RouteManager;
