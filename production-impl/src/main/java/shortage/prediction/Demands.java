package shortage.prediction;

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
        private long level;
        private LevelOnDeliveryCalculation strategy;

        public DailyDemand(long level, LevelOnDeliveryCalculation strategy)
        {
            this.level = level;
            this.strategy = strategy;
        }

        public long getLevel()
        {
            return level;
        }

        public long calculateLevelOnDelivery(long level, long produced)
        {
            return strategy.calculate(level, getLevel(), produced);
        }
    }
}
