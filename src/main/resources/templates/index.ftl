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
                The most popular products
                <br>
                <small>

                </small>
            </h1>

            <#if theMostViewedProducts??>

                <#list theMostViewedProducts as topProduct>

                    <!-- Blog Post -->
                    <div class="card mb-4">
                        <img class="card-img-top" src="/image/product/${topProduct.productThumbNailId}">
                        <div class="card-body">
                            <h2 class="card-title">${(topProduct.productName)!""}</h2>
                            <p class="card-text">${(topProduct.shortDescription)!""}</p>
                            <a href="/product/${topProduct.productCode}" class="btn btn-primary">Check this out &rarr;</a>
                        </div>
                        <div class="card-footer text-muted">
                            Last viewed on <b>${(topProduct.lastViewDate)!"N/A"}</b>
                        </div>
                    </div>

                </#list>

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

</html>