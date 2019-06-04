package acl;

import entities.DemandEntity;
import entities.ProductionEntity;
import entities.ShortageEntity;
import external.CurrentStock;
import shortage.prediction.*;

import java.time.LocalDate;
import java.util.List;

public class ShortageFinderACL
{

    public static List<ShortageEntity> findShortages(LocalDate today, int daysAhead, CurrentStock stock,
                                                     List<ProductionEntity> productions, List<DemandEntity> demands)
    {
        AlgorithmFactory factory = new AlgorithmFactory(today, daysAhead, stock, productions, demands);
        ShortagePredictionService service = new ShortagePredictionService(factory);

        List<ShortageEntity> shortages = service.findShortages();

        return shortages;
    }

    private ShortageFinderACL()
    {
    }

}