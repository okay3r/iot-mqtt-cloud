package ccuiot.iotc.service.impl;

import ccuiot.iotc.utils.PagedGridResult;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseService {

    @Value("${emqx.api.appId}")
    public String appId;

    @Value("${emqx.api.pwd}")
    public String pwd;

    @Value("${emqx.api.baseUrl}")
    public String baseUrl;

    @Value("${emqx.api.node}")
    public String node;

    //http请求头
    public Map<String, String> headers = new HashMap<>();

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    //http秘钥
    public String key;

    @PostConstruct
    public void init() {
        key = Base64.getEncoder().encodeToString((appId + ":" + pwd).getBytes());
        key = "Basic " + key;
        headers.put("Authorization", key);
    }

    public PagedGridResult setPagedGrid(List<?> list, Integer page) {
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        grid.setPage(page);
        grid.setRows(list);
        grid.setTotal(pageList.getPages());
        grid.setRecords(pageList.getTotal());
        return grid;
    }

}
