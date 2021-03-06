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

                <h1 class="mt-4 mb-2">
                    Your basket
                    <#if !productsInBasket?? || productsInBasket?size=0>
                        is empty.
                    </#if>
                </h1>

                <br>

                <#if productsInBasket?? && productsInBasket?size!=0>

                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Count</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>

                            <#list productsInBasket as product>

                                <tr>
                                    <td>${(product.productName)!""}</td>
                                    <td>${(product.price)!""} PLN</td>
                                    <td>${(product.countInBasket)!""}</td>
                                    <td>
                                        <a class="btn btn-primary" href="/product/addOnePieceFromBasket/${(product.productCode)!''}" role="button">+</a>
                                        <a class="btn btn-danger" href="/product/deleteOnePieceFromBasket/${(product.productCode)!''}" role="button">-</a>
                                        <a class="btn btn-danger" href="/product/deleteProductFromBasket/${(product.productCode)!''}" role="button">Delete</a>
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

