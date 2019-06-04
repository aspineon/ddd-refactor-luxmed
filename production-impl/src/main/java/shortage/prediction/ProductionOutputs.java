package shortage.prediction;

import entities.ProductionEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductionOutputs
{

    private final HashMap<LocalDate, List<ProductionEntity>> outputs;
    public String ProductRefNo;

    public ProductionOutputs(List<ProductionEntity> productions)
    {
        outputs = new HashMap<>();
        for (ProductionEntity production : productions)
        {
            outputs.computeIfAbsent(production.getStart().toLocalDate(), localDate -> new ArrayList<>());
            ProductRefNo = production.getForm().getRefNo();
        }
    }

    public long getOutput(LocalDate day)
    {
        List<ProductionEntity> production = outputs.get(day);
        long output = 0;
        for (ProductionEntity entity : production)
        {
            output += entity.getOutput();
        }
        return output;
    }
}
