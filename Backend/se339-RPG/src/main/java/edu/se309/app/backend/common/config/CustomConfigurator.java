package edu.se309.app.backend.common.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.socket.server.standard.ServerEndpointRegistration;

/**
 * Custom Configuration class
 */
public class CustomConfigurator extends ServerEndpointRegistration.Configurator implements ApplicationContextAware {

    private static volatile BeanFactory context;

    /**
     * Get the endpoint Instance context
     *
     * @param endpoint endpoint
     * @param <T>      type of enpoint
     * @return the context bean
     * @throws InstantiationException
     */
    @Override
    public <T> T getEndpointInstance(Class<T> endpoint) throws InstantiationException {
        return context.getBean(endpoint);
    }

    /**
     * Set Application Context
     *
     * @param applicationContext application context
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CustomConfigurator.context = applicationContext;
    }
}