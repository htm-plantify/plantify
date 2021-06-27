import express from 'express';
import * as PlantController from '../controllers/plant';

const router = express.Router();

router.get('/plants', PlantController.getAllPlants);
router.post('/plants', PlantController.createPlant);
router.get('/plants/:id', PlantController.getPlantById);
router.put('/plants/:id', PlantController.updatePlantById);
router.delete('/plants/:id', PlantController.deletePlantById);

export default router;
