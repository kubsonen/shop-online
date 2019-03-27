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

    <!-- Most viewed categories -->
    <div class="card my-4">
        <h5 class="card-header">Most viewed categories</h5>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-6">
                    <ul class="list-unstyled mb-0">
                        <li>
                            <a href="#">Web Design</a>
                        </li>
                        <li>
                            <a href="#">HTML</a>
                        </li>
                        <li>
                            <a href="#">Freebies</a>
                        </li>
                    </ul>
                </div>
                <div class="col-lg-6">
                    <ul class="list-unstyled mb-0">
                        <li>
                            <a href="#">JavaScript</a>
                        </li>
                        <li>
                            <a href="#">CSS</a>
                        </li>
                        <li>
                            <a href="#">Tutorials</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <!-- Recently viewed products -->
    <div class="card my-4">
        <h5 class="card-header">Last viewed products</h5>
        <div class="card-body">
            <div class="row">
                <div class="col-lg-6">
                    <ul class="list-unstyled mb-0">
                        <li>
                            <a href="#">Web Design</a>
                        </li>
                        <li>
                            <a href="#">HTML</a>
                        </li>
                        <li>
                            <a href="#">Freebies</a>
                        </li>
                    </ul>
                </div>
                <div class="col-lg-6">
                    <ul class="list-unstyled mb-0">
                        <li>
                            <a href="#">JavaScript</a>
                        </li>
                        <li>
                            <a href="#">CSS</a>
                        </li>
                        <li>
                            <a href="#">Tutorials</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>