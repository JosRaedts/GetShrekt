<%-- 
    Document   : photodetail
    Created on : 14-apr-2015, 13:06:30
    Author     : Jos
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">
    <div class="Titelphotoview">
        <h1>Photo detail pagina </h1>
    </div>
    <div class="row">
      <div class="col-md-6">
        <!-- <h3 class="page-header">Demo:</h3> -->
        <div id="cropper" class="img-container">
          <img src="${baseurl}/photo/view/low/${photo.id}" alt="Picture">
        </div>
      </div>
      <div class="col-md-6">
        <!-- <h3 class="page-header">Preview:</h3> -->
        <div class="docs-preview clearfix">
          <div class="img-preview preview-lg"></div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-9 docs-buttons">
        <!-- <h3 class="page-header">Toolbar:</h3> -->
        <div class="btn-group">
          <button class="btn btn-primary" data-method="setDragMode" data-option="move" type="button" title="Move">
            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setDragMode&quot;, &quot;move&quot;)">
              <span class="icon icon-move"></span>
            </span>
          </button>
          <button class="btn btn-primary" data-method="setDragMode" data-option="crop" type="button" title="Crop">
            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setDragMode&quot;, &quot;crop&quot;)">
              <span class="icon icon-crop"></span>
            </span>
          </button>
          <button class="btn btn-primary" data-method="zoom" data-option="0.1" type="button" title="Zoom In">
            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;zoom&quot;, 0.1)">
              <span class="icon icon-zoom-in"></span>
            </span>
          </button>
          <button class="btn btn-primary" data-method="zoom" data-option="-0.1" type="button" title="Zoom Out">
            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;zoom&quot;, -0.1)">
              <span class="icon icon-zoom-out"></span>
            </span>
          </button>
          <button class="btn btn-primary" data-method="rotate" data-option="-45" type="button" title="Rotate Left">
            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;rotate&quot;, -45)">
              <span class="icon icon-rotate-left"></span>
            </span>
          </button>
          <button class="btn btn-primary" data-method="rotate" data-option="45" type="button" title="Rotate Right">
            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;rotate&quot;, 45)">
              <span class="icon icon-rotate-right"></span>
            </span>
          </button>
        </div>
        <div class="btn-group btn-group-crop">
          <button class="btn btn-primary" data-method="getCroppedCanvas" type="button">
            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getCroppedCanvas&quot;)">
              Get Cropped Canvas
            </span>
          </button>
        </div>
        <!-- Show the cropped image in modal -->
        <div class="modal fade docs-cropped" id="getCroppedCanvasModal" aria-hidden="true" aria-labelledby="getCroppedCanvasTitle" role="dialog" tabindex="-1">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-header">
                <button class="close" data-dismiss="modal" type="button" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="getCroppedCanvasTitle">Cropped</h4>
              </div>
              <div class="modal-body"></div>
            </div>
          </div>
        </div><!-- /.modal -->
        <div class="btn-group" data-toggle="buttons">
          <label class="btn btn-primary active" data-method="setAspectRatio" data-option="1.7777777777777777" title="Set Aspect Ratio">
            <input class="sr-only" id="aspestRatio1" name="aspestRatio" value="1.7777777777777777" type="radio">
            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setAspectRatio&quot;, 16 / 9)">
              16:9
            </span>
          </label>
          <label class="btn btn-primary" data-method="setAspectRatio" data-option="0.5625" title="Set Aspect Ratio">
            <input class="sr-only" id="aspestRatio2" name="aspestRatio" value="0.5625" type="radio">
            <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setAspectRatio&quot;, 9 / 16)">
              9:16
            </span>
          </label>
        </div>

        <button class="btn btn-primary" data-method="getData" data-option="" data-target="#putData" type="button">
          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getData&quot;)">
            Get Data
          </span>
        </button>
        <button class="btn btn-primary" data-method="setData" data-target="#putData" type="button">
          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setData&quot;, data)">
            Set Data
          </span>
        </button>
        <button class="btn btn-primary" data-method="getContainerData" data-option="" data-target="#putData" type="button">
          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getContainerData&quot;)">
            Get Container Data
          </span>
        </button>
        <button class="btn btn-primary" data-method="getImageData" data-option="" data-target="#putData" type="button">
          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getImageData&quot;)">
            Get Image Data
          </span>
        </button>
        <button class="btn btn-primary" data-method="getCanvasData" data-option="" data-target="#putData" type="button">
          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getCanvasData&quot;)">
            Get Canvas Data
          </span>
        </button>
        <button class="btn btn-primary" data-method="setCanvasData" data-target="#putData" type="button">
          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setCanvasData&quot;, data)">
            Set Canvas Data
          </span>
        </button>
        <button class="btn btn-primary" data-method="getCropBoxData" data-option="" data-target="#putData" type="button">
          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;getCropBoxData&quot;)">
            Get Crop Box Data
          </span>
        </button>
        <button class="btn btn-primary" data-method="setCropBoxData" data-target="#putData" type="button">
          <span class="docs-tooltip" data-toggle="tooltip" title="$().cropper(&quot;setCropBoxData&quot;, data)">
            Set Crop Box Data
          </span>
        </button>
        <input class="form-control" id="putData" type="text" placeholder="Get data to here or set data with this value">

      </div><!-- /.docs-buttons -->
    </div>
    <div class="col-md-12">
        <div id="boxes">
        <c:forEach var="product" items="${products}">
            <c:if test="${product.price > 0}">
            <div class="col-md-3 col-sm-6 col-xs-12">
                <div class="box">
                    <input type="hidden" name="productid" value="${product.id}">
                    <img id="photo${product.id}" src="${baseurl}/photo/view/low/${photo.id}" width="100%" />
                    <img class="imgproduct" src="${baseurl}/product/view/${product.id}" width="100%"/>
                    <select value="" class="qty" name="qty">
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                    <label id="price${product.id}" price="${product.price}">&euro; ${product.price}</label>
                </div>
            </div>
            </c:if>
        </c:forEach>
        </div>
        <div class="col-md-12">
            <label name="totalAmount" class="total"></label><br />
            <input id="add_btn" type="submit" name="submit" value="Toevoegen aan winkelwagen" style="margin-bottom:15px;"> 
            <script>
                $(document).ready(function()
                {
                    $('.box').each(function() {
                        var id = $(this).find('input:hidden').val();
                        $( "#photo"+id ).imageMask( "${baseurl}/product/mask/"+id );
                    });
                    update_amounts();
                    $('.box').change(function(){
                        update_amounts();
                    });
                });

                function update_amounts()
                {
                    var sum = 0.0;
                    $('.box').each(function() {
                        var id = $(this).find('input:hidden').val();
                        var qty = $(this).find('option:selected').val();
                        var price = $('#price'+id).attr('price');
                        var amount = (qty*price);
                        sum+=amount;
                    });
                    //just update the total to sum  
                    $('.total').text("Totaal bedrag: " + '\u20AC' + sum.toFixed(2));
                }
                
                
                $(document).ready(function(){
                    $("#add_btn").click(function(){
                        var products = [];
                        var $image = $('.img-container > img')
                     $('.box').each(function() {
                        var id = $(this).find('input:hidden').val();
                        var qty = $(this).find('option:selected').val();
                        var price = $('#price'+id).attr('price');
                        if(qty > 0) {
                            var product = [];
                            product["id"] = id;
                            product["qty"] = qty;
                            products.push(product);
                            $.post("${baseurl}/shoppingcart/add",
                        {
                          photo_id: "${photo.id}",
                          photo_data: $image.cropper("getCropBoxData"),
                          product_id: id,
                          product_qty: qty
                        },
                        function(data,status){
                            
                        });
                        }
                    });
                    window.location.href = "${baseurl}/shoppingcart/list";
                        //console.log(products);
                    });
                });

            </script>
        </div>
    </div>
</div>
  <!-- Alert -->
  <div class="docs-alert"><span class="warning message"></span></div>
  <script src="${baseurl}/resources/js/cropper.js"></script>
  <script src="${baseurl}/resources/js/crop.js"></script>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />