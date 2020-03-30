package elec.cloud.entity;

/***
 * select d.*,
 *        c.*
 * from device d
 *          inner join user u on d.user_id = u.id
 *          left join category c on d.category_id = c.id
 * where u.id = #{id}
 *
 * 根据userID查询设备、类别
 */

public class DeviceCategoryVo {
    private Integer userId;
    private Device device;
    private Category category;

    public DeviceCategoryVo() {
    }

    public DeviceCategoryVo(Integer userId, Device device, Category category) {
        this.userId = userId;
        this.device = device;
        this.category = category;
    }

    @Override
    public String toString() {
        return "DeviceCategoryVo{" +
                "userId=" + userId +
                ", device=" + device +
                ", category=" + category +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
