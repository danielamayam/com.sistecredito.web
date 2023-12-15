package com.sistecredito.web.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class UiMenu {

    public static final Target BTN_CERRAR_MODAL = Target.the("Botón cerrar modal").located(By.xpath("(//button[@class='icon-button icon-button-default icon-button-transparent icon-button-md'])[1]"));
    public static final Target BTN_MENU_MAS_CATEGORIAS = Target.the("Botón mas categorias").located(By.xpath("//button[span[contains(text(),'Más categorías')]]"));
    public static final Target BTN_MENU_ITEM = Target.the("Boton item menu").locatedBy("(//li//child::a//child::div//child::span)[{0}]");
    public static final Target BTN_MENU_SUB_ITEM = Target.the("Botón subitem menu").locatedBy("(//ul[contains(@class,'block')]//child::div//child::li//child::a)[{0}]");
    public static final Target BTN_MENU_SUB_ITEM2 = Target.the("Botón subitem menu 2").locatedBy("(//ul[contains(@class,'block')]//child::div//child::li//child::a)");

}
