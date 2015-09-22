package org.thymeleaf.spring4.view;

import java.io.File;
import java.util.Locale;

import org.springframework.web.servlet.View;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * <p>User: jaylee
 * <p>Date: 2015-09-17 上午10:09
 * <p>Version: 1.0
 */
public class ThymeleafViewResolverEx extends ThymeleafViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {

        ServletContextTemplateResolver servletContextTemplateResolver = (ServletContextTemplateResolver) this.getWebApplicationContext().getBean("templateResolver");
        servletContextTemplateResolver.initialize();
        String prefix = servletContextTemplateResolver.getPrefix().substring(1);
        String suffix = servletContextTemplateResolver.getSuffix();

        //String str = getClass().getResource("/").toString().replace("file:/", "").replace("/WEB-INF/classes/", "");
        //log.info("*****************************" + str);
        System.out.println("-----------------------------" + this.getServletContext().getRealPath("/") + "/" + prefix + viewName + suffix);
        File file = new File(this.getServletContext().getRealPath("/") + "/" + prefix + viewName + suffix);
        if (!file.exists()) {
            return null;
        }
        return super.resolveViewName(viewName, locale);
    }
}
