package io.swagger.api;


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
public class PredictlabelApiControllerIntegrationTest {

    @Autowired
    private PredictlabelApi api;

    @Test
    public void predictlabelPostTest() throws Exception {
        List<Float> body = Arrays.asList(3.4F);
        ResponseEntity<Integer> responseEntity = api.predictlabelPost(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
