import Plant from '../models/Plant';

export const getAllPlants = async (req, res) => {
  const plants = await Plant.findAll();

  req.log.debug('Fetched all plants');

  res.status(200).json(plants);
};

export const getPlantById = async (req, res) => {
  const queryId = req.params.id;

  const plant = await Plant.findByPk(queryId);

  req.log.debug(`Fetched plant: ${JSON.stringify(plant)}`);

  if (plant === null) res.json({});

  res.status(200).json(plant);
};

export const createPlant = async (req, res) => {
  const queryPlant = req.body;

  const plant = await Plant.create({
    commonName: queryPlant.commonName,
    scientificName: queryPlant.scientificName,
    description: queryPlant.description,
    price: queryPlant.price,
    weight: queryPlant.weight,
    height: queryPlant.height,
    model: queryPlant.model,
  });

  req.log.debug(`Added plant: ${JSON.stringify(plant)}`);

  res.status(201).json(plant);
};

export const updatePlantById = async (req, res) => {
  const queryId = req.params.id;

  const updatePayload = req.body;
  delete updatePayload.id;

  const result = await Plant.update(updatePayload, {
    where: {
      id: queryId,
    },
  });

  req.log.debug(`Updated plant: id: ${queryId} | updated: ${result[0]}`);

  res.status(202).json({ updated: result[0] });
};

export const deletePlantById = async (req, res) => {
  const queryId = req.params.id;

  const number = await Plant.destroy({
    where: {
      id: queryId,
    },
  });

  req.log.debug(`Deleted plants: ${number}`);

  res.status(202).json({ deleted: number });
};
