const express = require('express');
const ModelIndex = require('./models');
const RouteManager = require('./routes');

ModelIndex
.openDatabase()
.then(_startServer)
.catch((err) => {
  console.error(err);
});

// INTERNAL

function _startServer() {

  const app = express();

  RouteManager.attach(app);


  app.listen(process.env.ALWAYSDATA_HTTPD_PORT,process.env.ALWAYSDATA_HTTPD_IP, function(){
    console.log('Server started.. ');
  });

}
