package acl;

import entities.ProductionEntity;
import shortage.prediction.ProductionOutputs;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ProductionOutputsRepository {

    public ProductionOutputs createOutputs() {
        List<ProductionEntity> productions = null;

        Map<LocalDate, Long> outputs = productions.stream().collect(
                Collectors.groupingBy(
                        production -> production.getStart().toLocalDate(),
                        Collectors.summingLong(ProductionEntity::getOutput)
                )
        );

        return new ProductionOutputs(outputs);
    }
}
