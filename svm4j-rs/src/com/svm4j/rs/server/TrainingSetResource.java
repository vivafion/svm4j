package com.svm4j.rs.server;

import java.util.ArrayList;
import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.svm4j.Hyperplane;
import com.svm4j.SupportVectorMachine;
import com.svm4j.rs.dao.Dao;
import com.svm4j.rs.entity.Model;
import com.svm4j.rs.xml.ResultConverter;
import com.svm4j.rs.xml.TrainingSetConverter;
import com.svm4j.rs.xml.ResultConverter.Status;

public class TrainingSetResource extends ServerResource {
	
	@Get("xml")
	public ResultConverter test() {
		List<double[]> trainingSet = new ArrayList<double[]>();
		List<Double> classifications = new ArrayList<Double>();
		trainingSet.add(new double[] { 0.0, 0.5 });
		trainingSet.add(new double[] { 0.0, 0.0 });
		classifications.add(1.0);
		classifications.add(-1.0);
		SupportVectorMachine svm = new SupportVectorMachine();
		Hyperplane solution = svm.run(trainingSet, classifications);
		Model model = Model.fromHyperplane(solution);
		Dao.getDao().save(model);
		return new ResultConverter(Status.PASSED, model.getId());
	}

	@Post("xml")
	public ResultConverter train(TrainingSetConverter trainingSet) {
		Object[] xAndY = trainingSet.toXYLists();
		SupportVectorMachine svm = new SupportVectorMachine();
		@SuppressWarnings("unchecked")
		Hyperplane solution = svm.run((List<double[]>) xAndY[0],
				(List<Double>) xAndY[1]);
		Model model = Model.fromHyperplane(solution);
		Dao.getDao().save(model);
		return new ResultConverter(Status.PASSED, model.getId());
	}
}
