package com.team22.backend.Entity;
import lombok.*;

import javax.persistence.*;

@Entity  
@Data 
@Getter @Setter
@ToString
@EqualsAndHashCode
@Table(name="TypeReason") 
public class TypeReason {
    @Id  
    @SequenceGenerator(name="typereason_seq",sequenceName="typereason_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="typereason_seq")
    @Column(name="TypeReasonID",unique = true, nullable = false)
    private @NonNull Long typeReasonID;
    private @NonNull String typeReasonName;
    
    public TypeReason(String typeReasondb){
        this.typeReasonName = typeReasondb;
    }
}