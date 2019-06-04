package shortage.prediction;

import entities.ShortageEntity;

import java.util.List;

public class ShortagePredictionService
{
    private IAlgorithmFactory factory;

    public ShortagePredictionService(IAlgorithmFactory factory)
    {
        this.factory = factory;
    }

    public List<ShortageEntity> findShortages()
    {
        Algorithm algorithm = factory.create();

        return algorithm.calculateShortages();
    }
}
