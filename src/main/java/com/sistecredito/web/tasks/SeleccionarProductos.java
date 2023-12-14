package com.sistecredito.web.tasks;

import com.sistecredito.web.interactions.NavegarMenu;
import com.sistecredito.web.interactions.ScrollElement;
import com.sistecredito.web.interactions.SeleccionarCantidad;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.*;
import static com.sistecredito.web.userinterfaces.UiPagina.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SeleccionarProductos implements Task {

    private int cantidad;

    public SeleccionarProductos(int cantidad){
        this.cantidad = cantidad;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<Map<String, Object>> tabla = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < cantidad; i++) {
            actor.attemptsTo(NavegarMenu.on());
            WebElement elemento = CONTENEDOR_PADRE.resolveFor(actor);
            List<WebElement> productElements = elemento.findElements(By.xpath("//div//child::h3"));
            int item = rand.nextInt(productElements.size());
            WebElement randomProduct = productElements.get(item == 1 ? 0 : item);
            Map<String, Object> datosCarrito = new HashMap<>();
            datosCarrito.put("nombre", Arrays.asList(randomProduct.getText()));
            actor.attemptsTo(
                    ScrollElement.to(LINK_TITLE.of(randomProduct.getText().trim()).getCssOrXPathSelector()),
                    WaitUntil.the(LINK_TITLE.of(randomProduct.getText().trim()), isClickable()).forNoMoreThan(60).seconds(),
                    Click.on(LINK_TITLE.of(randomProduct.getText().trim()))
            );
            actor.attemptsTo(WaitUntil.the(LBL_PRESIO, isVisible()).forNoMoreThan(60).seconds());
            datosCarrito.put("precio", LBL_PRESIO_DESCUENTO.resolveFor(actor).isPresent() ? LBL_PRESIO_DESCUENTO.resolveFor(actor).getText().replace("$", "").replace(",", "").replace(".", "").trim() : LBL_PRESIO.resolveFor(actor).getText().replace("$", "").replace(",", "").replace(".", "").trim());
            datosCarrito.put("descuento",LBL_PORSENTAJE.resolveFor(actor).isPresent() ? LBL_PORSENTAJE.resolveFor(actor).getText().replace("-", "").replace("%", "").trim() : "0");
            int cantidadWeb = Integer.parseInt(LBL_CANTIDAD.resolveFor(actor).getText().replace(") Disponibles", "").replace("(", "").trim());
            int cant = (int) (Math.random() * cantidadWeb + 1);
            int nuevoValor = cant < 10 ? cant : 5;
            datosCarrito.put("cantidad", nuevoValor);
            actor.attemptsTo(
                    SeleccionarCantidad.seleccionar(nuevoValor),
                    WaitUntil.the(BTN_AGREGAR, isClickable()).forNoMoreThan(60).seconds(),
                    Click.on(BTN_AGREGAR),
                    Check.whether(BTN_CERRAR_CARRITO.resolveFor(actor).isCurrentlyVisible()).andIfSo(Click.on(BTN_CERRAR_CARRITO))
            );
            tabla.add(datosCarrito);
            BrowseTheWeb.as(actor).getDriver().navigate().back();
            actor.attemptsTo(WaitUntil.the(CONTENEDOR_PADRE, isVisible()).forNoMoreThan(60).seconds());
        }
        actor.remember("tabladatos", tabla);
        actor.attemptsTo(
                WaitUntil.the(CARRITO, isClickable()).forNoMoreThan(60).seconds(),
                Click.on(CARRITO)
        );
    }

    public static SeleccionarProductos on(int cantidad){
        return Tasks.instrumented(SeleccionarProductos.class, cantidad);
    }
}
