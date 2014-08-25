#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import net.mantucon.baracus.annotations.Bean;
import net.mantucon.baracus.util.Logger;
import ${package}.dao.CustomerDao;
import ${package}.model.Customer;
import ${package}.service.CustomerDataService;

/**
 * Created by marcus on 16.07.14.
 */
@Bean
public class CustomerDataServiceImpl implements CustomerDataService {

    final static Logger logger = new Logger(CustomerDataService.class); // create a logger

    @Bean
    CustomerDao customerDao; // Inject bean by type

    @Override
    public void initializeApplicationData() {

        logger.info("Begin initializing the data.");

        customerDao.save(new Customer("Meyer","John"));
        customerDao.save(new Customer("Harris","Frank"));
        customerDao.save(new Customer("Wu", "Elias"));
        customerDao.save(new Customer("Hattar","Muhammad"));
        customerDao.save(new Customer("Froehlich","Heinz"));
        customerDao.save(new Customer("Frosteau","Jaques"));
        customerDao.save(new Customer("Mugambe","Joseph"));
        customerDao.save(new Customer("Richter", "Franz"));
        customerDao.save(new Customer("Johnson","Jessica"));
        customerDao.save(new Customer("Pot","Fullerton"));

        logger.info("Done initializing the data. ${symbol_dollar}1 customers written to database.", customerDao.loadAll().size());

    }
}
