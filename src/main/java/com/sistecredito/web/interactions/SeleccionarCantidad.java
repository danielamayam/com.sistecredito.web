package com.sistecredito.web.interactions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.sistecredito.web.userinterfaces.UiPagina.BTN_INCREMENTAR;
import static com.sistecredito.web.userinterfaces.UiPagina.BTN_ITEM_INCREMENTAR;

public class SeleccionarCantidad implements Interaction {

    private int cantidad;

    public SeleccionarCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
                    actor.attemptsTo(
                            WaitUntil.the(BTN_INCREMENTAR, WebElementStateMatchers.isClickable()).forNoMoreThan(120).seconds(),
                            Click.on(BTN_INCREMENTAR),
                            Click.on(BTN_ITEM_INCREMENTAR.of(String.valueOf(cantidad)))
                    );
    }


    public static SeleccionarCantidad seleccionar(int cantidad) {
        return Instrumented.instanceOf(SeleccionarCantidad.class).withProperties(cantidad);
    }
}
