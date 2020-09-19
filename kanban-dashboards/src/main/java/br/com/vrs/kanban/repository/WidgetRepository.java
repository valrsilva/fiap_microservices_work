package br.com.vrs.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vrs.kanban.model.Widget;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, Long>{}
