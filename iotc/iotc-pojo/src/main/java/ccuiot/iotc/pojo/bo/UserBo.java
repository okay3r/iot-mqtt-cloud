package ccuiot.iotc.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户对象BO",description = "客户端传入的数据封装在此entity中")
public class UserBo {
    @ApiModelProperty(value = "用户名", name = "username", example = "mike", required = true)
    private String username;
    @ApiModelProperty(value = "密码", name = "password", example = "123123", required = true)
    private String password;
    @ApiModelProperty(value = "邮箱", name = "email", example = "1112223@qq.com", required = true)
    private String email;
    @ApiModelProperty(value = "手机号", name = "phone", example = "13599887890", required = true)
    private String phone;
    @ApiModelProperty(value = "备注", name = "remark", example = "无", required = false)
    private String remark;
}
