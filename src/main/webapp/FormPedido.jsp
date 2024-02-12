<%-- Document : FormPedido Created on : 10 feb 2024, 13:26:01 Author : ale --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="header.jsp"></jsp:include>

    <div class="container">
        <form method="POST">
            <input type="hidden" name="id" value="${id}">
        
            <div class="form-floating">
                <select class="form-select" id="id_cliente"  name="id_cliente" aria-label="Floating label select example">
                    <option selected>Open this select menu</option>
                    <c:forEach var="cliente" items="${lCliente}">
                        <c:choose>
                            <c:when test="${cliente.ID eq pedido.ID_Cliente}">
                                <option value="${cliente.ID}" selected>${cliente.nombre}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${cliente.ID}">${cliente.nombre}</option>
                            </c:otherwise>
                        </c:choose>

                        <!-- <option value="${cliente.ID}">${cliente.nombre}</option> -->
                    </c:forEach>
                </select>
                <label for="id_cliente">Select</label>
            </div>

            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="total" name="total" placeholder="" value="${pedido.total}">
                <label for="total">Total</label>
            </div>

            <button class="btn btn-success" type="submit">Agregar nuevo</button>
        </form>
    </div>

    <jsp:include page="footer.jsp"></jsp:include>