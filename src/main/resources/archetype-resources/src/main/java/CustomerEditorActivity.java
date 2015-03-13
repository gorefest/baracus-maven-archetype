#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import org.baracus.annotations.Bean;
import org.baracus.context.ManagedActivity;
import ${package}.dao.CustomerDao;
import ${package}.model.Customer;

/**
 * Created by marcus on 21.08.14.
 */
@Bean
public class CustomerEditorActivity extends ManagedActivity {

    @Bean
    CustomerDao customerDao;

    Customer customer;

    EditText customerFirstName;
    EditText customerLastName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the customer editor as our content view
        setContentView(R.layout.customer_editor);

        // validate the form on each focus change automatically
        this.enableFocusChangeBasedValidation();

        // set the widgets
        customerFirstName = (EditText) findViewById(R.id.customerFirstNameEdit);
        customerLastName = (EditText) findViewById(R.id.customerLastNameEdit);

        // if a customer ID passed, load the customer from database and push the data to the frontend
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Long customerId = extras.getLong(Customer.idCol.fieldName);
            customer = customerDao.getById(customerId);
            if (customer != null) {
                customerFirstName.setText(customer.getFirstName());
                customerLastName.setText(customer.getLastName());
            }
        } else {
            // otherwise : new customer
            customer = new Customer();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        validate();
    }

    public void onExitClicked(View view) {

        if (validate()){
            customer.setLastName(customerLastName.getText().toString());
            customer.setFirstName(customerFirstName.getText().toString());
            customerDao.save(customer);
            finish();
        }

        // the button only works when data is correct.
    }
}