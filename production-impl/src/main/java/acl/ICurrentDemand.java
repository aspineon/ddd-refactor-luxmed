package acl;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface ICurrentDemand {

    Stream<CurrentDemandReadModel> get();

    class CurrentDemandReadModel
    {
        LocalDate Day;
        enums.DeliverySchema DeliverySchema;
        long Level;

        public CurrentDemandReadModel(LocalDate day, enums.DeliverySchema deliverySchema, long level)
        {
            Day = day;
            DeliverySchema = deliverySchema;
            Level = level;
        }
    }
}
