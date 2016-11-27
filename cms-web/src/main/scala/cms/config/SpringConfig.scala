package cms.config

import cms.spring.{HtmlViewResolver, ScalaObjectArgumentResolver}
import cms.view.Posts
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.orm.jpa.EntityScan
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.jpa.repository.config.{EnableJpaAuditing, EnableJpaRepositories}
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.{ViewResolverRegistry, WebMvcConfigurerAdapter}
import org.springframework.web.servlet.view.{BeanNameViewResolver, InternalResourceViewResolver}

@SpringBootApplication(scanBasePackages = Array("cms"))
@EnableJpaRepositories(basePackages = Array("cms.repository"))
@EntityScan(basePackages=Array("cms.domain"))
@EnableJpaAuditing
@Configuration
//@Import(value = Array(classOf[WebMvcConfig], classOf[WebSecurityConfig]))
class WebMvcConfig extends WebMvcConfigurerAdapter{
    @Bean
    def jacksonObjectMapper() = {
        val mapper = new ObjectMapper() with ScalaObjectMapper
        mapper.registerModule(DefaultScalaModule)
        mapper
    }

    override def addArgumentResolvers(argumentResolvers: java.util.List[HandlerMethodArgumentResolver]) {
        argumentResolvers.add(new ScalaObjectArgumentResolver())
    }
}

//@EnableWebSecurity
//@Configuration
//class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//
//    @Bean
//    def shaPasswordEncoder = new ShaPasswordEncoder(512)
//
//    @Bean
//    def sltSource = {
//        val reflectionSaltSource = new ReflectionSaltSource()
//        reflectionSaltSource.setUserPropertyToUse("salt")
//        reflectionSaltSource
//    }
//
//    override def configure(http:HttpSecurity){
//        http
//            .formLogin()
//                .loginPage("/login").permitAll()
//                .loginProcessingUrl("/login").permitAll()
//                .passwordParameter("password")
//                .usernameParameter("id")
//            .and()
//                .logout()
//            .and()
//                .authorizeRequests()
//                .antMatchers("/**").access("hasAuthority('ROLE_USER')")
//    }
//
//}

object SpringConfig extends App{
    SpringApplication.run(classOf[WebMvcConfig], args: _*)
}
