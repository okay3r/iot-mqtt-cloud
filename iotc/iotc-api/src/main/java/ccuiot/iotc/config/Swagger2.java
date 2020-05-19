package ccuiot.iotc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * API文档生成
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

//    http://localhost:8088/swagger-ui.html     原路径
//    http://localhost:8088/doc.html     原路径

    // 配置swagger2核心配置 docket
    @Bean
    public Docket createRestApi() {
        //添加head参数start
        ParameterBuilder cacheKeyParm = new ParameterBuilder();
        ParameterBuilder secretKeyParm = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        cacheKeyParm.name("username").description("用户名").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        secretKeyParm.name("secretKey").description("秘钥").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(cacheKeyParm.build());
        pars.add(secretKeyParm.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("物联网云平台—接口api")        // 文档页标题
                .contact(new Contact("021641128王大瑞",
                        "https://www.ccuiot.cn",
                        "764893776@qq.com"))        // 联系人信息
                .description("物联网工程D212实验室")  // 详细信息
                .version("1.0.1")   // 文档版本号
                .termsOfServiceUrl("https://www.ccuiot.cn") // 网站地址
                .build();
    }

}
