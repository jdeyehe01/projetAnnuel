const RouteManager = function() { };

RouteManager.attach = function(app) {
  app.use('/user', require('./user'));
  app.use('/conference',require('./conference'));
  app.use('/guest' , require('./guest'));

};

module.exports = RouteManager;
