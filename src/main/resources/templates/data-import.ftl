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
                <small>Import categories</small>
            </h1>

            <#if importError??>
                <div class="alert alert-danger" role="alert">
                    ${importError}
                </div>
            </#if>
            <#if importSuccess??>
                <div class="alert alert-success" role="alert">
                    ${importSuccess}
                </div>
            </#if>

            <form class="mb-3" method="POST" action="${importAction}" >
                <div class="form-group">
                    <label for="importText">Paste your import text</label>
                    <textarea class="form-control" id="importText" name="importText" rows="10"></textarea>
                </div>
                <button type="submit" class="btn btn-primary my-3">Import ${whatImport}</button>
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

