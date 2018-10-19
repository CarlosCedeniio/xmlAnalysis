<!-- chart.jsp-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script type="text/javascript">
        window.onload = function() {

            var dps = [[]];
            var chart = new CanvasJS.Chart("chartContainer", {
                animationEnabled: true,
                theme: "light1", // "light1", "dark1", "dark2"
                title: {
                    text: "Cantidad de cambios entre muestras"
                },
                subtitles: [{
                    text: "Se omiten nodos que permanecen invariantes"
                }],

                axisY: {
                    title: "# Cambios",
                    suffix: "",
                    valueFormatString: "#",
                    includeZero: false,
                    interval: 8
                },
                axisX: {
                    title: "Nodos"
                },
                data: [{
                    type: "column",
                    yValueFormatString: "#,##0",
                    dataPoints: dps[0]
                }]
            });

            var yValue;
            var label;

            <c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
            <c:forEach items="${dataPoints}" var="dataPoint">
            yValue = parseInt("${dataPoint.y}");
            label = "${dataPoint.label}";
            dps[parseInt("${loop.index}")].push({
                label : label,
                y : yValue,
            });
            </c:forEach>
            </c:forEach>

            chart.render();

        }
    </script>
</head>
<body>
<div id="chartContainer" style="height: 500px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>