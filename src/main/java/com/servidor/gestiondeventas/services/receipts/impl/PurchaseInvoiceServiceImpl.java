package com.servidor.gestiondeventas.services.receipts.impl;

import com.servidor.gestiondeventas.entities.receipts.PurchaseInvoice;
import com.servidor.gestiondeventas.repository.receipts.PurchaseInvoiceRepository;
import com.servidor.gestiondeventas.services.receipts.PurchaseInvoiceService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class PurchaseInvoiceServiceImpl implements PurchaseInvoiceService {
    PurchaseInvoiceRepository purchaseInvoiceRepository;

    @Override
    public List<PurchaseInvoice> getPurchaseInvoice() {

        return purchaseInvoiceRepository.findAll();
    }

    @Override
    public Optional<PurchaseInvoice> getPurchaseInvoiceById(Long idPurchaseInvoice) {
        return purchaseInvoiceRepository.findById(idPurchaseInvoice);
    }

    @Override
    public PurchaseInvoice createPurchaseInvoice(PurchaseInvoice purchaseInvoice) {

        return purchaseInvoiceRepository.save(purchaseInvoice);

    }

    @Override
    public PurchaseInvoice editPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
        return purchaseInvoiceRepository.save(purchaseInvoice);

    }

    @Override
    public boolean deletePurchaseInvoice(Long idPurchaseInvoice) {
        Optional<PurchaseInvoice> purchaseInvoice = purchaseInvoiceRepository.findById(idPurchaseInvoice);
        if (purchaseInvoice.isPresent()) {
            purchaseInvoiceRepository.deleteById(idPurchaseInvoice);
            return true;
        }
        return false;

    }
}
