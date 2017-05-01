package ru.project.master.slave.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.project.master.slave.orm.Event;

/**
 * Интерфейс-репозиторий для операций с сущностью {@link Event}.
 * 
 * @author Alimurad A. Ramazanov
 * @version 1.0.0
 * @since 01.05.2017
 *
 */
public interface EventRepository extends JpaRepository<Event, String> {

	@Query(name = "getAll", value = "SELECT event FROM Event event")
	List<Event> getAll();
}
