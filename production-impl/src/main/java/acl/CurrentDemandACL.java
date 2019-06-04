package acl;

import dao.DemandDao;
import entities.DemandEntity;
import tools.Util;

import java.util.List;
import java.util.stream.Stream;

public class CurrentDemandACL implements ICurrentDemand
{
    DemandDao dao;

    public Stream<ICurrentDemand.CurrentDemandReadModel> get()
    {
        List<DemandEntity> entities = dao.findFrom(null, null);
        return entities.stream()
                .map(entity -> new ICurrentDemand.CurrentDemandReadModel(
                        entity.getDay(),
                        Util.getDeliverySchema(entity),
                        Util.getLevel(entity)

                ));
    }
}
