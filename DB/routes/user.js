var express = require('express');
var multer = require('../controller/multer');
var router = express.Router();
const userFunction = require('../controller/user');

router.post('/', userFunction.addUser);

router.get('/:email', userFunction.getUserData);

router.put('/updateProfile', multer, userFunction.replaceData);

router.post('/login', userFunction.login);

router.post('/check', userFunction.checkEmail);

router.post('/reset', userFunction.updatePassword);

router.get('/userImage/:email', userFunction.getImage);

module.exports = router;
