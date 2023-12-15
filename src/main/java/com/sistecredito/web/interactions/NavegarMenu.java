package com.sistecredito.web.interactions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.HoverOverElement;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.sistecredito.web.userinterfaces.UiMenu.*;
import static com.sistecredito.web.userinterfaces.UiMenu.BTN_MENU_SUB_ITEM;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class NavegarMenu implements Interaction {
    @Override
    public <T extends Actor> void performAs(T actor) {
        int item = (int) (Math.random() * (8 - 1 + 1)) + 1;
        actor.attemptsTo(
                WaitUntil.the(BTN_MENU_MAS_CATEGORIAS,  isClickable()).forNoMoreThan(120).seconds(),
                Click.on(BTN_MENU_MAS_CATEGORIAS),
                HoverOverElement.over(BTN_MENU_ITEM.of(String.valueOf(item)))

        );

        List<WebElement> elements = BrowseTheWeb.as(actor).getDriver().findElements(By.xpath(BTN_MENU_SUB_ITEM2.getCssOrXPathSelector()));
        int subItem = (int) (Math.random() * (elements.size() - 1 + 1)) + 1;
        actor.attemptsTo(
                Click.on(BTN_MENU_SUB_ITEM.of(String.valueOf(subItem)))
        );
    }

    public static NavegarMenu on() {
        return Instrumented.instanceOf(NavegarMenu.class).withProperties();
    }
}
