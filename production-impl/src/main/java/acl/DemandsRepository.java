package acl;

import shortage.prediction.Demands;

import java.util.stream.Collectors;

class DemandsRepository
{
    private CurrentDemandQuery demands;

    public Demands createDemandsPerDay()
    {
        return new Demands(demands.get().collect(Collectors.toMap(
                demand -> demand.Day,
                demand -> new Demands.DailyDemand(
                        demand.DeliverySchema,
                        demand.Level
                )
        )));
    }

}
