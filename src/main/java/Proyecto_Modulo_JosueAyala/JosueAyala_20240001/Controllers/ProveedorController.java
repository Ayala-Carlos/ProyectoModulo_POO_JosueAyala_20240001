package Proyecto_Modulo_JosueAyala.JosueAyala_20240001.Controllers;

import Proyecto_Modulo_JosueAyala.JosueAyala_20240001.Models.DTO.ProveedorDTO;
import Proyecto_Modulo_JosueAyala.JosueAyala_20240001.Services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ApiProveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService acceso;

    //Metodo para obtener los proveedores
    @GetMapping("/getProveedores")
    public List<ProveedorDTO>obtenerProveedores(){
        return acceso.obtenerProveedores();
    }

    //Metodo para obtener proveedor por ID
    @GetMapping("/getProveedor/{id}")
    public ResponseEntity<Map<String, Object>> obtenerProveedorPorId(@PathVariable Long id) {
        Map<String, Object> body = new HashMap<>();
        try {
            ProveedorDTO proveedor = acceso.obtenerProveedorPorId(id);
            HttpStatus status = (proveedor != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
            body.put("codigo", status.value());
            body.put("mensaje", (proveedor != null) ? "Proveedor encontrado correctamente" : "Proveedor no encontrado");
            return new ResponseEntity<>(body, status);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of(
                    "codigo", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "mensaje", "Error al buscar el proveedor"
            ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Metodo para ingresar proveedor
    @PostMapping("/ingresarPro")
    public ResponseEntity<ProveedorDTO>ingresarProveedores(@RequestBody ProveedorDTO proveedorDTO){
        try{
            ProveedorDTO nuevo = acceso.ingresarProveedores(proveedorDTO);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //Metodo para actualizar proveedor
    @PutMapping("/actualizarPro/{id}")
    public ResponseEntity<ProveedorDTO>actualizarProveedor(@PathVariable Long id, @RequestBody ProveedorDTO proveedorDTO){
        try{
            ProveedorDTO proveedorActualizado = acceso.actualizarProveedor(id, proveedorDTO);
            if (proveedorActualizado != null){
                return  new ResponseEntity<>(proveedorActualizado, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Metodo para eliminar proveedor
    @DeleteMapping("/eliminarPro/{id}")
    public ResponseEntity<String>eliminarProveedor(@PathVariable Long id){
        try{
            boolean eliminado = acceso.eliminarProveedor(id);
            if (eliminado){
                return new ResponseEntity<>("Proveedor Eliminado", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Proveedor no encontrado", HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error al eliminar el proveedor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
