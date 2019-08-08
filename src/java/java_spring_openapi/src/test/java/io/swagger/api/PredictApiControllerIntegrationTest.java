package io.swagger.api;

import io.swagger.model.DataArray;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PredictApiControllerIntegrationTest {

    @Autowired
    private PredictApi api;

    @Test
    public void predictPostTest() throws Exception {
        List<Float> body = Arrays.asList(3.4F);
        ResponseEntity<DataArray> responseEntity = api.predictPost(body);
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, responseEntity.getStatusCode());
    }

}
