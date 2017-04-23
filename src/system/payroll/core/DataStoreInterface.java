
package system.payroll.core;

import java.util.List;

public interface DataStoreInterface<T>
{
    boolean Add(T entity);
    boolean Add(Iterable<T> entities);
    
    T Get(int id);
    List<T> GetAll();
    List<T> GetRange(int from, int to);
    
    boolean update(T entity);
    boolean remove(T entity);
    
}
