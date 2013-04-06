package org.growersnation.site.repository.mem;

import com.google.common.collect.Lists;
import org.bson.types.ObjectId;
import org.growersnation.site.repository.EntityRepository;
import org.growersnation.site.repository.Persistable;

import java.util.List;

public class InMemoryEntityRepository<T extends Persistable<String>> implements EntityRepository<T, String> {

  /**
   * Maintain a simple mutable list
   */
  private final List<T> internalEntities = Lists.newArrayList();

  @Override
  public String save(T entity) {
    synchronized (internalEntities) {
      int index = internalEntities.indexOf(entity);
      if (index == -1) {
        if (entity.getId() == null) {
          entity.setId(ObjectId.get().toString());
        }
        internalEntities.add(entity);
      } else {
        internalEntities.remove(index);
        internalEntities.add(index, entity);
      }
      return entity.getId();
    }
  }

  @Override
  public String create(T entity) {
    synchronized (internalEntities) {
      internalEntities.add(entity);
      return entity.getId();
    }
  }

  @Override
  public List<String> createAll(List<T> entities) {
    synchronized (internalEntities) {
      List<String> ids = Lists.newArrayList();
      for (T entity : entities) {
        ids.add(create(entity));
      }
      return ids;
    }
  }

  @Override
  public String upsert(T entity) {
    synchronized (internalEntities) {
      // TODO Relies on ID being set and being included in equals()/hashCode()
      // Currently this is broken
      int index = internalEntities.indexOf(entity);
      if (index == -1) {
        internalEntities.add(entity);
      } else {
        internalEntities.remove(index);
        internalEntities.add(index, entity);
      }
      return entity.getId();
    }

  }

  @Override
  public List<String> upsertAll(List<T> entities) {
    synchronized (internalEntities) {
      List<String> ids = Lists.newArrayList();
      for (T entity : entities) {
        ids.add(upsert(entity));
      }
      return ids;
    }
  }

  @Override
  public void hardDelete(T entity) {
    synchronized (internalEntities) {
      internalEntities.remove(entity);
    }
  }

  public List<T> retrieveAll() {
    return internalEntities;
  }
}
