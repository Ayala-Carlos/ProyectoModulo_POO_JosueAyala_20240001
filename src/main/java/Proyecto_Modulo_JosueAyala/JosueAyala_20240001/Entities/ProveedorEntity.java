package Proyecto_Modulo_JosueAyala.JosueAyala_20240001.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Entidad que relaciona a la tabla TBPROVIDER
@Entity
@Getter @Setter @EqualsAndHashCode @ToString
@Table(name = "TBPROVIDER")
public class ProveedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROVIDER")
    @SequenceGenerator(name = "SEQ_PROVIDER", sequenceName = "SEQ_PROVIDER", allocationSize = 1)
    @Column(name = "PROVIDERID")
    private Long Id;

    @Column(name = "PROVIDERNAME", unique = true)
    private String providerName;

    @Column(name = "PROVIDERPHONE")
    private String providerPhone;

    @Column(name = "PROVIDERADDRESS")
    private String providerAddress;

    @Column(name = "PROVIDEREMAIL")
    private String providerEmail;

    @Column(name = "PROVIDERCODE", unique = true)
    private String ProviderCode;

    @Column(name = "PROVIDERSTATUS")
    private Long providerStatus;

    @Column(name = "PROVIDERCOMMENTS")
    private String providerComments;
}
