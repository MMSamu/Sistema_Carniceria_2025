package mx.uam.ayd.proyecto.presentacion.editarCarrito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import mx.uam.ayd.proyecto.negocio.modelo.ProductoPedido;

public class VistaCarrito extends JFrame {

    private final ControlCarrito control;
    private final JTextArea areaProductos;
    private final JTextField campoNota;
    private final JLabel etiquetaTotal;

    public VistaCarrito(ControlCarrito control) {
        this.control = control;

        setTitle("Carrito de Compras");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());

        // Área para mostrar productos
        areaProductos = new JTextArea(10, 40);
        areaProductos.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaProductos);
        panel.add(scroll, BorderLayout.CENTER);

        // Campo para nota
        JPanel panelNota = new JPanel(new BorderLayout());
        campoNota = new JTextField();
        JButton btnGuardarNota = new JButton("Guardar Nota");
        btnGuardarNota.addActionListener(e -> control.agregarNota(campoNota.getText()));
        panelNota.add(new JLabel("Nota: "), BorderLayout.WEST);
        panelNota.add(campoNota, BorderLayout.CENTER);
        panelNota.add(btnGuardarNota, BorderLayout.EAST);
        panel.add(panelNota, BorderLayout.SOUTH);

        // Total
        etiquetaTotal = new JLabel("Total: $0.00");
        panel.add(etiquetaTotal, BorderLayout.NORTH);

        add(panel);
        mostrarProductos();
    }

    public void mostrarProductos() {
        List<ProductoPedido> productos = control.obtenerCarrito();
        StringBuilder texto = new StringBuilder();

        for (ProductoPedido p : productos) {
            texto.append(String.format("• %s - %.2f kg x $%.2f = $%.2f\n",
                    p.getNombre(), p.getPeso(), p.getPrecio(), p.calcularSubtotal()));
        }

        areaProductos.setText(texto.toString());
        etiquetaTotal.setText(String.format("Total: $%.2f", control.calcularTotal()));
    }
}