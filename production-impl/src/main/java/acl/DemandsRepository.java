package acl;

import enums.DeliverySchema;
import shortage.prediction.Demands;
import shortage.prediction.LevelOnDeliveryCalculation;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.stream.Collectors;

class DemandsRepository
{
    private ICurrentDemand demands;
    private static HashMap<DeliverySchema, LevelOnDeliveryCalculation> map = init();

    public Demands createDemandsPerDay()
    {
        return new Demands(demands.get().collect(Collectors.toMap(
                demand -> demand.Day,
                demand -> new Demands.DailyDemand(
                        demand.Level,
                        strategy(demand.DeliverySchema)
                )
        )));
    }

    private LevelOnDeliveryCalculation strategy(DeliverySchema deliverySchema)
    {
        return map.getOrDefault(deliverySchema, (level, demand, produced) -> {
            throw new NotImplementedException();
        });
    }

    static private HashMap<DeliverySchema, LevelOnDeliveryCalculation> init() {
        HashMap<DeliverySchema, LevelOnDeliveryCalculation> map = new HashMap<>();
        map.put(DeliverySchema.atDayStart, LevelOnDeliveryCalculation.atDayStart);
        map.put(DeliverySchema.tillEndOfDay, LevelOnDeliveryCalculation.tillEndOfDay);
        return map;
    }
}
