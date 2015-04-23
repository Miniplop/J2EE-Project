<form class="form-inline form_signer_contrat row" action="consommateur" method="get">
    <h4>Signer un contrat</h4>
    <div class="form-group">
        <div class="col-lg-6">
            <input type="number" id="quantite_contrat" name="quantite_contrat" class="form-control" placeholder="Quantité" required>
        </div>
    </div>
    <input type="hidden" name="action" value="signer_contrat">
    <input type="hidden" name="produit_id" value="${param.produit}">
    <button type="submit" class="btn btn-default">Signer</button>
</form>