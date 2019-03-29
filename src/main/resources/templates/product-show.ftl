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
                <h1 class="mt-4 mb-2">
                    <small><b>${(product.productName)!""}</b></small>
                </h1>

                <div class="row my-3">
                    <div class="text-right mr-3">
                        <a class="btn btn-primary" href="/product/addToBasket/${(product.productCode)!''}" role="button">Add to basket</a>
                    </div>
                </div>
                <!-- Add to basket -->

                <div class="row">
                    <div class="col-2"></div>
                    <div class="col-8">
                        <#if showPhoto??>
                            <img src="/image/product/${showPhoto}" class="img-thumbnail">
                        <#else>
                            <#if product.images?? && product.images?size != 0>
                                <img src="/image/product/${product.images[0].id}" class="img-thumbnail">
                            </#if>
                        </#if>
                    </div>
                    <div class="col-2"></div>
                </div>

                <div class="row my-4">
                    <#if product.images??>
                        <#list product.images as image>
                            <div class="col-2">
                                <a href="?showImg=${image.id}"><img src="/image/product/${image.id}" class="img-thumbnail"></a>
                            </div>
                        </#list>
                    </#if>
                </div>
                <!-- Image gallery -->

                <h2 class="my-4">
                    Description
                </h2>
                <div class="row mb-3">
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

