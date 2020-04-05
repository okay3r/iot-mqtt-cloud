package yx.graduation.elec.async;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import yx.graduation.elec.pojo.vo.MessageVo;
import yx.graduation.elec.service.DataRecordService;
import yx.graduation.enums.RedisKeyEnum;
import yx.graduation.utils.RedisOperator;

/**
 * 处理消息
 */
@Component
public class MessageHandler {

    @Autowired
    private DataRecordService dataRecordService;

    @Autowired
    private RedisOperator redisOperator;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandler.class);


    /**
     * 异步处理接收到的消息
     */
    @Async("msgHandlerExecutor")
    public void handleMsg(MessageVo messageVo) {
        int value = Integer.parseInt(messageVo.getMsg());
        try {
            this.dataRecordService.storeMessage(messageVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String upKey = RedisKeyEnum.ALARM_UP.value + ":" + messageVo.getDeviceId() + ":" + messageVo.getParameter();
        String downKey = RedisKeyEnum.ALARM_DOWN.value + ":" + messageVo.getDeviceId() + ":" + messageVo.getParameter();

        String upString = (String) this.redisOperator.get(upKey);
        String downString = (String) this.redisOperator.get(downKey);
        if (StringUtils.isBlank(upString) && StringUtils.isBlank(downString)) {
            return;
        }

        Integer upValue = null;
        Integer downValue = null;
        try {
            upValue = Integer.parseInt(upString);
            downValue = Integer.parseInt(downString);
        } catch (NumberFormatException e) {
        }

        if ((upValue != null && value >= upValue) || (downValue != null && value <= downValue)) {
            String hasAlarm = RedisKeyEnum.HAS_ALARM.value + ":" + messageVo.getDeviceId() + ":" + messageVo.getParameter();
            Object has = this.redisOperator.get(hasAlarm);
            if (has == null) {
                //TODO 发送邮件、短信业务
                LOGGER.warn("设备" + messageVo.getDeviceId() + "发生异常！  " + messageVo.getParameter() + " = " + value);
                this.redisOperator.set(hasAlarm, messageVo.getMsg(), 10);
            }
        }
    }
}
