package acl;

import entities.DemandEntity;
import shortage.prediction.Demands;
import tools.Util;

import java.util.List;
import java.util.stream.Collectors;

class DemandsACLRepository
{
    private List<DemandEntity> demands;

    public Demands createDemandsPerDay()
    {
        return new Demands(demands.stream().collect(Collectors.toMap(
                DemandEntity::getDay,
                demand -> new Demands.DailyDemand(
                        Util.getDeliverySchema(demand),
                        Util.getLevel(demand)
                )
        )));
    }
}
