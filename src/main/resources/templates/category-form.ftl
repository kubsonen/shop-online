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
                        <div class="col-md-6 mb-3">
                            <@spring.formInput "category.acronym"/>
                            <@spring.showErrors "<br>"/>
                            <!--<label for="acronym">Acronym</label>-->
                            <!--<input type="text" class="form-control is-invalid" name="acronym" id="acronym" value="">-->
                            <div class="invalid-feedback">
                                Fail
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <@spring.formInput "category.name"/>
                            <@spring.showErrors "<br>"/>
                            <!--<label for="name">Name</label>-->
                            <!--<input type="text" class="form-control" name="name" id="name" value="">-->
                            <div class="invalid-feedback">
                                Fail
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-12 mb-3">
                            <@spring.formInput "category.description"/>
                            <@spring.showErrors "<br>"/>
                            <!--<label for="description">Description</label>-->
                            <!--<input type="text" class="form-control" name="description" id="description" value="">-->
                            <div class="invalid-feedback">
                                Fail
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Add category</button>
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

