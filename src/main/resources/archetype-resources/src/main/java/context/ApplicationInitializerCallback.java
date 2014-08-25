#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.context;

import net.mantucon.baracus.annotations.Bean;
import net.mantucon.baracus.lifecycle.ApplicationContextInitializer;
import net.mantucon.baracus.util.Logger;
import ${package}.service.ConfigurationService;
import ${package}.service.CustomerDataService;

/**
 * Created by marcus on 16.07.14.
 */
public class ApplicationInitializerCallback implements ApplicationContextInitializer {

    Logger logger = new Logger(ApplicationInitializerCallback.class);

    @Bean
    CustomerDataService customerDataService;

    @Bean
    ConfigurationService configurationService;

    @Override
    public void afterContextIsBuilt() {
        if (!configurationService.isApplicationInitializationDone()) {
            logger.info("Application data is not initialized. Will init now");
            customerDataService.initializeApplicationData();
            configurationService.setApplicationInitializationDone(true);
        } else {
            logger.info("Application is initialized. Nothing to be done.");
        }
    }
}
