package seminar.java_model_loading;

import java.io.IOException;

import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.nd4j.linalg.api.ndarray.INDArray;

import seminar.java_model_loading.machinelearning.Predictor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	if(args.length < 1 || args.length > 2)
    	{
    		System.err.println("Wrong number of arguments!");
    		System.err.println("1.: <path_to_model.h5>");
    		System.err.println("2.: <path_to_model.json> <path_to_weights.h5>");
    		System.exit(1);
    	}
    	String path_to_model = args[0];
    	Predictor p = null;
		try {
			if(args.length == 2)
			{
				String path_to_weights = args[1];
				p = new Predictor(path_to_model, path_to_weights);
			}
			else
				p = new Predictor(path_to_model);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (InvalidKerasConfigurationException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (UnsupportedKerasConfigurationException e) {
			e.printStackTrace();
			System.exit(1);
		}
		INDArray testinput = p.getTestInput();
		System.out.println("Input:");
		System.out.println(testinput);
		
		INDArray testoutput = p.getPrediction(testinput);
		System.out.println("Output:");
		System.out.println(testoutput);
		
		System.exit(0);
    }
}
