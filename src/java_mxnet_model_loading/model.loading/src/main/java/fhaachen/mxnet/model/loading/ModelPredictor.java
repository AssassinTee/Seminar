package fhaachen.mxnet.model.loading;

import java.util.*;

import org.apache.mxnet.infer.javaapi.Predictor;
import org.apache.mxnet.javaapi.*;

public class ModelPredictor {
	Predictor predictor = null;
	String modelPathPrefix;
	List<Context> context;
	
	public ModelPredictor(String modelPathPrefix, Shape inputShape)
	{
		this.modelPathPrefix = modelPathPrefix;
		 // Prepare the model
        context = new ArrayList<Context>();
        if (System.getenv().containsKey("SCALA_TEST_ON_GPU") &&
                Integer.valueOf(System.getenv("SCALA_TEST_ON_GPU")) == 1) {
            context.add(Context.gpu());
            System.out.println("Added gpu context");
        } else {
            context.add(Context.cpu());
            System.out.println("Added cpu context");
        }
        List<DataDesc> inputDesc = new ArrayList<>();
        System.out.println("shaped");
        inputDesc.add(new DataDesc("/dense_1_input1", inputShape, DType.Float32(), "NCHW"));
        predictor = new Predictor(modelPathPrefix, inputDesc, context,0);
	}
	
	public List<Context> getContext()
	{
		return context;
	}
	
	public float[] predict(NDArray test2)
	{
		List<NDArray> ndList = new ArrayList<>();
		ndList.add(test2);
		List<NDArray> ndResult = predictor.predictWithNDArray(ndList);
		float result[] = ndResult.get(0).toArray();
	    
		System.out.println("Predict with Float input");
	    System.out.println(getMaximumClass(result));
		return result;
	}
	
	public float[] predict(float data[])
	{
		NDArray nd = new NDArray(data, new Shape(new int[]{data.length}), null);
		nd = nd.asType(DType.Float32());
		
		return predict(nd);
	}
	
	public static int getMaximumClass(float[] probabilities) {
		int maxIdx = 0;
		for (int i = 1;i<probabilities.length;i++) {
			if (probabilities[i] > probabilities[maxIdx]) {
				maxIdx = i;
			}
		}
		return maxIdx;
	}
}
