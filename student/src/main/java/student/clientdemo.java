package student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.cfg.Configuration;
import java.util.List;


public class clientdemo {


	    public static void main(String[] args) {
	        // Create session factory
	        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Customer.class).buildSessionFactory();

	        // Open session
	        Session session = factory.openSession();

	        try {
	            // 1. Insert records manually
	            insertCustomer(session);

	            // 2. Apply restrictions using Criteria Interface
	            applyCriteriaRestrictions(session);

	        } finally {
	            session.close();
	            factory.close();
	        }
	    }

	    // Method to insert a customer record
	    public static void insertCustomer(Session session) {
	        // Create a new Customer object
	        Customer customer = new Customer("John Doe", "john.doe@example.com", 30, "New York");

	        // Begin transaction
	        Transaction transaction = session.beginTransaction();
	        session.save(customer); // Save the customer
	        transaction.commit();
	    }

	    // Method to apply Criteria restrictions
	    public static void applyCriteriaRestrictions(Session session) {
	        // Create Criteria object
	        Criteria criteria = session.createCriteria(Customer.class);

	        // Apply restrictions
	        criteria.add(Restrictions.eq("location", "New York")); // equal
	        List<Customer> customerList = criteria.list();

	        System.out.println("Customers from New York:");
	        for (Customer customer : customerList) {
	            System.out.println(customer.getName());
	        }

	        // Apply other restrictions
	        criteria = session.createCriteria(Customer.class);
	        criteria.add(Restrictions.ne("age", 30)); // not equal
	        customerList = criteria.list();

	        System.out.println("\nCustomers not aged 30:");
	        for (Customer customer : customerList) {
	            System.out.println(customer.getName());
	        }

	        criteria = session.createCriteria(Customer.class);
	        criteria.add(Restrictions.gt("age", 25)); // greater than
	        customerList = criteria.list();

	        System.out.println("\nCustomers aged greater than 25:");
	        for (Customer customer : customerList) {
	            System.out.println(customer.getName());
	        }

	        criteria = session.createCriteria(Customer.class);
	        criteria.add(Restrictions.lt("age", 40)); // less than
	        customerList = criteria.list();

	        System.out.println("\nCustomers aged less than 40:");
	        for (Customer customer : customerList) {
	            System.out.println(customer.getName());
	        }

	        criteria = session.createCriteria(Customer.class);
	        criteria.add(Restrictions.between("age", 25, 35)); // between
	        customerList = criteria.list();

	        System.out.println("\nCustomers aged between 25 and 35:");
	        for (Customer customer : customerList) {
	            System.out.println(customer.getName());
	        }
	    }
	}

}
