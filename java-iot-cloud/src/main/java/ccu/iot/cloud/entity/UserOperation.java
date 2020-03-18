package ccu.iot.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class UserOperation {

    @TableId(type= IdType.AUTO)
    private Long id;

    private String op;

    private String address;

    private Date time;
}
