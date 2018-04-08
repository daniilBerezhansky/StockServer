<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: MyPC
  Date: 31.03.2018
  Time: 0:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/jquery.cookie.js"></script>
    <meta name="_csrf" th:content="${_csrf.token}"/>
</head>
<body>
<table class="table table-hover" id  = "myTable">
    <thead>
    <tr>
        <th>ID</th>
        <th>Product name</th>
        <th>Amount</th>
        <th>Arrival date</th>
        <th>Category</th>
    </tr>
    </thead>
    <tbody>

    </tbody>
</table>
<div>

        <input  type="text" id="product" value="Product" />
        <input  type="number" id="amount"  />
        <input  type="date" id="date" />
    <select id="categories">
        <option value="food">Food</option>
        <option value="house_hold">House hold</option>
        <option value="clothes">Clothes</option>
        <option value="appliances">Appliances</option>
    </select>
        </br>
        <input type="button" id="addBtn" value="Add"/>
        </br>
        <input type="number" id="id"/>

        <input type="button" id="delBtn" value="Delete"/>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        const url = 'http://localhost:8080/products'
        $.getJSON(url,
            function (json) {
                console.log(json)
                for (var i = 0; i < json.length; i++) {
                    var date =  new Date(json[i].dateArrival);
                    $('table').append("<tr>" +
                        "<td>" + json[i].id  + "</td>" +
                        "<td>" + json[i].productName + "</td>" +
                        "<td>" + json[i].amount + "</td>" +
                        "<td>" + date + "</td>" +
                        "<td>" + json[i].category + "</td>" +
                        "</tr>");
                }
            });
        function formToJSON() {
            return JSON.stringify({
                "productName": $('#product').val(),
                "amount": parseInt($('#amount').val()),
                "dateArrival" : new Date($('#date').val()).getTime(),
                "category" : $('#categories').val()
            });
        }
        $('#addBtn').click(function () {
            console.log('addProduct');
            $.ajax({
                type: 'POST',
                url: url,
                dataType: "json",
                data: formToJSON(),
                success: function(data, textStatus, jqXHR){
                    alert('Product added successfully');
                    location.reload();

                },
                error: function(jqXHR, textStatus, errorThrown){
                    alert('addProduct error: ' + textStatus);
                }
            });
        });
        $('#delBtn').click(function () {
            var id = $('#id').val();
            $.ajax({
                type: 'DELETE',
                url: url+"/"+id,

                success: function(data, textStatus, jqXHR){
                    alert('Product deleted successfully');
                    location.reload();

                },
                error: function(jqXHR, textStatus, errorThrown){
                    alert('Delete product error: ' + textStatus);
                }
            });
        });
    });
</script>
</body>
</html>
