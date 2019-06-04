package shortage.prediction;

import entities.DemandEntity;
import enums.DeliverySchema;
import tools.Util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class Demands
{
    private final HashMap<LocalDate, DemandEntity> demandsPerDay;

    public Demands(List<DemandEntity> demands)
    {
        demandsPerDay = new HashMap<>();
        for (DemandEntity demand1 : demands)

        {
            demandsPerDay.put(demand1.getDay(), demand1);
        }

    }

    public DailyDemand get(LocalDate day)
    {
        if (demandsPerDay.containsKey(day))
        {
            return new DailyDemand(demandsPerDay.get(day));
        }
        else
        {
            return null;
        }
    }

    public static class DailyDemand
    {
        private DemandEntity demand;

        public DailyDemand(DemandEntity demand)
        {
            this.demand = demand;
        }

        public DeliverySchema getDeliverySchema()
        {
            return Util.getDeliverySchema(demand);
        }

        public long getLevel()
        {
            return Util.getLevel(demand);
        }
    }
}
