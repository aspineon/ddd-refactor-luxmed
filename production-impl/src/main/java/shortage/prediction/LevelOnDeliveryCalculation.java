package shortage.prediction;

public interface LevelOnDeliveryCalculation {

    LevelOnDeliveryCalculation atDayStart = (level, demand, produced) -> level - demand;
    LevelOnDeliveryCalculation tillEndOfDay = (level, demand, produced) -> level - demand + produced;

    long calculate(long level, long demand, long produced);
}
