package net.controller;

import net.factory.ReportType;
import net.mediator.Mediator;
import net.mediator.handler.ReadReportQueryHandler;
import net.mediator.query.ReportQuery;
import net.mediator.response.FindReportQueryResponse;
import net.model.CreatedReport;
import net.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "Authorization")
public class ReportController {

    private final Mediator mediator;

    public ReportController(Mediator mediator) {
        this.mediator = mediator;
    }

    private CreatedReport setup(String reportType, String username){
        ReportQuery request = new ReportQuery(reportType, username);
        ReadReportQueryHandler handler = (ReadReportQueryHandler)mediator.<ReportQuery, FindReportQueryResponse>handle(request);
        FindReportQueryResponse response = handler.handle(request);
        CreatedReport createdReport = response.getReportDTO();
        return createdReport;
    }

    @RequestMapping(value = "/report/{username}/{reportType}/eatenCalories", method = RequestMethod.GET)
    public ResponseEntity<Integer> getEatenCalories(@PathVariable(name = "username") String username, @PathVariable(name = "reportType") String reportType) {
        CreatedReport createdReport = setup(reportType, username);
        return new ResponseEntity<>(createdReport.getEatenCalories(), HttpStatus.OK);
    }

    @RequestMapping(value = "/report/{username}/{reportType}/wastedCalories", method = RequestMethod.GET)
    public ResponseEntity<Integer> getWastedCalories(@PathVariable(name = "username") String username, @PathVariable(name = "reportType") String reportType) {
        CreatedReport createdReport = setup(reportType, username);
        return new ResponseEntity<>(createdReport.getWastedCalories(), HttpStatus.OK);
    }

    @RequestMapping(value = "/report/{username}/{reportType}/eatenFood", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> getEatenFood(@PathVariable(name = "username") String username, @PathVariable(name = "reportType") String reportType) {
         CreatedReport createdReport = setup(reportType, username);
        List<Item> eatenFood = createdReport.getEatenFood();
        return new ResponseEntity<>(eatenFood, HttpStatus.OK);
    }

    @RequestMapping(value = "/report/{username}/{reportType}/expiredFood", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> getExpiredFood(@PathVariable(name = "username") String username, @PathVariable(name = "reportType") String reportType) {
        CreatedReport createdReport = setup(reportType, username);
        List<Item> expiredFood = createdReport.getExpiredFood();
        return new ResponseEntity<>(expiredFood, HttpStatus.OK);
    }

}
