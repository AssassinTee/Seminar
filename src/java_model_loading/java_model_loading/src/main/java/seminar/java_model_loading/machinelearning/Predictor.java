package seminar.java_model_loading.machinelearning;

import java.io.IOException;

//imports
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.io.ClassPathResource;

public class Predictor {
	MultiLayerNetwork model;
	public Predictor(String path) throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException
	{
		String simpleMlp = new ClassPathResource(path).getFile().getPath();
		model = KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);
	}
	
	public Predictor(String path1, String path2) throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException
	{
		String json = new ClassPathResource(path1).getFile().getPath();
		String weights = new ClassPathResource(path2).getFile().getPath();
		model = KerasModelImport.importKerasSequentialModelAndWeights(json, weights);
	}
	
	public INDArray getPrediction(INDArray features)
	{
		return model.output(features);
	}
	
	public INDArray getTestInput(int size)
	{
		INDArray features = Nd4j.zeros(1, size);
		for(int i = 0; i < 28; ++i)
		{
			features.putScalar(new int[] {0, i}, Math.random() < 0.5? 0 : 1);
		}
		return features;
	}
}
