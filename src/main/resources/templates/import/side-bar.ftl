<!-- Sidebar Widgets Column -->
<div class="col-md-4">

    <!-- Basket -->
    <div class="card my-4">
        <h5 class="card-header">Your basket
            <#if basketShopAttribute??>
                ${basketShopAttribute.getOrderSum()}&nbsp;PLN
            </#if>
        </h5>
        <div class="card-body">
            <a href="/basket" class="btn btn-secondary w-100" type="button">Go to your basket</a>
        </div>
    </div>

    <!-- Search Widget -->
    <div class="card my-4">
        <h5 class="card-header">Search your product</h5>
        <div class="card-body">
            <form action="/product/searchProduct" method="post">
                <div class="input-group">
                    <input type="text" class="form-control" name="search-field" id="search-field" placeholder="Search for...">
                    <span class="input-group-btn"></span>
                    <button type="submit" class="btn btn-secondary" type="button">Go!</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Recently viewed products -->
    <#if lastProductsViewed?? && lastProductsViewed?size != 0>
        <div class="card my-4">
            <h5 class="card-header">Last viewed products</h5>
            <div class="card-body" style="padding: 0px;">
                <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <#list lastProductsViewed as product>
                            <div class="carousel-item">
                                <img class="d-block w-100" alt="First slide [800x400]" src="/image/productThumb/${(product.productCode)!''}" data-holder-rendered="true">
                                <div class="carousel-caption d-none d-md-block">
                                    <h5>First slide label</h5>
                                    <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                                </div>
                            </div>
                        </#list>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>
    </#if>

    <!-- Most viewed categories -->
    <#if mostViewCategories?? && mostViewCategories?size != 0>
        <div class="card my-4">
            <h5 class="card-header">Most viewed categories</h5>
            <div class="card-body">
                <div class="row">
                    <#list mostViewCategories as category>
                        <div class="col-lg-6">
                            <ul class="list-unstyled mb-0">
                                <li>
                                    <a href="/category/${(category.acronym)!''}">${(category.name)!""}</a>
                                </li>
                            </ul>
                        </div>
                    </#list>
                </div>
            </div>
        </div>
    </#if>


</div>