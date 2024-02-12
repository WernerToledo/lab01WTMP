<%-- 
    Document   : Grafico
    Created on : 12 feb 2024, 14:21:47
    Author     : ale
--%>
  <jsp:include page="header.jsp"></jsp:include>
  
  <div class="container">
    <canvas id="myChart" width="400" height="400"></canvas>  
  </div>
    
    <script>
        // Realizar una solicitud AJAX al servlet para obtener los datos JSON
        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://localhost:8080/lab01WTMP/ControllerGraf', true);
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
            var nombresClientes = [];
            var cantidadesPedidos = [];

            // Extraer los datos del JSON recibido
            for (var i = 0; i < data.length; i++) {
                nombresClientes.push(data[i].nombreCliente);
                cantidadesPedidos.push(data[i].cantidadPedidos);
            }

            // Crear el gráfico con Chart.js
            var ctx = document.getElementById('myChart').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: nombresClientes,
                    datasets: [{
                        label: 'Cantidad de Pedidos por Cliente',
                        data: cantidadesPedidos,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.5)',
                            'rgba(54, 162, 235, 0.5)',
                            'rgba(255, 206, 86, 0.5)',
                            'rgba(75, 192, 192, 0.5)',
                            'rgba(153, 102, 255, 0.5)',
                            'rgba(255, 159, 64, 0.5)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)'
                        ],
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