package com.lambdaschool.javacrudyorders.services;

import com.lambdaschool.javacrudyorders.models.Agent;
import com.lambdaschool.javacrudyorders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "agentsService")
public class AgentsServiceImpl implements AgentsService
{
    @Autowired
    AgentsRepository agentsrepos;

    public Agent findAgentById(long id) throws EntityNotFoundException
    {
        return agentsrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(("Agent " + id + " NOT FOUND ")));
    }

}
