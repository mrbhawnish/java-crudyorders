package com.lambdaschool.javacrudyorders.repositories;

import com.lambdaschool.javacrudyorders.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentsRepository extends CrudRepository<Agent, Long>
{
}
