var express = require('express');
var fs = require('fs');
var dataDir = './mockServer/data';


var readJson = function(fName, callback) {
  var file = dataDir + '/' + fName;
  try {
    var jData = fs.readFileSync(file, 'UTF-8');
    console.log(jData);
    callback(JSON.parse(jData));
  } catch (err) {
    console.error('Unable to load file: ' + file);
  }

};

module.exports.kmList = function(req, res, next) {
  readJson('kmList.json', function(d) {
    req.data = d;
    next();
  })
};

module.exports.kmParamList = function(req, res, next) {
  readJson('kmParamList.json', function(d) {
    req.data = d;
    next();
  })
};

module.exports.execute = function(req, res, next) {
  readJson('executeResult.json', function(d) {
    req.data = d;
    next();
  })
};

module.exports.rate = function(req, res, next) {
  console.log(req.body);
  readJson('rateResponse.json', function(d) {
    req.data = d;
    next();
  })
};

module.exports.readJson = readJson;
