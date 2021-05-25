const express = require('express');
const app = express();
const path = require('path');

app.use(express.static(__dirname + '/dist/ipproiect'));
app.listen(process.env.PORT || 8080);

app.get('/*', function(req,res){
  const fullPath = path.join(__dirname + '/dist/ipproiect/index.html');
  res.sendFile(fullPath);
})
