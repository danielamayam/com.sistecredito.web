package com.sistecredito.web.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;
import static com.sistecredito.web.userinterfaces.UiPagina.TXT_CANTIDAD;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class VerificarCantidadUnitaria  implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {
        List<Map<String, Object>> nuevaTabla = theActorInTheSpotlight().recall("tabladatos");
        boolean resultado = false;
        for (Map<String, Object> datosCarrito : nuevaTabla){
            List<String> nombres = (List<String>) datosCarrito.get("nombre");
            int cantidad = (int) datosCarrito.get("cantidad");
            int cantidadPagina = Integer.parseInt(TXT_CANTIDAD.of(nombres.get(0)).resolveFor(actor).getValue());
            resultado = cantidad == cantidadPagina;
            if (!resultado){
                throw new AssertionError("La cantidad de productos en el carrito no es igual a la seleccionada\n" +
                        "Cantidad en el carrito: " + cantidadPagina + "\n" +
                        "Cantidad seleccionada: " + cantidad);
            }
        }
            return resultado;
    }

    public static VerificarCantidadUnitaria on(){
        return new VerificarCantidadUnitaria();
    }
}
