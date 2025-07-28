package Proyecto_Modulo_JosueAyala.JosueAyala_20240001.Services;

import Proyecto_Modulo_JosueAyala.JosueAyala_20240001.Entities.ProveedorEntity;
import Proyecto_Modulo_JosueAyala.JosueAyala_20240001.Models.DTO.ProveedorDTO;
import Proyecto_Modulo_JosueAyala.JosueAyala_20240001.Repositories.ProveedorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository repoProveedor;

    //Este metodo nos devolvera todos los proveedores
    public List<ProveedorDTO> obtenerProveedores(){
        List<ProveedorEntity>proveedores = repoProveedor.findAll();
        return proveedores.stream().map(this::convertirProveedorDTO).collect(Collectors.toList());
    }

    //Este metodo nos permitira ingresar proveedores
    public ProveedorDTO ingresarProveedores(ProveedorDTO proveedorDTO){
        ProveedorEntity nuevoProveedor = convertirDTOaEntity(proveedorDTO);
        ProveedorEntity proveedorGuardado = repoProveedor.save(nuevoProveedor);
        return convertirProveedorDTO(proveedorGuardado);
    }

    //Este metodo nos permitira eliminar proveedores
    public boolean eliminarProveedor(Long id){
        if (repoProveedor.existsById(id)){
            repoProveedor.deleteById(id);
            return true;
        }else{
            log.error("No se pudo eliminar el proveedor con ID: "+ id);
            return false;
        }
    }

    //Este metodo nos permitira encontrar si existe un proveedor con el id que se ingrese
    public ProveedorDTO obtenerProveedorPorId(Long id) {
        return repoProveedor.findById(id)
                .map(this::convertirProveedorDTO)
                .orElse(null);
    }

    //Este metodo nos va permitir actualizar los proveedores
    public ProveedorDTO actualizarProveedor(Long id, ProveedorDTO proveedorDTO){
        Optional<ProveedorEntity> proveedorExiste = repoProveedor.findById(id);

        if (proveedorExiste.isPresent()){
            ProveedorEntity proveedor = proveedorExiste.get();
            proveedor.setProviderName(proveedorDTO.getProviderName());
            proveedor.setProviderPhone(proveedorDTO.getProviderPhone());
            proveedor.setProviderAddress(proveedorDTO.getProviderAddress());
            proveedor.setProviderEmail(proveedorDTO.getProviderEmail());
            proveedor.setProviderCode(proveedorDTO.getProviderCode());
            proveedor.setProviderComments(proveedorDTO.getProviderComments());
            ProveedorEntity proveedorActualizado = repoProveedor.save(proveedor);
            return convertirProveedorDTO(proveedorActualizado);
        }
        else {
            log.error("Proveedor no encontrado con ID"+ id);
            return null;
        }
    }

    //Este metodo nos va convertir el DTO a Entity
    private ProveedorEntity convertirDTOaEntity(ProveedorDTO proveedorDTO) {
        ProveedorEntity entity = new ProveedorEntity();
        entity.setId(proveedorDTO.getId());
        entity.setProviderName(proveedorDTO.getProviderName());
        entity.setProviderPhone(proveedorDTO.getProviderPhone());
        entity.setProviderAddress(proveedorDTO.getProviderAddress());
        entity.setProviderEmail(proveedorDTO.getProviderEmail());
        entity.setProviderCode(proveedorDTO.getProviderCode());
        entity.setProviderStatus(proveedorDTO.getProviderStatus());
        entity.setProviderComments(proveedorDTO.getProviderComments());
        return entity;
    }

    //Este metodo nos va a convertir la Entity a DTO
    private ProveedorDTO convertirProveedorDTO(ProveedorEntity proveedorEntity) {
        ProveedorDTO dto = new ProveedorDTO();

        dto.setId(proveedorEntity.getId());
        dto.setProviderName(proveedorEntity.getProviderName());
        dto.setProviderPhone(proveedorEntity.getProviderPhone());
        dto.setProviderAddress(proveedorEntity.getProviderAddress());
        dto.setProviderEmail(proveedorEntity.getProviderEmail());
        dto.setProviderCode(proveedorEntity.getProviderCode());
        dto.setProviderStatus(proveedorEntity.getProviderStatus());
        dto.setProviderComments(proveedorEntity.getProviderComments());
        return dto;

    }
}
