import seqcrdb from 'sequelize-cockroachdb';
import sequelize from '../sequelize';

const { DataTypes } = seqcrdb;

const Plant = sequelize.define('plant', {
  id: {
    type: DataTypes.INTEGER,
    autoIncrement: true,
    primaryKey: true,
  },
  commonName: {
    type: DataTypes.STRING,
  },
  scientificName: {
    type: DataTypes.STRING,
  },
  description: {
    type: DataTypes.STRING,
  },
  price: {
    type: DataTypes.INTEGER,
  },
  weight: {
    type: DataTypes.INTEGER,
  },
  height: {
    type: DataTypes.INTEGER,
  },
  model: {
    type: DataTypes.STRING,
  },
});

export default Plant;
