package com.melon.kafkademo.application;

import com.melon.kafkademo.common.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "app")
@Validated
public class AppController {

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @PostMapping(value = "deploy")
    public RestResponse appDeploy(@Valid AppDto appDto) {
        appService.appDeploy(appDto);
        return RestResponse.success();


    }
}
