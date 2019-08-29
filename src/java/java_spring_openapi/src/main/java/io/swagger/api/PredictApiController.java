package io.swagger.api;

import io.swagger.model.DataArray;
import predictor.KerasPredictor;
import predictor.MxnetPredictor;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.*;

import org.apache.mxnet.javaapi.Shape;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-08-06T13:45:03.308Z[GMT]")
@Controller
public class PredictApiController implements PredictApi {

    private static final Logger log = LoggerFactory.getLogger(PredictApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    private String path1 = "model_kaggle_cooking.h5";
    private String path2 = "src/main/resources/kc_mxnet";
    private KerasPredictor kerasPredictor;
    private MxnetPredictor mxnetPredictor;

    @org.springframework.beans.factory.annotation.Autowired
    public PredictApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
        try {
			this.kerasPredictor = new KerasPredictor(path1);//, path2);
		} catch (IOException | InvalidKerasConfigurationException | UnsupportedKerasConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Shape inputShape = new Shape(new int[]{1, 1, 1, 3010});
    	this.mxnetPredictor = new MxnetPredictor(path2, inputShape);
    }

    public ResponseEntity<DataArray> predictPost(@ApiParam(value = ""  )  @Valid @RequestBody List<Float> body) {
        String accept = request.getHeader("Content-Type");
        
        if (accept != null && accept.contains("application/json")) {
            //System.out.println("recieved body");
            //System.out.println(body);
			float[] input = new float[body.size()];
			for(int i = 0; i < body.size(); ++i)
				input[i] = body.get(i);
			float[] res;
			try {
				res = kerasPredictor.getPrediction(input);
				//res = mxnetPredictor.predict(input);
			} catch(ArrayIndexOutOfBoundsException e) {
				return new ResponseEntity<DataArray>(HttpStatus.BAD_REQUEST);
			}
				
			//Convert Result
			DataArray dt = new DataArray();
			for(int i = 0; i < res.length; ++i)
				dt.add(Float.valueOf(res[i]));
				
			return ResponseEntity.ok(dt);
        }

        return new ResponseEntity<DataArray>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

}
