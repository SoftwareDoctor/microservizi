package it.softwaredoctor.order_service.stubs;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStubs {

    public static void stubInventoryCall(String skuCode, String quantity) {
        stubFor(get(urlPathEqualTo("/api/v1/inventory"))
                .withQueryParam("skuCode", equalTo(skuCode))
                .withQueryParam("quantity", equalTo(quantity))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("true"))); // restituisce `true` per simulare disponibilità
    }
    
}
/*
Utilità per i Test: Questo metodo viene probabilmente usato nei test per simulare le risposte di un'API, grazie a WireMock, ed è comune nei contesti di testing che i metodi di stubbing siano static. In questo modo, puoi chiamare InventoryClientStubs.stubInventoryCall(...) direttamente, semplificando il codice nei test e risparmiando il passaggio di istanziare la classe.

Nessuno Stato Interno Necessario: Il metodo non dipende da alcun dato o stato interno della classe InventoryClientStubs. Poiché è autonomo e non interagisce con variabili di istanza, non ha bisogno di essere associato a un'istanza.
 */