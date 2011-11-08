package com.svm4j.rs.server;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.svm4j.Hyperplane;
import com.svm4j.rs.dao.Dao;
import com.svm4j.rs.entity.Converter;
import com.svm4j.rs.entity.Model;
import com.svm4j.rs.xml.ClassifiedPointConverter;
import com.svm4j.rs.xml.ModelConverter;


/**
 * RESTfull resource class for Model objects.
 * 
 * @author Tommer
 *
 */
public class ModelResource extends ServerResource {
	
	@Get("xml")
	public ModelConverter getModel() {
		Long id = Long.parseLong((String) this.getRequest().getAttributes().get("id"));
		Model model = Dao.getDao().get(Model.class, id);
		return ModelConverter.fromModel(model);
	}

	@Post("xml")
	public ClassifiedPointConverter classify(ClassifiedPointConverter point) {
		Long id = Long.parseLong((String) this.getRequest().getAttributes().get("id"));
		Model model = Dao.getDao().get(Model.class, id);
		Hyperplane hyperplane = model.toHyperplane();
		point.setY(hyperplane.classify(Converter.buildArray(point.getX())));
		return point;	
	}
}
