<form action="consommateur" method="get">
    <h4>DisponibilitÚs</h4>
    <c:forEach begin="1" end="${param.produit.getDuree()}" var="val">
        <label class="sr-only" for="semaine_${val}" id="type">Semaine ${val}</label>
        <input type="checkbox" name="semaine_${val}" id="semaine_${val}">
    </c:forEach>
    <input type="hidden" name="contrat_id" value="${param.contrat.getId()}">
    <input type="hidden" name="action" value="renseigner_disponibilites">
</form>