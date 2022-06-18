require('dotenv').config();

const { db_username, db_password, db_hostname, db_name, db_dialect } = process.env;

module.exports = {
  development: {
    username: db_username,
    password: db_password,
    database: db_name,
    host: db_hostname,
    dialect: db_dialect,
  },
  test: {
    username: db_username,
    password: db_password,
    database: db_name,
    host: db_hostname,
    dialect: db_dialect,
  },
  production: {
    username: db_username,
    password: db_password,
    database: db_name,
    host: db_hostname,
    dialect: db_dialect,
  },
};
