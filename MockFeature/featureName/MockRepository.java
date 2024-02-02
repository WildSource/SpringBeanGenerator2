package featureName;

import java.lang.Long;
import java.lang.String;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockRepository extends CrudRepository<String, Long> {
}
