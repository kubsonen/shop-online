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

            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Description</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Mark</td>
                        <td>
                            <button type="button" class="btn btn-primary">Primary</button>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>Jacob</td>
                        <td>
                            <button type="button" class="btn btn-primary">Primary</button>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td>Larry</td>
                        <td>
                            <button type="button" class="btn btn-primary">Primary</button>
                        </td>
                    </tr>
                </tbody>
            </table>

            <br>

            <#if adminLogged?? && adminLogged == true>

                <h1 class="my-4">
                    <small>Add category</small>
                </h1>
                <form class="mb-3" method="POST" action="category/add" id="parentId" name="parentId">
                    <input type="hidden" id="custId" name="custId" value="${parentId}">
                    <div class="form-row">
                        <div class="col-md-6 mb-3">
                            <label for="acronym">Acronym</label>
                            <input type="text" class="form-control is-invalid" name="acronym" id="acronym" value="">
                            <div class="invalid-feedback">
                                Fail
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="name">Name</label>
                            <input type="text" class="form-control is-invalid" name="name" id="name" value="">
                            <div class="invalid-feedback">
                                Fail
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <label for="description">Description</label>
                            <input type="text" class="form-control is-invalid" name="description" id="description" value="">
                            <div class="invalid-feedback">
                                Fail
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-primary" type="submit">Add category</button>
                </form>
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

