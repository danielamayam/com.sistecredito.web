package com.sistecredito.web.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import static com.sistecredito.web.userinterfaces.UiPagina.LBL_SUBTOTAL;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class VerificarSubTotal implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {
        DecimalFormat format = new DecimalFormat("###,###");
        boolean resultado = false;
        double totalGeneral = 0.0;
        List<Map<String, Object>> nuevaTabla = theActorInTheSpotlight().recall("tabladatos");
        for (Map<String, Object> datosCarrito : nuevaTabla) {
            int cantidad = (int) datosCarrito.get("cantidad");
            double precio = Double.parseDouble((String) datosCarrito.get("precio"));
            double totalPorItem = precio * cantidad;
            totalGeneral += totalPorItem;
        }
        String total = LBL_SUBTOTAL.resolveFor(actor).getText().replace("$", "").replace(".", "").replace(",", "").trim();
        String totalGeneralString = String.valueOf(format.format(totalGeneral)).replace(".", "").replace(",", "");
        resultado = total.contains(totalGeneralString);

        if (!resultado) {
            throw new AssertionError("El subtotal del carrito no es igual a la seleccionada\n" +
                    "Cantidad en el carrito Web: " + total + "\n" +
                    "Cantidad en la lista: " + totalGeneralString);
        }
        return resultado;
    }

    public static VerificarSubTotal on() {
        return new VerificarSubTotal();
    }
}
