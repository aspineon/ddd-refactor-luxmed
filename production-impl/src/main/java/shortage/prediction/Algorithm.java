package shortage.prediction;

import entities.ShortageEntity;
import enums.DeliverySchema;
import external.CurrentStock;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Algorithm {
    private CurrentStock stock;
    private List<LocalDate> dates;
    private ProductionOutputs outputs;
    private Demands demandsPerDay;

    public Algorithm(CurrentStock stock, List<LocalDate> dates, ProductionOutputs outputs, Demands demandsPerDay) {
        this.stock = stock;
        this.dates = dates;
        this.outputs = outputs;
        this.demandsPerDay = demandsPerDay;
    }

    public List<ShortageEntity> calculateShortages() {
        long level = stock.getLevel();

        List<ShortageEntity> gap = new LinkedList<>();
        for (LocalDate day : dates)
        {
            Demands.DailyDemand demand = demandsPerDay.get(day);
            if (demand == null)
            {
                level += outputs.getOutput(day);
                continue;
            }
            long produced = outputs.getOutput(day);

            long levelOnDelivery;
            if (demand.getDeliverySchema() == DeliverySchema.atDayStart)
            {
                levelOnDelivery = level - demand.getLevel();
            }
            else if (demand.getDeliverySchema() == DeliverySchema.tillEndOfDay)
            {
                levelOnDelivery = level - demand.getLevel() + produced;
            }
            else if (demand.getDeliverySchema() == DeliverySchema.every3hours)
            {
                // TODO WTF ?? we need to rewrite that app :/
                throw new NotImplementedException();
            }
            else
            {
                // TODO implement other variants
                throw new NotImplementedException();
            }

            if (!(levelOnDelivery >= 0))
            {
                ShortageEntity entity = new ShortageEntity();
                entity.setRefNo(outputs.ProductRefNo);
                entity.setFound(LocalDate.now());
                entity.setMissing(levelOnDelivery * -1L);
                entity.setAtDay(day);
                gap.add(entity);
            }
            long endOfDayLevel = level + produced - demand.getLevel();
            level = endOfDayLevel >= 0 ? endOfDayLevel : 0;
        }
        return gap;
    }
}
