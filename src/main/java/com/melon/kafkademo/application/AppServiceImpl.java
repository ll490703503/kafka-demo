package com.melon.kafkademo.application;

import com.melon.kafkademo.common.util.JsonHelper;
import com.melon.kafkademo.kafka.KafkaSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppServiceImpl implements AppService {

    private final KafkaSendService kafkaService;

    @Autowired
    public AppServiceImpl(KafkaSendService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @Override
    public void appDeploy(AppDto appDto) {
        String envName = appDto.getEnvName();
        String app = JsonHelper.obj2String(appDto);
        kafkaService.sendMessage(envName, app);
    }
}
