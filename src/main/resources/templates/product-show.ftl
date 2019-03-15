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

            <#if product??>
                <h1 class="mt-4">
                    <small>${(product.productName)!""}</small>
                </h1>

                <br>

                <div class="row my-3">
                        <div class="text-right mr-3">
                            <a class="btn btn-primary" href="#" role="button">Add to basket</a>
                        </div>
                    </div>
                </div>
                <!-- Add to basket -->

                <div class="row">
                    <div class="col-2"></div>
                    <div class="col-8">
                        <#if product.images?? && product.images?size != 0>
                            <img src="/image/product/${product.images[0].id}" class="img-thumbnail">
                        </#if>
                    </div>
                    <div class="col-2"></div>
                </div>
                <!-- Image gallery -->

                <div class="row">
                    <p>
                        ${(product.description)!""}
                    </p>
                </div>
                <!-- Description -->
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

