/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.10).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.DataArray;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-08-06T13:45:03.308Z[GMT]")
@Api(value = "predict", description = "the predict API")
public interface PredictApi {

    @ApiOperation(value = "", nickname = "predictPost", notes = "Returns a prediction-vector of the ML-model", response = DataArray.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful prediction", response = DataArray.class),
        @ApiResponse(code = 400, message = "Invalid request") })
    @RequestMapping(value = "/predict",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<DataArray> predictPost(@ApiParam(value = ""  )  @Valid @RequestBody List<Float> body);

}
