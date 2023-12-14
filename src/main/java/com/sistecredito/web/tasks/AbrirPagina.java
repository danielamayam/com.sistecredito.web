package com.sistecredito.web.tasks;

import com.sistecredito.web.userinterfaces.UiPagina;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.sistecredito.web.userinterfaces.UiMenu.BTN_CERRAR_MODAL;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class AbrirPagina implements Task {
    private UiPagina uiPagina;

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn(uiPagina),
                WaitUntil.the(BTN_CERRAR_MODAL,  isClickable()).forNoMoreThan(120).seconds(),
                Click.on(BTN_CERRAR_MODAL)
        );
    }

    public static AbrirPagina urlWeb() {
        return Tasks.instrumented(AbrirPagina.class);
    }
}