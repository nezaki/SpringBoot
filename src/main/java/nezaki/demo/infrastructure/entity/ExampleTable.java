package nezaki.demo.infrastructure.entity;

import java.sql.Timestamp;

public class ExampleTable {
  private int id;
  private String exampleString;
  private int exampleNumber;
  private boolean exampleBoolean;
  private Timestamp exampleDatetime;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getExampleString() {
    return exampleString;
  }

  public void setExampleString(String exampleString) {
    this.exampleString = exampleString;
  }

  public int getExampleNumber() {
    return exampleNumber;
  }

  public void setExampleNumber(int exampleNumber) {
    this.exampleNumber = exampleNumber;
  }

  public boolean isExampleBoolean() {
    return exampleBoolean;
  }

  public void setExampleBoolean(boolean exampleBoolean) {
    this.exampleBoolean = exampleBoolean;
  }

  public Timestamp getExampleDatetime() {
    return exampleDatetime;
  }

  public void setExampleDatetime(Timestamp exampleDatetime) {
    this.exampleDatetime = exampleDatetime;
  }
}
