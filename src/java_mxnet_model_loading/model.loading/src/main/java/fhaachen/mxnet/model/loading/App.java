package fhaachen.mxnet.model.loading;

import org.apache.mxnet.javaapi.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	//NCHW N = batch size, c = channel, h = height, w = width
    	Shape inputShape = new Shape(new int[]{1, 1, 1, 3010});
    	ModelPredictor mp = new ModelPredictor("src/main/resources/kc_mxnet", inputShape);
    	System.out.println("test");
    	
    	float test[] = new float[3010];
    	for(int i = 0; i < 3010; ++i)
    		test[i] = 0;

    	NDArray test2 = new NDArray(test, inputShape, mp.getContext().get(0));
    	test2.set(test);
    	
    	System.out.println("predict:");
    	float result[] = mp.predict(test2);
    	int max = ModelPredictor.getMaximumClass(result);
    	System.out.println("maxid: "+max+" result_len: "+result.length+" probabilities:");
    	for(int i = 0; i < result.length; ++i)
    	{
    		System.out.print(result[i]);
    		if(i != result.length-1)
    		{
    			System.out.print(", ");
    		}
    	}
    	System.out.println();
    }
}
