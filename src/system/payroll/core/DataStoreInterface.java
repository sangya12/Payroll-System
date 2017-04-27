
package system.payroll.core;

import java.util.List;

public interface DataStoreInterface<T>
{
    int Add(T entity);
    //boolean Add(Iterable<T> entities);
    
    T Get(int id);
    List<T> GetAll();
    
    boolean update(T entity);
    boolean remove(T entity);
    
}
