package yx.graduation.elec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
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

/*重要！如果你的项目引入junit测试，此处需要使用@WebAppConfiguration，如果没有使用junit使用@Configuration(很多的博客都没有注明这个问题，为此我花了非常多的时间解决问题)*/
@WebAppConfiguration
@EnableSwagger2//重要！
@EnableWebMvc
@ComponentScan(basePackages = "yx.graduation.elec.controller")//扫描control所在的package请修改为你control所在package
public class SwaggerConfig {
    @Bean
    public Docket api() {

        //添加head参数start
        ParameterBuilder usernameTokenPar = new ParameterBuilder();
        ParameterBuilder secretKeyTokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        usernameTokenPar.name("username").description("用户名").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        secretKeyTokenPar.name("secretKey").description("秘钥").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(usernameTokenPar.build());
        pars.add(secretKeyTokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("电力设备管理系统—api文档")        // 文档页标题
                .contact(new Contact("021640906杨雪",
                        "电气工程及其自动化",
                        "17743170521"))        // 联系人信息
                .version("1.0.1")   // 文档版本号
                .termsOfServiceUrl("http://elec.okay3r.top") // 网站地址
                .build();
    }
}