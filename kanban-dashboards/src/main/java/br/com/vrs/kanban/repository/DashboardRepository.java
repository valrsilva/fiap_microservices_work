package br.com.vrs.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vrs.kanban.model.Dashboard;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard, Long>{}
