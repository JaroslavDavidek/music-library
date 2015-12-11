package cz.fi.muni.pa165.rest.layer.rest.controllers;

import cz.fi.muni.pa165.rest.layer.rest.ApiUris;
import java.util.Map;
import java.util.Collections;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author JaroslavDavidek
 */
@RestController
public class MainController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Map<String, String> getResources() {

        Map<String,String> resourcesMap = new HashMap<String,String>();     
        resourcesMap.put("songs", ApiUris.ROOT_URI_SONGS);
        return Collections.unmodifiableMap(resourcesMap);        
    }
}
