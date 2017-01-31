package org.planning.rest.controller.impl;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pascalstammer
 * @version 30.01.17.
 */
@RestController
@EnableAutoConfiguration
public class ConfigurationControllerImpl {

    @RequestMapping("/")
    @ResponseBody
    public String indexAction() {
        return "Hello World";
    }
}
