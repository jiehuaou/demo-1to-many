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

@NamedNativeQueries({
        @NamedNativeQuery(name = "Customer.totalCustomersByType",
                resultClass = CustomerTypeCount.class,
                resultSetMapping ="customerTypeMapping",
                query = "SELECT c.customer_type, COUNT(c.customer_type) AS count FROM Customer AS c GROUP BY c.customer_type"
        ),
        @NamedNativeQuery(name = "Customer.totalCustomersByType2",
                resultClass = Object[].class,
                resultSetMapping ="MultiplePojoMapping",
                query = "SELECT c.customer_type, COUNT(c.customer_type) AS customer_count FROM Customer AS c GROUP BY c.customer_type"
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name="customerTypeMapping",
                classes = @ConstructorResult(
                        targetClass = CustomerTypeCount.class,
                        columns = {
                                @ColumnResult(name = "customer_type", type = String.class),
                                @ColumnResult(name = "count", type = Long.class)
                        })),
        @SqlResultSetMapping(
                name = "MultiplePojoMapping",
                classes = {
                        @ConstructorResult(targetClass = CustomerType.class, columns = @ColumnResult(name="customer_type", type = String.class)),
                        @ConstructorResult(targetClass = CustomerCount.class, columns = @ColumnResult(name="customer_count", type = Long.class))
                }
        )
})




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
