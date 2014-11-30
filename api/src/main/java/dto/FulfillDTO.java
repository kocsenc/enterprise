package dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FulfillDTO {

  private final int requestId;

  @JsonCreator
  public FulfillDTO(@JsonProperty("requestId") int reqId){
    this.requestId = reqId;
  }

  public int getRequestId(){
    return this.requestId;
  }

}