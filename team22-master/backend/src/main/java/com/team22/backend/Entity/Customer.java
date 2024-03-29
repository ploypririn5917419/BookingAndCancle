package com.team22.backend.Entity;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Customer")
public class Customer { 
    @Id
    @SequenceGenerator(name="customer_seq",sequenceName="customer_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="customer_seq")
    @Column(name="Customer_ID",unique = true, nullable = false)
    private @NonNull Long cusId;
    private  String customerName;
    private String customerIDs;
    private String customerAddress;
    private String customerPassword;
    private String customerPhone;
    private String customerGender;
    private String customerBirthday;
}