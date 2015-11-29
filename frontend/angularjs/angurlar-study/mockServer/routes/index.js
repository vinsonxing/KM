var express = require('express');
var helper = require('./helper.js');
var router = express.Router();


// A browser's default method is 'GET', so this
// is the route that express uses when we visit
// our site initially.
var populateResponse = function(req, res) {
  res.send({
    data: req.data
  });
}
router.get('/kmList', helper.kmList, populateResponse);
router.get('/kmParamList', helper.kmParamList, populateResponse);
router.post('/execute', helper.execute, populateResponse);
router.post('/rate', helper.rate, populateResponse);


module.exports = router;
