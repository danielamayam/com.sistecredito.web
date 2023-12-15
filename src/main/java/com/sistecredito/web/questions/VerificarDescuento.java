package com.sistecredito.web.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import static com.sistecredito.web.userinterfaces.UiPagina.LBL_DESCUENTO;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class VerificarDescuento implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {
        DecimalFormat format = new DecimalFormat("###,###");
        boolean resultado = false;
        double subTotal = 0.0;
        List<Map<String, Object>> nuevaTabla = theActorInTheSpotlight().recall("tabladatos");
        for (Map<String, Object> datosCarrito : nuevaTabla) {
            int cantidad = (int) datosCarrito.get("cantidad");
            double descuento = Double.parseDouble((String) datosCarrito.get("descuento"));
            double precio = Double.parseDouble((String) datosCarrito.get("precio"));
            double total = (descuento > 0) ? (precio * (descuento / 100.0)) * cantidad : 0;
            subTotal += total;
        }
        String totalWeb = LBL_DESCUENTO.resolveFor(actor).getText().replace("- $", "").replace(".", "").replace(",", "").trim();
        String totalGeneralString = String.valueOf(format.format(subTotal)).replace(".", "").replace(",", "");
        resultado = totalWeb.contains(totalGeneralString);
        if (!resultado) {
            throw new AssertionError("El descuento del carrito no es igual a la seleccionada\n" +
                    "Cantidad en el carrito Web: " + totalWeb + "\n" +
                    "Cantidad en la lista: " + totalGeneralString);
        }
        return resultado;
    }

    public static VerificarDescuento on(){
        return new VerificarDescuento();
    }
}
