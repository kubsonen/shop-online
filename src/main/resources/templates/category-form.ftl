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
                    <small>Add category</small>
                </h1>

                <@spring.bind "category"/>
                <form class="mb-3" method="POST" action="/category/form" id="category" >
                    <div class="form-row">
                        <div class="col-md-6">
                            <label for="acronym">Acronym</label>
                            <@spring.bind "category.acronym" />
                            <input type="text" class="form-control" name="acronym" id="acronym" value="${spring.status.value?default("")}">
                            <#list spring.status.errorMessages as error>
                                <p class="text-danger">${error}</p>
                            </#list>
                        </div>
                        <div class="col-md-6">
                            <@spring.bind "category.name" />
                            <label for="name">Name</label>
                            <input type="text" class="form-control" name="name" id="name" value="${spring.status.value?default("")}">
                            <#list spring.status.errorMessages as error>
                                <p class="text-danger">${error}</p>
                            </#list>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-12">
                            <@spring.bind "category.description" />
                            <label for="description">Description</label>
                            <input type="text" class="form-control" name="description" id="description" value="${spring.status.value?default("")}">
                            <#list spring.status.errorMessages as error>
                                <p class="text-danger">${error}</p>
                            </#list>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary my-3">Add category</button>
                </form>

            </div>

        <#include "/import/side-bar.ftl" >

        </div>
        <!-- /.row -->

    </div>
    <!-- /.container -->



<#include "/import/footer.ftl">
<#include "/import/js-libs.ftl">

</body>

