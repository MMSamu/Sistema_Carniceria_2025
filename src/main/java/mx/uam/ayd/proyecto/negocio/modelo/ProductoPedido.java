package mx.uam.ayd.proyecto.negocio.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa un producto que ha sido solicitado como parte de un pedido.
 * Contiene la información necesaria para identificar el producto, su precio, peso y su disponibilidad al momento de procesar el pedido.
 */

@Entity
@Getter
@Setter
@NoArgsConstructor

public class ProductoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    private String nombre;
    private String descripcion;
    private float precio;
    private boolean disponible;
    private float peso;

    /**
     * Actualiza el precio del producto en el pedido.
     * @param nuevoPrecio nuevo precio a establecer
     */

    public void actualizarPrecio(float nuevoPrecio) {

        this.precio = nuevoPrecio;

    }

    /**
     * Verifica si el producto está disponible para ser agregado al pedido.
     * @return true si el producto está disponible, false en caso contrario
     */

    public boolean verificarDisponibilidad() {

        return disponible;

    }

}
