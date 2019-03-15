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
                <small>Add product</small>
            </h1>

            <#if productSaveFail??>
                <div class="alert alert-danger" role="alert">
                    ${productSaveFail}
                </div>
            </#if>
            <#if productSaveSuccess??>
                <div class="alert alert-success" role="alert">
                    ${productSaveSuccess}
                </div>
            </#if>

            <@spring.bind "product"/>
            <form enctype="multipart/form-data" class="mb-3" method="POST" action="/product/form" id="product" >
                <div class="form-row">
                    <div class="col-md-4">
                        <label for="productName">Name</label>
                        <@spring.bind "product.productName" />
                        <input type="text" class="form-control" name="productName" id="productName" value="${spring.status.value?default("")}">
                        <#list spring.status.errorMessages as error>
                            <p class="text-danger">${error}</p>
                        </#list>
                    </div>
                    <div class="col-md-4">
                        <@spring.bind "product.price" />
                        <label for="price">Price</label>
                        <input type="text" class="form-control" name="price" id="price" value="${spring.status.value?default("")}">
                        <#list spring.status.errorMessages as error>
                            <p class="text-danger">${error}</p>
                        </#list>
                    </div>
                    <div class="col-md-4">
                        <@spring.bind "product.category" />
                        <label for="category">Category</label>
                        <select class="form-control" name="category" id="category" value="${spring.status.value?default(0)}">
                            <option value=""></option>
                            <#if availableCategories??>
                                <#list availableCategories as cat>
                                    <option   value="${cat.id}" <#if cat.id?? && product.category?? && product.category.id?? && cat.id=product.category.id> selected="selected" </#if>>
                                        ${cat.name}
                                    </option>
                                </#list>
                            </#if>
                        </select>
                        <#list spring.status.errorMessages as error>
                            <p class="text-danger">${error}</p>
                        </#list>
                    </div>
                    <div class="col-md-12 my-2">
                        <@spring.bind "product.description" />
                        <label for="description">Paste your product description</label>
                        <textarea class="form-control" id="description" name="description" rows="5">${spring.status.value?default("")}</textarea>
                        <#list spring.status.errorMessages as error>
                            <p class="text-danger">${error}</p>
                        </#list>
                    </div>

                    <div class="col-md-12 my-2">
                        <@spring.bind "product.tempFiles" />
                        <div class="form-group">
                            <label for="tempFiles">Import images</label>
                            <input type="file" class="form-control-file" name="tempFiles" id="tempFiles" multiple="multiple">
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary my-3">Add product</button>
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

