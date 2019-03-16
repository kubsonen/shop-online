<#import "/spring.ftl" as spring />

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Shop online</title>
    <#include "/import/css-libs.ftl">

</head>

<body>

<#include "/import/navigation.ftl">

<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="my-4">
                <small>Browse products</small>
            </h1>

            <br>

            <#if products??>

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Name</th>
                        <th scope="col">Price</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>

                    <#list products as product>

                        <tr>
                            <td>
                                <#if product.productThumbNailId??>
                                    <img src="/image/product/${product.productThumbNailId}" class="img-fluid" width="100" height="100">
                                </#if>
                            </td>
                            <td>${(product.productName)!""}</td>
                            <td>${(product.price)!""} PLN</td>
                            <td>
                                <a class="btn btn-primary" href="/product/${(product.productCode)!''}" role="button">Show</a>
                                <a class="btn btn-primary" href="#" role="button">Add</a>
                                <#if adminLogged?? && adminLogged == true>
                                    <a class="btn btn-danger" href="/product/delete/${product.id}" role="button">Delete</a>
                                </#if>
                            </td>
                        </tr>

                    </#list>


                    </tbody>
                </table>

            </#if>

        </div>

    <#include "/import/side-bar.ftl" >
    </div>
    <!-- /.row -->

</div>
<!-- /.container -->



<#include "/import/footer.ftl">
<#include "/import/js-libs.ftl">

</body>

