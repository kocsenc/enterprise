package domain;

public class FriendRequest {

  private final Integer rid;
  private final Integer sender;
  private final Integer receiver;
  private final Boolean trust;

  public FriendRequest(Integer rid, Integer sender, Integer receiver, Boolean trust){
    this.rid = rid;
    this.sender = sender;
    this.receiver = receiver;
    this.trust = trust;
  }

  public int getRid(){
    return this.rid;
  }

  public int getSender(){
    return this.sender;
  }

  public int getReciever(){
    return this.receiver;
  }

  public boolean getTrust() {
    return this.trust;
  }

}