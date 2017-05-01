package ru.project.master.slave.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.project.master.slave.annotations.ReadOnly;
import ru.project.master.slave.dao.EventService;
import ru.project.master.slave.dao.repository.EventRepository;
import ru.project.master.slave.orm.Event;
import ru.project.master.slave.utils.ScopeType;

@Service
@Repository
@Scope(ScopeType.SINGLETON)
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository repository;
	
	@Override
	@ReadOnly
	@Transactional(readOnly = true)
	public List<Event> getEvents() {
		return repository.getAll();
	}

	@Override
	@ReadOnly
	@Transactional(readOnly = true)
	public Event getEventById(String id) {
		return repository.findOne(id);
	}

	@Override
	@Transactional
	public void updateEvent(Event event) {
		repository.save(event);
	}

	@Override
	@Transactional
	public void createEvent(Event event) {
		repository.save(event);
	}

	@Override
	@Transactional
	public void delete(Event event) {
		repository.delete(event);
	}

}
