package mx.uam.ayd.proyecto.presentacion.pago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.stage.Stage;
import mx.uam.ayd.proyecto.negocio.PagoService;

@Component
public class ControlPago {
    @Autowired
    private PagoService pagoService;

    // Puedes agregar métodos como iniciar la ventana, registrar pagos, etc.
    public void inicia() {
        System.out.println("ControlPago iniciado");
    }

}
