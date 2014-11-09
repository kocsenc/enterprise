package domain;

public class FriendRequest {

  private final int rid;
  private final int sender;
  private final int receiver;
  private final Boolean trust;

  public FriendRequest(int rid, int sender, int receiver, Boolean trust){
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