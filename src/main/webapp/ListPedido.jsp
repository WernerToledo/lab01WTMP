<%-- Document : ListPedido Created on : 10 feb 2024, 13:25:49 Author : ale --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <jsp:include page="header.jsp"></jsp:include>

    <div class="container">
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Cliente</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Total</th>
                    <th scope="col">Estado</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="pedido" items="${lPedido}">
                    <tr>
                        <th>${pedido.nombre}</th>
                        <th>${pedido.fecha}</th>
                        <td>${pedido.total}</td>
                        <td>${pedido.estado}</td>
                        <td>
                            <form method="GET" action="ControllerPedidoCUD">
                                <input type="hidden" name="id" value="${pedido.ID}">
                                <input type="hidden" name="action" value="delete">
                                <button type="submit" class="btn btn-danger">Eliminar</button>
                            </form>
                            <form method="GET" action="ControllerPedidoCUD">
                                <input type="hidden" name="id" value="${pedido.ID}">
                                <input type="hidden" name="action" value="update">
                                
                                <button type="submit" class="btn btn-warning">Modificar</button>
                            </form>
                        </td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form method="GET" action="ControllerPedidoCUD">
            <input type="hidden" name="id" value="-1">
            <button type="sudmit" class="btn btn-success">Agregar</button>
        </form>
        
        <h2 class="text-center">${warning}</h2>
    </div>

    <jsp:include page="footer.jsp"></jsp:include>