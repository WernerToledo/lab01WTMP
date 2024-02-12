<%-- Document : header Created on : 10 feb 2024, 13:30:21 Author : ale --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
    <html>

    <head>
        <title>Start Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Navbar</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="ListCliente.jsp">Home</a>
                        </li>
                        
                        <li class="nav-item">
                            <a class="nav-link" href="ControllerPedido">Pedido</a>
                        </li>

                        
                        <li class="nav-item">
                            <a class="nav-link" href="ControllerShowGraf">Grafico Más pedidos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ControllerShowAnio">Grafico Por año</a>
                        </li>
                        
                    </ul>
                </div>
            </div>
        </nav>