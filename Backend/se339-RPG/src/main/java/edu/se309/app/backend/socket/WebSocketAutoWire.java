package edu.se309.app.backend.socket;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Shared context for the web socket
 */
@Component
public class WebSocketAutoWire implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * Returns the ApplicationContext
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * Used by Spring Boot and shouldn't be called by any other source
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * Is used to get the stored Spring bean when autowiring such in isn't feasible
     *
     * @param beanClass The class of the requested spring bean
     * @param <T>       The object type of the request spring bean
     * @return the instance of the spring bean
     */
    public static <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    }
}
