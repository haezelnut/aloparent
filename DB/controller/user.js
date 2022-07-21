const Validator = require('fastest-validator');
const { user } = require('../models');
const mysql = require('mysql2');
const valid = new Validator();
const express = require('express');
const app = express();
const fs = require('fs');

app.use(express.static(__dirname + '/images/image_user'));

//User Profile Images

let userID;
var connection = mysql.createConnection({
  host: '127.0.0.1',
  user: 'root',
  password: '',
  database: 'aloparent_db',
});

//Get User Data Function
exports.getUserData = async (req, res) => {
  try {
    const email = req.params.email;
    const getData = await connection.promise().query(` 
		SELECT * FROM user WHERE email = '${email}'
		`);

    if (getData.length === 0) {
      return res.status(400).json({ message: 'E-mail Not Found !!!' });
    } else {
      res.json(getData[0][0]);
    }
  } catch (e) {
    console.log(e);
  }
};

exports.getImage = async (req, res) => {
  try {
    const email = req.params.email;
    const getData = await connection.promise().query(` 
		SELECT * FROM user WHERE email = '${email}'
		`);

    if (getData.length === 0) {
      return res.status(400).json({ message: 'E-mail Not Found !!!' });
    } else {
      const dbRes = getData[0][0];
      res.download('controller/images/image_user/' + dbRes.user_Image);
    }
  } catch (e) {
    console.log(e);
  }
};

//Register Function
exports.addUser = async (req, res) => {
  const schema = {
    id_user: 'number',
    username: 'string',
    email: 'string',
    password: 'string',
	user_Image: 'string'
  };

  const { id_user, username, email, password, user_Image } = req.body;

  const validate = valid.validate(req.body, schema);

  if (validate.length) {
    return res.status(400).json(validate);
  }

  try {
    const [checkUser] = await connection.promise().query(`
        SELECT * FROM user WHERE email = '${email}'
    `);
    if (checkUser.length === 0) {
      await user.create(req.body);
      console.log('Success Create Account');
      return res.status(200).json({ message: 'SUCCESS' });
    } else {
      console.log('Unsuccessfull Create Account');
      return res.status(400).json({ message: 'EXIST' });
    }
  } catch (e) {
    console.log(e);
  }
};

//Login Function
exports.login = async (req, res) => {
  const { email, password } = req.body;

  if (email === '' && password === '') return res.status(400).send('USER NOT FOUND!!!');

  const [checkUser] = await connection.promise().query(`
      SELECT * FROM user WHERE email = '${email}'
  `);

  if (checkUser.length === 0) return res.status(400).send('EMAIL FALSE');

  if (checkUser[0].password !== password) return res.status(200).send('PASSWORD FALSE');

  return res.status(200).send('TRUE');
};

//Reset password
exports.checkEmail = async (req, res) => {
  const { username, email } = req.body;

  try {
    const [checkUserEmail] = await connection.promise().query(` 
    SELECT * FROM user WHERE email = '${email}'
    `);
    if (checkUserEmail.length === 0) return res.status(400).json({ message: 'EMAIL FALSE' });

    const [checkUsername] = await connection.promise().query(`
    SELECT * FROM user WHERE username = '${username}'
    `);
    if (checkUsername.length === 0) return res.status(400).json({ message: 'USERNAME FALSE' });

    return res.status(200).json({ message: 'TRUE' });
  } catch (e) {
    return res.status(400).send(e);
  }
};

exports.updatePassword = async (req, res) => {
  try {
    const { email, password } = req.body;

    userID = await connection.promise().query(`
    UPDATE user
    SET password='${password}'
    WHERE email='${email}'
    `);

    return res.status(200).json({ message: 'TRUE' });
  } catch (e) {
    return res.status(400).send(e);
  }
};

//Function Update User [with PUT]
exports.replaceData = async (req, res) => {
  try {
    console.log(req.body);
    console.log(req.file.filename);
    var imgsrc = 'http://127.0.0.1:3000/controller/images/image_user/' + req.file.fieldname;
    const { username, email, password } = req.body;
    const [checkUserEmail] = await connection.promise().query(` 
		SELECT * FROM user WHERE email = '${email}'
		`);

    userID = await connection.promise().query(`
		UPDATE user
		SET username='${username}', email='${email}',password='${password}', user_Image='${req.file.filename}'
		WHERE email='${email}'
		`);

    return res.status(200).send('TRUE');
  } catch (e) {
    return res.status(400).send(e);
  }
};
