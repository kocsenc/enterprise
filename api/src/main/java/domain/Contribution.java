package domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Contribution {

  private final String campaignTitle;
  private final String campaignDescription;
  private final double amount;
  private final int uid;
  private final int cid;

  public Contribution(int cid, int uid, double amount, String CampaignDescription, String CampaignTitle){
    this.cid = cid;
    this.uid = uid;
    this.campaignDescription = CampaignDescription;
    this.campaignTitle = CampaignTitle;
    this.amount = amount;
  }

  @JsonCreator
  public Contribution(@JsonProperty("uid") int uid, @JsonProperty("cid") int cid, @JsonProperty("amount") double amount){
    this.cid = cid;
    this.uid = uid;
    this.campaignDescription = " ";
    this.campaignTitle = " ";
    this.amount = amount;
  }

  public String getCampaignTitle(){
    return this.campaignTitle;
  }

  public String getCampaignDescription(){
    return this.campaignDescription;
  }

  public double getAmount(){
    return this.amount;
  }

  public int getUid() {return this.uid; }

  public int getCid() {return this.cid;}
}