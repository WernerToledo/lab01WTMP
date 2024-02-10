<%-- 
    Document   : FormCliente
    Created on : 10 feb 2024, 13:25:37
    Author     : ale
--%>


    <jsp:include page="header.jsp"></jsp:include>
        <div class="container">
            <form method="post">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="nombre" placeholder="Ingrese su nombre">
                    <label for="nombre">Ingrese su nombre</label>
                </div>
                <button class="btn btn-success">Agregar Nuevo</button>
            </form>    
        </div>
        
    <jsp:include page="footer.jsp"></jsp:include>
