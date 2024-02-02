package featureName;

import java.lang.Long;
import java.lang.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class MockService {
  @Autowired
  private Repository repository;

  private Object readMockEntity(Long id) {
  }

  private void createMockEntity(Object mock) {
  }

  private void modifyMockEntity(Object modifiedMock, Long targetMock) {
  }

  private void deleteMockEntity(Long id) {
  }
}
