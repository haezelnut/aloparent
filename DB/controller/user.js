const Validator = require('fastest-validator');
const { user } = require('../models');
const mysql = require('mysql2');
const valid = new Validator();

let userID;
var connection = mysql.createConnection({
  host: '127.0.0.1',
  user: 'root',
  password: '',
  database: 'aloparent_db',
});

//Function Tambah User
exports.addUser = async (req, res) => {
  const schema = {
    id_user: 'number',
    username: 'string',
    email: 'string',
    password: 'string',
  };

  const { id_user, username, email, password } = req.body;

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

//Function Update User [with PUT]
exports.replaceData = async (req, res) => {
  const id = req.params.id;
  userID = await user.findByPk(id);

  if (!userID) {
    return res.json({ message: 'User Not Found!' });
  }

  const schema = {
    username: 'string|optional',
    email: 'string|optional',
    password: 'string|optional',
  };

  const validate = valid.validate(req.body, schema);

  if (validate.length) {
    return res.status(400).json(validate);
  }

  userID = await userID.update(req.body);
  res.json(userID);
};

//Function Show All User
exports.showUser = async (req, res) => {
  userID = await user.findAll();
  return res.json(userID);
};

//Function Show User by ID
exports.showUserByID = async (req, res) => {
  const id = req.params.id;
  const userID = await user.findByPk(id);
  return res.json(userID || {});
};

exports.deleteUser = async (req, res) => {
  const id = req.params.id;
  userID = await user.findByPk(id);

  if (!userID) {
    return res.json({ message: 'User Not Found!' });
  }

  await userID.destroy();

  res.json({ message: 'User was Deleted!' });
};

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
