package com.dummies.config;

import com.dummies.filter.AuthFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

/**
 * @author taumal
 * @since 11/13/17 7:07 PM
 */
@Configuration
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static final String CHARACTER_ENCODING = "UTF-8";

    public WebInit() {
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters(){
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding(CHARACTER_ENCODING);
        encodingFilter.setForceEncoding(true);

        AuthFilter authFilter = new AuthFilter();

        return new Filter[] {encodingFilter};
    }

    @Override
    protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
        return super.registerServletFilter(servletContext, filter);
    }
}
