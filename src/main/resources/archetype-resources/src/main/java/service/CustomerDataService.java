#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

/**
 * Created by marcus on 17.07.14.
 *
 * Simple Demonstration for using the interface/implementation approach
 * with BARACUS.
 *
 */
public interface CustomerDataService {

    /**
     * initialize all customer data
     */
    void initializeApplicationData();
}
