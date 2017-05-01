package ru.project.master.slave;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ru.project.master.slave.dao.EventService;
import ru.project.master.slave.orm.Event;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MasterSlaveApplicationTests extends Assert {

	private Event event;

	@Autowired
	private EventService service;

	@Before
	public void init() {
		event = new Event("msg");
	}

	@Test
	public void contextLoads() {
		assertNotNull(event);
		service.createEvent(event);
		assertNotNull(event.getId());
	}

}
