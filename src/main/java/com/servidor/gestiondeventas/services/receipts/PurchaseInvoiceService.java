package com.servidor.gestiondeventas.services.receipts;


import com.servidor.gestiondeventas.entities.receipts.PurchaseInvoice;

import java.util.List;
import java.util.Optional;

public interface PurchaseInvoiceService {
    public List<PurchaseInvoice> getPurchaseInvoice();

    public Optional<PurchaseInvoice> getPurchaseInvoiceById(Long idPurchaseInvoice);

    public PurchaseInvoice createPurchaseInvoice(PurchaseInvoice purchaseInvoice);

    public PurchaseInvoice editPurchaseInvoice(PurchaseInvoice purchaseInvoice);

    public boolean deletePurchaseInvoice(Long idPurchaseInvoice);
}
