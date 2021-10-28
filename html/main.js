let urlProduct = "http://localhost:8080/products/";
let urlCategory = "http://localhost:8080/categories/";

showProduct();

function category(category) {
    return `<option value="${category.id}">${category.name}</option>`
}

function showCategory() {
    $.getJSON(urlCategory, function (data) {
        let content = "";
        for (let i = 0; i < data.length; i++) {
            content += category(data[i]);
        }
        $("#category").html(content);
    })
}

function product(product) {
    return `<tr><td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.image}</td>
            <td><button id="editProduct" value="${product.id}" class="btn btn-warning">Edit</button></td>
            <td><button id="deleteProduct" value="${product.id}" class="btn btn-danger">Delete</button></td>
            <td><button id="infoProduct" value="${product.id}" class="btn btn-info">Info</button></td></tr>`
}

function showProduct() {
    $.getJSON(urlProduct, function (data) {
        let content = "";
        for (let i = 0; i < data.length; i++) {
            content += product(data[i]);
        }
        $("#productTable").html(content);
    })
}

//Show Modal Create Product
$(document).ready(function () {
    $("#createProduct").click(function () {
        $("#name").val("");
        $("#image").val("");
        $("#description").val("");
        $("#category").val("");
        let myModal = new bootstrap.Modal(document.getElementById('showCreate'));
        $("#checkCreateAndEdit").val("create");
        showCategory();
        myModal.show();
    });
})

function createAndEditProduct() {
    let check = $("#checkCreateAndEdit").val();
    if (check == "create") {
        let form = $("#productForm")[0];
        let data = new FormData(form);
        $.ajax({
            url: urlProduct,
            type: "POST",
            enctype: 'multipart/form-data',
            data: data,
            processData: false,
            contentType: false,
            success: function (result) {
                alert("done");
            }
        })
    }
}

//Create and edit product
$(document).ready(function () {
    $("#createProductButton").click(function () {
        createAndEditProduct();
        showProduct();
    });
})

//Info product
$(document).ready(function () {
    $("#infoProduct").click(function () {
        let id = $("#infoProduct").val();
        $.getJSON(urlProduct + id, function (data) {
            $("#modalBody").html(showProductInfo(data));
        })

    })
})

function showProductInfo(product) {
    let content = `<div class="card">
                    <div class="card-header">
                        Product Info
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">${product.name}</li>
                        <li class="list-group-item">${product.description}</li>
                        <li class="list-group-item">${product.image}</li>
                    </ul>
                </div>`
    return content;
}