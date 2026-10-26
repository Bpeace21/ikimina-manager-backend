package rw.ikimina.ikimina_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rw.ikimina.ikimina_manager.service.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardServ;

    @GetMapping("/summary")
    public ResponseEntity<?> summary() {
        return new ResponseEntity<>(dashboardServ.getSummary(), HttpStatus.OK);
    }
}
