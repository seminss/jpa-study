package jpql;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

}
