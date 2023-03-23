package com.example.demo.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 *  demo to map the result of the queries into entities or objects
 */
@Entity
@NoArgsConstructor
@Data
@SqlResultSetMapping(
        name="customerTypeMapping",
        classes = @ConstructorResult(
                targetClass = CustomerTypeDTO.class,
                columns = {
                        @ColumnResult(name = "customer_type", type = String.class),
                        @ColumnResult(name = "count", type = long.class)
                }))
@NamedNativeQuery(name = "Customer.totalCustomersByType",
        resultClass = CustomerTypeDTO.class,
        resultSetMapping ="customerTypeMapping",
        query = "SELECT c.customer_type, COUNT(c.customer_type) AS count FROM Customer AS c GROUP BY c.customer_type"
)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String customerType;

    public Customer(String name, String customerType) {
        this.name = name;
        this.customerType = customerType;
    }
}
