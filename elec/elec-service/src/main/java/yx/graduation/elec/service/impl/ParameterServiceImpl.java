package yx.graduation.elec.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import yx.graduation.elec.mapper.ParameterMapper;
import yx.graduation.elec.pojo.Parameter;
import yx.graduation.elec.service.ParameterService;
import yx.graduation.utils.ApiJsonResult;

import java.util.List;

@Service
public class ParameterServiceImpl implements ParameterService {

    @Autowired
    private ParameterMapper parameterMapper;

    /**
     * 添加参数
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ApiJsonResult createParameter(String paramName, String unit) {
        Parameter parameter = new Parameter();
        paramName = StringUtils.lowerCase(paramName);
        parameter.setParameterName(paramName);
        if (this.parameterMapper.selectOne(parameter) != null) {
            return ApiJsonResult.errorMsg("参数名冲突");
        }
        parameter.setParameterUnit(unit);
        int res = this.parameterMapper.insert(parameter);
        if (res != 1) {
            return ApiJsonResult.errorMsg("添加失败");
        }
        return ApiJsonResult.ok(parameter);
    }

    /**
     * 查询全部参数
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Parameter> queryAll() {
        return this.parameterMapper.selectAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Parameter> queryParameterSelective(Parameter parameter) {
        return this.parameterMapper.select(parameter);
    }
}
