<%-- 
    Document   : GraficoPAnio
    Created on : 12 feb 2024, 17:01:15
    Author     : ale
--%>
  
 <jsp:include page="header.jsp"></jsp:include>
 <div class="container"> 
    <canvas id="graficoPedido" width="400" height="400"></canvas>
 </div>

    <script>
        // Realizar una solicitud AJAX al servlet para obtener los datos JSON
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://localhost:8080/lab01WTMP/ControllerGrafAnio', true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    var data = JSON.parse(xhr.responseText);
                    // Llamar a la función para generar el gráfico con Chart.js
                    generarGrafico(data);
                } else {
                    console.error('Error al obtener los datos del servidor');
                }
            }
        };
        xhr.send();

        // Función para generar el gráfico con Chart.js
        function generarGrafico(data) {
            var labels = [];
            var valores = [];

            // Extraer los datos del JSON recibido
            for (var i = 0; i < data.length; i++) {
                labels.push(data[i].mes + ' ' + data[i].anio);
                valores.push(data[i].ID);
            }

            // Crear el gráfico con Chart.js
            var ctx = document.getElementById('graficoPedido').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Total de Pedidos por Año',
                        data: valores,
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    }
                }
            });
        }
    </script>

    <jsp:include page="footer.jsp"></jsp:include>
