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

            <h1 class="my-4">Categories
                <small>Find your product</small>
            </h1>

            <br>

            <#if categories??>

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Description</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>

                    <#list categories as category>

                        <tr>
                            <td>${(category.name)!""}</td>
                            <td>${(category.description)!""}</td>
                            <td>
                                <a class="btn btn-primary" href="/category?acronym=${(category.acronym)!''}" role="button">Subcategory</a>
                                <a class="btn btn-primary" href="/category/${(category.acronym)!''}" role="button">Products</a>
                            </td>
                        </tr>

                    </#list>


                    </tbody>
                </table>

            </#if>

            <#if adminLogged?? && adminLogged == true>
                <a class="btn btn-primary my-3" href="/category/form" role="button">Add category</a>
                <a class="btn btn-primary my-3" href="/category/import" role="button">Import categories</a>
                <a class="btn btn-primary my-3" href="/product/form" role="button">Add product</a>
                <a class="btn btn-primary my-3" href="/product/import" role="button">Import products</a>
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

