package featureName;

import java.lang.Long;
import java.lang.Object;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MockController {
  @Autowired
  private Service service;

  @GetMapping
  @ResponseBody
  Object getMock(@PathVariable Long id) {
  }

  @CrossOrigin
  @GetMapping
  @ResponseBody
  List<Object> getAllMock() {
  }

  @PostMapping
  @ResponseBody
  void postMock(Object mock) {
  }

  @PutMapping
  @ResponseBody
  void putMock(Object modifiedMock, Object targetMock) {
  }

  @DeleteMapping
  @ResponseBody
  void deleteMock(@PathVariable Long id) {
  }
}
