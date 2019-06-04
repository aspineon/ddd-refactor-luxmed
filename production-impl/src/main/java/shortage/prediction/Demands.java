package shortage.prediction;

import enums.DeliverySchema;

import java.time.LocalDate;
import java.util.Map;

public class Demands
{
    private final Map<LocalDate, DailyDemand> demandsPerDay;

    public Demands(Map<LocalDate, DailyDemand> demandsPerDay)
    {
        this.demandsPerDay = demandsPerDay;
    }

    public DailyDemand get(LocalDate day)
    {
        return demandsPerDay.getOrDefault(day, null);
    }

    public static class DailyDemand
    {
        private DeliverySchema deliverySchema;
        private long level;

        public DailyDemand(DeliverySchema deliverySchema, long level)
        {
            this.deliverySchema = deliverySchema;
            this.level = level;
        }

        public DeliverySchema getDeliverySchema()
        {
            return deliverySchema;
        }

        public long getLevel()
        {
            return level;
        }
    }
}
