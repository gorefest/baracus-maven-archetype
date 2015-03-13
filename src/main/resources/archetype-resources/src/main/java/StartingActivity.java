#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import org.baracus.annotations.Bean;
import org.baracus.context.ManagedActivity;
import org.baracus.util.Logger;
import ${package}.dao.CustomerDao;
import ${package}.model.Customer;

import java.util.List;

@Bean
public class StartingActivity extends ManagedActivity { // ManagedActivity will let injected components
                                                        // survive after an internal reinstatiation (e.g. device rotate)

    @Bean
    CustomerDao customerDao;    // Type based injection

    CustomerListItemAdapter customerListItemAdapter;

    static {
        Logger.setTag("SMPAPP"); // This is going to be the logtag of your application
    }

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void loadAll() {
        final ListView listView = (ListView) findViewById(R.id.customerTable);

        listView.setAdapter(null);
        customerListItemAdapter = new CustomerListItemAdapter(this,R.layout.customer_list_row);
        listView.setAdapter(customerListItemAdapter);

        final List<Customer> allCustomers = customerDao.loadAll();
        for (Customer customer : allCustomers) {
            customerListItemAdapter.add(customer);
        }

        customerListItemAdapter.add(new Customer());
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        loadAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(${package}.R.menu.main, menu);
	return true;
    }

}

