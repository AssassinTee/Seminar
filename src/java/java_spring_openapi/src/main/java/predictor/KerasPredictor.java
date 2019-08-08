package predictor;



import java.io.IOException;

//imports
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.io.ClassPathResource;

public class KerasPredictor {
	MultiLayerNetwork model;
	public KerasPredictor(String path) throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException
	{
		String simpleMlp = new ClassPathResource(path).getFile().getPath();
		model = KerasModelImport.importKerasSequentialModelAndWeights(simpleMlp);
	}
	
	public KerasPredictor(String path1, String path2) throws IOException, InvalidKerasConfigurationException, UnsupportedKerasConfigurationException
	{
		String json = new ClassPathResource(path1).getFile().getPath();
		String weights = new ClassPathResource(path2).getFile().getPath();
		model = KerasModelImport.importKerasSequentialModelAndWeights(json, weights);
	}
	
	public INDArray getPrediction(INDArray features)
	{
		return model.output(features);
	}
	
	public float[] getPrediction(float features[])
	{
		//Check input size and feature size
		long size = model.layerInputSize(0);
		if(features.length != size)
			throw new ArrayIndexOutOfBoundsException();
		
		//convert float array to INDArray-Matrix of shape 1 * size
		INDArray ind = Nd4j.create(features, new long[] {1, size});
		
		//Predict
		INDArray res = getPrediction(ind);
		
		//Convert back and return
		return res.toFloatVector();
	}
	
	public INDArray getTestInput()
	{
		return getTestInput(true);
	}
	
	public INDArray getTestInput(boolean onlyOnesAndZeros)
	{
		long size = model.layerInputSize(0);
		INDArray features = null;
		if(onlyOnesAndZeros)
		{
			features = Nd4j.zeros(1, (int) size);
			for(int i = 0; i < size; ++i)
			{
				features.putScalar(new int[] {0, i}, Math.random() < 0.5? 0 : 1);
			}
		}
		else
		{
			features = Nd4j.rand(1, (int)size);
		}
		return features;
	}
}
