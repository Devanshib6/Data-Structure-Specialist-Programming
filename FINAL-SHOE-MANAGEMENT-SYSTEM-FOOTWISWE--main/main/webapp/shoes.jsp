<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Footwise - Shoe Inventory</title>
    <link rel="stylesheet" type="text/css" href="css/shoe.css">
</head>
<body>

<div class="container">
    <h2>Shoe Inventory - Footwise</h2>

    <!-- Add Shoe Form -->
    <form action="shoes" method="post">
        <input type="hidden" name="action" value="add">
        <input type="text" name="name" placeholder="Shoe Name" required>
        <input type="text" name="brand" placeholder="Brand" required>
        <input type="number" step="0.01" name="price" placeholder="Price" required>
        <input type="number" name="quantity" placeholder="Quantity" required>
        <button type="submit">Add Shoe</button>
    </form>

    <!-- Shoe List Table -->
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Shoe Name</th>
                <th>Brand</th>
                <th>Price</th>
                <th>Quantity</th>
                <th colspan="2">Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="shoe" items="${shoes}">
                <tr>
                    <td>${shoe.id}</td>
                    <td>${shoe.name}</td>
                    <td>${shoe.brand}</td>
                    <td>${shoe.price}</td>
                    <td>${shoe.quantity}</td>
                    <td>
                        <!-- Update Button -->
                        <form action="shoes" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="id" value="${shoe.id}">
                            <input type="text" name="name" value="${shoe.name}" required>
                            <input type="text" name="brand" value="${shoe.brand}" required>
                            <input type="number" step="0.01" name="price" value="${shoe.price}" required>
                            <input type="number" name="quantity" value="${shoe.quantity}" required>
                            <button class="action-btn update-btn" type="submit">Update</button>
                        </form>
                    </td>
                    <td>
                        <!-- Delete Button -->
                        <form action="shoes" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="${shoe.id}">
                            <button class="action-btn delete-btn" type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
