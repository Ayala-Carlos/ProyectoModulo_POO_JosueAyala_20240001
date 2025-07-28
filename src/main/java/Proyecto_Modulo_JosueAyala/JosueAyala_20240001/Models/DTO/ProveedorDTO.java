package Proyecto_Modulo_JosueAyala.JosueAyala_20240001.Models.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

//DTO del la tabla TBPROVIDER
@Getter @Setter @EqualsAndHashCode @ToString
public class ProveedorDTO {

    private Long id;

    @NotNull(message = "El campo nombre no puede quedar vacio")
    private String providerName;

    private String providerPhone;

    private String providerAddress;

    private String providerEmail;

    private String providerCode;

    @NotNull(message = "El campo estatus no puede quedar vacio")
    private Long providerStatus;

    private String providerComments;
}
