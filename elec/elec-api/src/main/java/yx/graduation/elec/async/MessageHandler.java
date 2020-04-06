package yx.graduation.elec.async;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Component;
import yx.graduation.elec.pojo.Device;
import yx.graduation.elec.pojo.User;
import yx.graduation.elec.pojo.vo.MessageVo;
import yx.graduation.elec.service.DataRecordService;
import yx.graduation.elec.service.DeviceService;
import yx.graduation.elec.service.EmailService;
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

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private EmailService emailService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandler.class);


    /**
     * 异步处理接收到的消息
     */
    @Async("msgHandlerExecutor")
    public void handleMsg(MessageVo messageVo) {
        int value = Integer.parseInt(messageVo.getMsg());
        try {
            //将消息存储到db
            this.dataRecordService.storeMessage(messageVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //报警值上下限在redis中的key
        String deviceId = messageVo.getDeviceId();
        String upKey = RedisKeyEnum.ALARM_UP.value + ":" + deviceId + ":" + messageVo.getParameter();
        String downKey = RedisKeyEnum.ALARM_DOWN.value + ":" + deviceId + ":" + messageVo.getParameter();

        //获取到redis中的值
        String upString = (String) this.redisOperator.get(upKey);
        String downString = (String) this.redisOperator.get(downKey);

        //如果上下限都为空，则表明没有设置报警值，直接返回
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

        //进行比较，如果超出了范围，则进行报警
        if ((upValue != null && value >= upValue) || (downValue != null && value <= downValue)) {
            String hasAlarm = RedisKeyEnum.HAS_ALARM.value + ":" + deviceId + ":" + messageVo.getParameter();
            Object has = this.redisOperator.get(hasAlarm);
            if (has == null) {
                LOGGER.warn("设备" + deviceId + "发生异常！  " + messageVo.getParameter() + " = " + value);
                this.redisOperator.set(hasAlarm, messageVo.getMsg(), 10);

                User user = this.deviceService.queryUserByDeviceId(deviceId);
                if (user != null && user.getEmail() != null) {
                    String unit =
                            (String) this.redisOperator.get(RedisKeyEnum.PARAM_KV.value + ":" + messageVo.getParameter());
                    StringBuilder sb = new StringBuilder();
                    Device device = this.deviceService.queryDeviceById(deviceId);
                    sb.append("有设备发生异常！\n");
                    sb.append("设备所在IP：" + messageVo.getHost() + "\n");
                    sb.append("设备id：" + deviceId + "\n");
                    sb.append("设备名称：" + device.getDeviceName() + "\n");
                    sb.append("异常参数：" + messageVo.getParameter() + "\n");
                    sb.append("异常值：" + messageVo.getMsg() + unit + "\n");
                    sb.append("预设值上限：" + upValue + unit + "\n");
                    sb.append("预设值下限：" + downValue + unit + "\n");

                    this.emailService.send(user.getEmail(), sb.toString());
                }
            }

        }
    }
}
