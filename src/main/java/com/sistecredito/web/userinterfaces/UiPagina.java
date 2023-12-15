package com.sistecredito.web.userinterfaces;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;


@DefaultUrl("https://www.luegopago.com/")
public class UiPagina extends PageObject{

    public static final Target CONTENEDOR_PADRE = Target.the("Contenedor Padre").locatedBy("//div[@class='group min-h-full rounded-lg border border-transparent bg-white transition ease-in-out hover:cursor-pointer md:hover:border-secondary md:hover:shadow-md']");
    public static final Target LINK_TITLE = Target.the("Link titulo").locatedBy("//a//child::div//child::h3[contains(text(),'{0}')]");
    public static final Target LBL_PRESIO_DESCUENTO = Target.the("Label presio descuento").locatedBy("//*[@class='text-gray-400 line-through']//child::span");
    public static final Target LBL_PORSENTAJE = Target.the("Label porcentaje").locatedBy("//*[@class='ml-2 rounded bg-primary px-2 text-white']");
    public static final Target LBL_PRESIO = Target.the("Label presio").locatedBy("//div[@class='flex items-center font-bold']//child::h5[@class='font-bold']//child::span");
    public static final Target BTN_INCREMENTAR = Target.the("Boton incrementar").located(By.xpath("//button[@id=\"headlessui-listbox-button-5\"]"));
    public static final Target BTN_ITEM_INCREMENTAR = Target.the("Boton item incrementar").locatedBy("//li[text()='{0}']");
    public static final Target BTN_AGREGAR = Target.the("Boton Agregar al carrito").located(By.xpath("//button[contains(text(),'Añadir al carrito')]"));
    public static final Target BTN_CERRAR_CARRITO = Target.the("Boton cerrar carrito").located(By.xpath("(//button[@class='icon-button icon-button-default icon-button-transparent icon-button-md'])[7]"));
    public static final Target CARRITO = Target.the("Boton carrito").located(By.xpath("(//a[@title=\"Checkout\"])[2]"));
    public static final Target LBL_CANTIDAD = Target.the("Label cantidad").located(By.xpath("//div[@class='flex items-center space-x-2']//child::span"));
    public static final Target TXT_CANTIDAD = Target.the("Input cantidad").locatedBy("//a[contains(text(),'{0}')]//ancestor::div[@class='relative py-4 px-2 md:p-4 grid grid-cols-1 space-y-2 lg:grid-cols-2']//child::input");
    public static final Target LBL_SUBTOTAL = Target.the("Label subtotal").located(By.xpath("(//div[@class=\"flex justify-between px-2 text-lg\"]//child::span//following::span)[1]"));
    public static final Target LBL_DESCUENTO = Target.the("Label descuento").located(By.xpath("(//div[@class=\"flex justify-between px-2 text-lg\"]//child::span//following::span)[3]"));
    public static final Target LBL_TOTAL = Target.the("Label total").located(By.xpath("//span[@class=\"ml-4 text-right\"]"));

}
