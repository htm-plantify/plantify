import express from 'express';
import cors from 'cors';
import pino from 'express-pino-logger';
import plant from './routes/plant';
import sequelize from './sequelize';

const pinoExpress = pino();

const app = express();

const PORT = 8000;

if (process.env.NODE_ENV === 'development') {
  pinoExpress.logger.level = 'trace';
} else if (process.env.NODE_ENV === 'production') {
  pinoExpress.logger.level = 'error';
}

app.use(pinoExpress);
app.use(express.json());
app.use(cors());

app.use('/api', plant);

sequelize.sync().then(() => {
  app.listen(PORT, () => {
    pinoExpress.logger.info(`Server is running at http://localhost:${PORT}`);
  });
});
