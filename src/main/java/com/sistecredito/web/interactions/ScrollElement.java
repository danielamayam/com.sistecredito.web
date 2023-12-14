package com.sistecredito.web.interactions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ScrollElement implements Interaction {
    private String xpath;

    public ScrollElement(String xpath) {
        this.xpath = xpath;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement element = BrowseTheWeb.as(actor).getDriver().findElement(By.xpath(xpath));
        Actions actions = new Actions(BrowseTheWeb.as(actor).getDriver());
        actions.moveToElement(element);
        actions.perform();
    }

    public static ScrollElement to(String xpath) {
        return Instrumented.instanceOf(ScrollElement.class).withProperties(xpath);
    }
}
