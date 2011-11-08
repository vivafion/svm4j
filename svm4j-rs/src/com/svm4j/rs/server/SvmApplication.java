package com.svm4j.rs.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

import com.svm4j.rs.dao.Dao;

public class SvmApplication extends Application {

	private Dao dao;

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		Dao.setDao(dao);
		router.attachDefault(new Directory(getContext(), "war:///"));
		router.attach("/trainingset", TrainingSetResource.class);
		router.attach("/model/{id}/classification", ModelResource.class);
		router.attach("/model/{id}", ModelResource.class);

		return router;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

}
