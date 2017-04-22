package ru.project.master.slave.dao.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.project.master.slave.Storage;
import ru.project.master.slave.dao.EventService;
import ru.project.master.slave.orm.Event;
import ru.project.master.slave.utils.ScopeType;

@Service
@Repository
@Scope(ScopeType.SINGLETON)
public class EventServiceImpl implements EventService {

	@Override
	@Transactional(readOnly = true)
	public List<Event> getEvents() {
		return Storage.getEntityManager().createNamedQuery("getAll", Event.class).getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Event getEvent(String id) {
		return Storage.getEntityManager().find(Event.class, id);
	}

	@Override
	@Transactional
	public void updateEvent(Event event) {
		Storage.getEntityManager().persist(event);
	}

	@Override
	@Transactional
	public void createEvent(Event event) {
		Storage.getEntityManager().persist(event);
	}

	@Override
	@Transactional
	public void deleteEvent(Event event) {
		Storage.getEntityManager().remove(event);
	}

}
