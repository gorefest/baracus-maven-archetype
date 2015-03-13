#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.context;

import org.baracus.context.BaracusApplicationContext;
import ${package}.dao.CustomerDao;
import ${package}.service.ConfigurationService;
import ${package}.service.CustomerDataService;
import ${package}.service.impl.CustomerDataServiceImpl;

/**
 * The application context. Mostly used for initializing the bean context
 */
public class ApplicationContext extends BaracusApplicationContext {

    // Here the application configuration is constructed
    static {
        // This one is mandatory since the open helper carries the information for the app database
        registerBeanClass(OpenHelper.class);

        // These are simple bean registrations
        registerBeanClass(ConfigurationService.class);
        registerBeanClass(CustomerDao.class);

        // This is a Interface/Implmementation style registration
        registerBeanClass(CustomerDataService.class, CustomerDataServiceImpl.class);

        // ...
        // register your beans here
        // ...


        // finally, we set a Post-Appcontext-Init hook
        setApplicationContextInitializer(new ApplicationInitializerCallback());


    }

}
