package com.lambdaschool.javacrudyorders.controllers;

import com.lambdaschool.javacrudyorders.models.Agent;
import com.lambdaschool.javacrudyorders.services.AgentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agents")
public class AgentsController
{


    @Autowired
    AgentsService agentsService;

    // http://localhost:2019/agents/agent/9

    @GetMapping(value = "/agent/{agentcode}", produces = {"application/json"})
    public ResponseEntity<?> getAgentById(@PathVariable long agentcode)
    {
       Agent a =  agentsService.findAgentById(agentcode);
       return new ResponseEntity<>(a,
           HttpStatus.OK);

    }
}
