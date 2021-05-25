const express = require('express');
const path = require('path');

const app = express();

app.use(express.static('./dist/ipproiect'));

app.get('/*', (req, res) => 
    res.sendFile('./dist/index.html'),
);

app.listen(process.env.PORT || 8080);