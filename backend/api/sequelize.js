import { Sequelize } from 'sequelize-cockroachdb';
import * as fs from 'fs';

const sequelize = new Sequelize({
  dialect: 'postgres',
  username: process.env.DB_USERNAME,
  password: process.env.DB_PASSWORD,
  host: process.env.DB_HOST,
  port: process.env.DB_PORT,
  database: process.env.DB_DATABASE,
  dialectOptions: {
    ssl: {
      rejectUnauthorized: false,
      ca: fs.readFileSync(process.env.DB_CERT).toString(),
    },
  },
  logging: false,
});

export default sequelize;
