Feature: Integración compra de artículos

  Como qa de sistecredito
  Quiero automatizar la compra de artículos
  Para verificar el flujo y superar el reto

  @smokTest
  Scenario: QA realiza una compra de varios productos aleatorios
    Given que "Daniel" se encuentra en la pagina web
    When selecciono 2 productos aleatoriamente
    Then válido los productos del carrito