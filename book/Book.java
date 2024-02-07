package book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.lang.Long;
import java.lang.Object;

@Entity
public class Book {
  @Id
  @GeneratedValue
  private Long id;

  public Book() {
  }

  public Object getBook() {
    return this.id;
  }

  public void setBook(Long book) {
    this.id = book;
  }
}
