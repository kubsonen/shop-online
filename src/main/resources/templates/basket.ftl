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
                    <small>Your basket</small>
                </h1>

                <br>

                <#if productsInBasket??>

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
                                    <td></td>
                                    <td>${(prod.productName)!""}</td>
                                    <td>${(prod.price)!""} PLN</td>
                                    <td></td>
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

