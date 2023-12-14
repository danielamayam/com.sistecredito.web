package com.sistecredito.web.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import static com.sistecredito.web.userinterfaces.UiPagina.LBL_DESCUENTO;
import static com.sistecredito.web.userinterfaces.UiPagina.LBL_TOTAL;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class VerificarTotal implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {
        DecimalFormat format = new DecimalFormat("###,###");
        boolean resultado = false;
        double subTotal = 0.0, totalDes = 0.0;
        List<Map<String, Object>> nuevaTabla = theActorInTheSpotlight().recall("tabladatos");
        for (Map<String, Object> datosCarrito : nuevaTabla) {
            int cantidad = (int) datosCarrito.get("cantidad");
            double precioDes = Double.parseDouble((String) datosCarrito.get("precioDes"));
            double precio = Double.parseDouble((String) datosCarrito.get("precioDes"));
            double total = precio * cantidad;
            double desc = precioDes * cantidad;
            subTotal += total;
            totalDes += desc;
        }
        String totalWeb = LBL_TOTAL.resolveFor(actor).getText().replace("- $", "").replace(".", "").replace(",", "").trim();
        String totalGeneralString = String.valueOf(format.format(subTotal - (subTotal - totalDes))).replace(".", "").replace(",", "");
        resultado = totalWeb.contains(totalGeneralString);
        if (!resultado) {
            throw new AssertionError("El total del carrito no es igual a la seleccionada\n" +
                    "Cantidad en el carrito Web: " + totalWeb + "\n" +
                    "Cantidad en la lista: " + totalGeneralString);
        }
        return resultado;
    }

    public static VerificarTotal on(){
        return new VerificarTotal();
    }
}
