package mx.uam.ayd.proyecto.negocio;
import mx.uam.ayd.proyecto.datos.PagoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Pago;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PagoServiceTest {
    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegistrarPagoValido() {
        Pago pago = new Pago();
        pago.setMonto(100.0f);
        pago.setCliente("Juan");

        when(pagoRepository.save(pago)).thenReturn(pago);

        boolean resultado = pagoService.registrarPago(pago);
        assertTrue(resultado);
        verify(pagoRepository).save(pago);
    }

    @Test
    public void testRegistrarPagoInvalido() {
        Pago pago = new Pago();
        pago.setMonto(0.0f);

        boolean resultado = pagoService.registrarPago(pago);
        assertFalse(resultado);
        verify(pagoRepository, never()).save(any());
    }

    @Test
    public void testBuscarPagoPorId() {
        Pago pago = new Pago();
        pago.setIdPago(10L);
        pago.setMonto(300.0f);

        when(pagoRepository.findById(10L)).thenReturn(Optional.of(pago));

        Optional<Pago> resultado = pagoService.buscarPagoPorId(10L);
        assertTrue(resultado.isPresent());
        assertEquals(300.0, resultado.get().getMonto());
    }

    @Test
    public void testListarPagos() {
        List<Pago> lista = List.of(new Pago(), new Pago());
        when(pagoRepository.findAll()).thenReturn(lista);

        Iterable<Pago> resultado = pagoService.listarPagos();
        assertNotNull(resultado);
        assertEquals(2, ((List<?>) resultado).size());
    }
}

