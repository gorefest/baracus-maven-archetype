#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao;

import android.content.ContentValues;
import android.database.Cursor;
import org.baracus.annotations.Bean;
import org.baracus.dao.BaseDao;
import org.baracus.orm.Field;
import org.baracus.orm.FieldList;
import ${package}.model.Customer;

import java.util.Date;

import static org.baracus.orm.ModelBase.idCol;
import static ${package}.model.Customer.*;

/**
 * Implementation of a sample DAO
 */
@Bean
public class CustomerDao extends BaseDao<Customer> {

    /**
     *
     */
    public CustomerDao() {
        super(Customer.class);
    }

    private final RowMapper<Customer> rowMapper = new RowMapper<Customer>() {
        @Override
        public Customer from(Cursor c) {
            Customer result = new Customer();

            final Long id = c.getLong(idCol.fieldIndex);
            result.setId(id);
            result.setLastName(c.getString(lastNameCol.fieldIndex));
            result.setFirstName(c.getString(firstNameCol.fieldIndex));
            result.setCreationDate(new Date(c.getLong(createDateCol.fieldIndex)));
            result.setLastModificationDate(new Date(c.getLong(lastModifiedCol.fieldIndex)));
            result.setTransient(false);

            return result;
        }

        @Override
        public String getAffectedTable() {
            return Customer.TABLE_CUSTOMER;
        }

        @Override
        public FieldList getFieldList() {
            return Customer.fieldList;
        }

        @Override
        public Field getNameField() {
            return Customer.lastNameCol;
        }

        @Override
        public ContentValues getContentValues(Customer customer) {
            ContentValues result = new ContentValues();
            if (customer.getId() != null) { result.put(idCol.fieldName, customer.getId()); }
            if (customer.getLastName() != null) { result.put(lastNameCol.fieldName, customer.getLastName()); }
            if (customer.getFirstName() != null) { result.put(firstNameCol.fieldName, customer.getFirstName()); }
            if (customer.getLastModificationDate() != null) { result.put(lastModifiedCol.fieldName, customer.getLastModificationDate().getTime());}
            if (customer.getCreationDate() != null) { result.put(createDateCol.fieldName, customer.getCreationDate().getTime());}
            return result;
        }
    };

    @Override
    public RowMapper<Customer> getRowMapper() {
        return rowMapper;
    }
}
