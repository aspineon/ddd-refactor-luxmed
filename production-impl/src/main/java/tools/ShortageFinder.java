package tools;

import entities.DemandEntity;
import entities.ProductionEntity;
import entities.ShortageEntity;
import external.CurrentStock;
import shortage.prediction.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ShortageFinder
{

    /**
     * Production at day of expected delivery is quite complex:
     * We are able to produce and deliver just in time at same day
     * but depending on delivery time or scheme of multiple deliveries,
     * we need to plan properly to have right amount of parts ready before delivery time.
     * <p/>
     * Typical schemas are:
     * <li>Delivery at prod day start</li>
     * <li>Delivery till prod day end</li>
     * <li>Delivery during specified shift</li>
     * <li>Multiple deliveries at specified times</li>
     * Schema changes the way how we calculate shortages.
     * Pick of schema depends on customer demand on daily basis and for each product differently.
     * Some customers includes that information in callof document,
     * other stick to single schema per product. By manual adjustments of demand,
     * customer always specifies desired delivery schema
     * (increase amount in scheduled transport or organize extra transport at given time)
     *
     * TODO algorithm is finding wrong shortages, when more productions is planned in a single day
     */
    public static List<ShortageEntity> findShortages(LocalDate today, int daysAhead, CurrentStock stock,
                                                     List<ProductionEntity> productions, List<DemandEntity> demands)
    {

        AlgorithmFactory factory = new AlgorithmFactory(today, daysAhead, stock, productions, demands);
        ShortagePredictionService service = new ShortagePredictionService(factory);

        List<ShortageEntity> shortages = service.findShortages();

        return shortages;
    }

    private ShortageFinder()
    {
    }

    private static class AlgorithmFactory implements IAlgorithmFactory {
        private LocalDate today;
        private int daysAhead;
        private CurrentStock stock;
        private List<ProductionEntity> productions;
        private List<DemandEntity> demands;

        public AlgorithmFactory(LocalDate today, int daysAhead, CurrentStock stock, List<ProductionEntity> productions, List<DemandEntity> demands) {
            this.today = today;
            this.daysAhead = daysAhead;
            this.stock = stock;
            this.productions = productions;
            this.demands = demands;
        }

        public Algorithm create() {
            List<LocalDate> dates = Stream.iterate(today, date -> date.plusDays(1))
                    .limit(daysAhead)
                    .collect(toList());

            ProductionOutputs outputs = new ProductionOutputs(productions);
            Demands demandsPerDay = new Demands(demands);

            return new Algorithm(stock, dates, outputs, demandsPerDay);
        }
    }

}
