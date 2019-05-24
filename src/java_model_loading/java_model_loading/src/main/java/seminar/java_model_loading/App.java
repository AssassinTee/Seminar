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
    	if(args.length < 1)
    	{
    		System.out.println("Missing argument: <path to model>");
    		System.exit(1);
    	}
    	String path_to_model = args[0];
    	Integer size = Integer.parseInt(args[1]);
    	Predictor p = null;
		try {
			/*if(args.length == 2)
			{
				String path_to_weights = args[1];
				p = new Predictor(path_to_model, path_to_weights);
			}*/
			//else
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
		INDArray testinput = p.getTestInput(size);
		System.out.println("Input:");
		System.out.println(testinput);
		
		INDArray testoutput = p.getPrediction(testinput);
		System.out.println("Output:");
		System.out.println(testoutput);
		
		System.exit(0);
    }
}
