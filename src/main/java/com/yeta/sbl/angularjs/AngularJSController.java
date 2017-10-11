package com.yeta.sbl.angularjs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-9-29.
 */
@RestController
public class AngularJSController {

    @GetMapping(value = "/search")
    public Map<String, Object> search(@RequestParam(value = "name") String name){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("name", name);
        result.put("age", 20);
        result.put("address", "成都");
        return result;
    }
}
