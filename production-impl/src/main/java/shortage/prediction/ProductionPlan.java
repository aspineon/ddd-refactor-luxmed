package shortage.prediction;

import entities.ProductionEntity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class ProductionPlan
{

    private final HashMap<LocalDate, ProductionEntity> outputs;
    public String ProductRefNo;

    public ProductionPlan(List<ProductionEntity> productions)
    {
        outputs = new HashMap<>();
        for (ProductionEntity production : productions)

        {
            outputs.put(production.getStart().toLocalDate(), production);
            ProductRefNo = production.getForm().getRefNo();
        }
    }

    public long getOutput(LocalDate day) {
        ProductionEntity production = outputs.get(day);
        if (production != null)
        {
            return production.getOutput();
        }
        else
        {
            return 0;
        }

    }
}
