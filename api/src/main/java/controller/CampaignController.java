package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


import java.util.List;
import dao.CampaignService;
import domain.User;
import domain.Campaign;
import java.sql.SQLException;
import domain.Contribution;


@RestController
@RequestMapping("/api/campaign")
public class CampaignController {

    CampaignService campaignService = new CampaignService();

    @RequestMapping(value = "/create", consumes="application/json")
    public ResponseEntity<String> createCampaign(@RequestBody Campaign campaign)throws SQLException{
        ResponseEntity<String> accepted = campaignService.createCampaign(campaign);
        return accepted;
    }

    @RequestMapping(value = "{id}/campaigns")
    public List<Campaign> campaigns(@PathVariable int id) throws SQLException {
        List<Campaign> campaigns = campaignService.campaigns(id);
        return campaigns;
    }

    @RequestMapping(value = "{id}/friend_campaigns")
    public List<Campaign> friendCampaigns(@PathVariable int id) throws SQLException {
        List<Campaign> campaigns = campaignService.friendCampaigns(id);
        return campaigns;
    }

   @RequestMapping(value = "{id}/contributions")
    public List<Contribution> contributions(@PathVariable int id) throws SQLException {
        List<Contribution> contributions = campaignService.contributions(id);
        return contributions;
    }

    @RequestMapping(value = "{id}/contribute", consumes="application/json")
    public ResponseEntity<String> contribute(@PathVariable int id, @RequestBody Contribution contribution) throws SQLException {
        ResponseEntity<String> accepted = campaignService.contribute(contribution);
        return accepted;
    }

}