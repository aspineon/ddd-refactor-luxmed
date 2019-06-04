package acl;

import entities.DemandEntity;
import entities.ProductionEntity;
import external.CurrentStock;
import shortage.prediction.Algorithm;
import shortage.prediction.Demands;
import shortage.prediction.IAlgorithmFactory;
import shortage.prediction.ProductionOutputs;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class AlgorithmFactory implements IAlgorithmFactory
{
    private LocalDate today;
    private int daysAhead;
    private CurrentStock stock;
    ProductionOutputsRepository productions;
    private List<DemandEntity> demands;

    public AlgorithmFactory(LocalDate today, int daysAhead, CurrentStock stock, List<ProductionEntity> productions, List<DemandEntity> demands)
    {
        this.today = today;
        this.daysAhead = daysAhead;
        this.stock = stock;

        this.demands = demands;
    }

    public Algorithm create()
    {
        List<LocalDate> dates = Stream.iterate(today, date -> date.plusDays(1))
                .limit(daysAhead)
                .collect(toList());

        ProductionOutputs outputs = productions.createOutputs();
        Demands demandsPerDay = new Demands(demands);

        return new Algorithm(stock, dates, outputs, demandsPerDay);
    }

}
