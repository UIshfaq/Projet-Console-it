package console.consoleit.repositories;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepositoryInterface<T,ID> {
    ArrayList<T> getAll() throws SQLException;
    void create (T obj) throws SQLException;
    void update (T obj) throws SQLException;
    void delete (T obj) throws SQLException;

}
