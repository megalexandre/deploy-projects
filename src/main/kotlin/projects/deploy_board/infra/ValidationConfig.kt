package projects.deploy_board.infra

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

@Configuration
class ValidationConfig {

    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasename("classpath:messages")
        messageSource.setDefaultEncoding("UTF-8")
        messageSource.setUseCodeAsDefaultMessage(false)
        return messageSource
    }

    @Bean
    fun validator(messageSource: MessageSource): LocalValidatorFactoryBean {
        val bean = LocalValidatorFactoryBean()
        //bean.setValidationMessageSource(messageSource)
        return bean
    }
}
