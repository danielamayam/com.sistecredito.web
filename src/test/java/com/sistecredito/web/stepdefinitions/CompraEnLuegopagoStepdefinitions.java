package com.sistecredito.web.stepdefinitions;

import com.sistecredito.web.questions.VerificarCantidadUnitaria;
import com.sistecredito.web.questions.VerificarDescuento;
import com.sistecredito.web.questions.VerificarSubTotal;
import com.sistecredito.web.questions.VerificarTotal;
import com.sistecredito.web.tasks.AbrirPagina;
import com.sistecredito.web.tasks.SeleccionarProductos;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CompraEnLuegopagoStepdefinitions {

    @Given("que {string} se encuentra en la pagina web")
    public void navegueHastaLaWeb(String actor) {
        OnStage.theActorCalled(actor).wasAbleTo(
                AbrirPagina.urlWeb()
        );
    }
    @When("selecciono {int} productos aleatoriamente")
    public void seleccionDeProductos(Integer cantidad) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SeleccionarProductos.on(cantidad)
        );
    }
    @Then("válido los productos del carrito")
    public void validacionDelScenario() {
        theActorInTheSpotlight().should(
                seeThat(VerificarCantidadUnitaria.on())
        );
        theActorInTheSpotlight().should(
                seeThat(VerificarSubTotal.on())
        );
        theActorInTheSpotlight().should(
                seeThat(VerificarDescuento.on())
        );

        theActorInTheSpotlight().should(
                seeThat(VerificarTotal.on())
        );
    }

}
