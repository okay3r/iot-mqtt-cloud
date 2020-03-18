package ccu.iot.cloud.service.impl;

import ccu.iot.cloud.redis.RedisUtils;
import ccu.iot.cloud.service.MqttCliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MqttCliServiceImpl implements MqttCliService {

    @Autowired
    private RedisUtils redisUtils;

    @Value("${redis.cli.info.name.key}")
    private String cliInfoNameKey;

    @Value("${redis.cli.info.remark.key}")
    private String cliInfoRemarkKey;

    @Override
    public Boolean updateCliInfo(String clientId, String clientName, String remark) {

        boolean hsetName = true;
        boolean hsetRemark = true;

        // 将新的name、remark更新到redis
        if (!StringUtils.isEmpty(clientName)) {
            hsetName = this.redisUtils.hset(cliInfoNameKey, clientId, clientName);
        }
        if (!StringUtils.isEmpty(remark)) {
            hsetRemark = this.redisUtils.hset(cliInfoRemarkKey, clientId, remark);
        }

        return hsetName && hsetRemark;
    }
}
