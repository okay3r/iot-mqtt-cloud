package yx.graduation.elec.service;

import yx.graduation.elec.pojo.Parameter;
import yx.graduation.utils.ApiJsonResult;

import java.util.List;

public interface ParameterService {
    ApiJsonResult createParameter(String paramName, String unit);

    List<Parameter> queryAll();
}
