package domain;

public class Campaign {

  private final String Title;
  private final String Description;
  private final Double Wallet;
  private final Date startDate;
  private final Date endDate;

  public FriendRequest(String Title, String Description, Double Wallet, Date startDate, Date endDate){
    this.Title = Title;
    this.Description = Description;
    this.Wallet = Wallet;
    this.startDate = startDate;
	this.endDate = endDate;
  }

  public String getTitle(){
    return this.Title;
  }

  public String getDescription(){
    return this.Description;
  }

  public double getWallet(){
    return this.Wallet;
  }

  public Date getStartDate() {
    return this.startDate;
  }
  
  public Date getEndDate() {
    return this.endDate;
  }

}