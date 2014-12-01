package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import utility.DBUtility;
import domain.Campaign;
import domain.Contribution;


public class CampaignService {

    private Connection connection;

    public CampaignService() {
        connection = DBUtility.getConnection();
    }

    public ResponseEntity<String> createCampaign(Campaign campaign) throws SQLException {
        PreparedStatement createCampaignStatement = null;
        try{


            String createCampaignString = "INSERT into Campaign values(NULL, ?, ?, ?, ?, ?, ?, ?)";
            createCampaignStatement = connection.prepareStatement(createCampaignString);
            createCampaignStatement.setInt(1, campaign.getCreator());
            createCampaignStatement.setDouble(2, campaign.getWallet());
            createCampaignStatement.setString(3, campaign.getDescription());
            createCampaignStatement.setDouble(4, campaign.getGoal());
            createCampaignStatement.setDate(5, new java.sql.Date(campaign.getStartDate().getTime()));
            createCampaignStatement.setDate(6, new java.sql.Date(campaign.getEndDate().getTime()));
            createCampaignStatement.setString(7, campaign.getTitle());
            createCampaignStatement.executeUpdate();
            System.out.println("test");


        }catch(SQLException e){
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            createCampaignStatement.close();
            if(createCampaignStatement != null){
                return new ResponseEntity<String>(HttpStatus.CREATED);
            }

            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    public List<Campaign> campaigns(int id) throws SQLException{
        List<Campaign> campaigns = new ArrayList<Campaign>();
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Campaign where creator = " + id);
            while (rs.next()) {
                int cid = rs.getInt("cid");
                int creator = rs.getInt("creator");
                double wallet = rs.getDouble("wallet");
                String description = rs.getString("description");
                double goal = rs.getDouble("goal");
                Date start_date = rs.getDate("start_date");
                Date end_date = rs.getDate("end_date");
                String title = rs.getString("Title");
                Campaign campaign = new Campaign(cid, title, description, wallet, start_date, end_date, creator, goal);
                if(campaigns.contains((campaign)) == false){
                    campaigns.add(campaign);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return campaigns;
        }

    }

    public List<Campaign> friendCampaigns(int id) throws SQLException{
        List<Campaign> campaigns = new ArrayList<Campaign>();
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Campaign where creator = " + id);
            while (rs.next()) {
                int cid = rs.getInt("cid");
                int creator = rs.getInt("creator");
                double wallet = rs.getDouble("wallet");
                String description = rs.getString("description");
                double goal = rs.getDouble("goal");
                Date start_date = rs.getDate("start_date");
                Date end_date = rs.getDate("end_date");
                String title = rs.getString("Title");
                Campaign campaign = new Campaign(cid, title, description, wallet, start_date, end_date, creator, goal);
                if(campaigns.contains((campaign)) == false){
                    campaigns.add(campaign);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return campaigns;
        }

    }

    public ResponseEntity<String> contribute(Contribution contribution) throws SQLException {
        PreparedStatement contributeStatement = null;
        try{

            String contributeString = "INSERT into Contributor values(?, ?, ?)";
            contributeStatement = connection.prepareStatement(contributeString);
            contributeStatement.setInt(1, contribution.getCid());
            contributeStatement.setInt(2, contribution.getUid());
            contributeStatement.setDouble(3, contribution.getAmount());
            contributeStatement.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            contributeStatement.close();
            if(contributeStatement != null){
                return new ResponseEntity<String>(HttpStatus.CREATED);
            }

            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }


    public List<Contribution> contributions(int id) throws SQLException{
        List<Contribution> contributions = new ArrayList<Contribution>();
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("Select * from Contributor where uid = " + id);
            while(rs.next()) {
                int cid = rs.getInt("cid");
                System.out.println(cid);
                double amount = rs.getDouble("amount");
                Statement campaignStatement = connection.createStatement();
                ResultSet campaignRS = campaignStatement.executeQuery("select * from Campaign where cid = " + cid);
                while (campaignRS.next()) {
                    String description = campaignRS.getString("description");
                    String title = campaignRS.getString("Title");
                    System.out.println(title);
                    Contribution contribution = new Contribution(cid, id, amount, description, title);
                    if (contributions.contains((contribution)) == false) {
                        contributions.add(contribution);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return contributions;
        }

    }

}