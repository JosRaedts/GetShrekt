// By : Codicode.com
// Source : http://www.codicode.com/art/image_color_up_jquery_plugin.aspx
// Licence : Creative Commons Attribution license (http://creativecommons.org/licenses/by/3.0/)

// You can use this plugin for commercial and personal projects.
// You can distribute, transform and use them into your work,
// but please always give credit to www.codicode.com

// The above copyright notice and this permission This notice shall be included in
// all copies or substantial portions of the Software.

jQuery.fn.colorUp = function () {
    $(window).load(function () {
        $('.colorup').each(function () {
            var curImg = $(this).wrap('<span />');
            var newImg = curImg.clone().css({ "position": "absolute", "z-index": "98", "opacity": "0" }).insertBefore(curImg);
            newImg.attr("src", grayImage(this, curImg.attr("effect")));
            newImg.addClass('colorUpped').animate({ opacity: getInv(curImg) ? 1 : 0 }, getSpeed(curImg));
        });
        $('.colorUpped').mouseover(function () {
            $(this).stop().animate({ opacity: getInv($(this)) ? 0 : 1 }, getSpeed($(this)));
        });
        $('.colorUpped').mouseout(function () {
            $(this).stop().animate({ opacity: getInv($(this)) ? 1 : 0 }, getSpeed($(this)));
        });
        
        $('.filter').change(function(){
            switch ($('.filter').val()) {
            case '0':
                $('.colorup').attr('effect','none');
                break;
            case '1':
                $('.colorup').attr('effect','sepia');
                break;
            case '2':
                $('.colorup').attr('effect','mono');
                break;
            default:
                alert("default");
                }
            
            reload();
        });
    });
    
    function reload(){
        $('.colorup').each(function () {
            var curImg = $(this).wrap('<span />');
            var newImg = curImg.clone().css({ "position": "absolute", "z-index": "98", "opacity": "0" }).insertBefore(curImg);
            newImg.attr("src", grayImage(this, curImg.attr("effect")));
            newImg.addClass('colorUpped').animate({ opacity: getInv(curImg) ? 1 : 0 }, getSpeed(curImg));
        });
    }
    
    function getSpeed(elem) {
        return (elem.attr("speed")) ? parseInt(elem.attr("speed")) : 1000;
    }
    function getInv(elem) {
        return (elem.attr("inverse") && (elem.attr("inverse") === "true")) ? true : false;
    }
    
    function grayImage(image, effect) {
        var myCnv = document.createElement("canvas");
        var myCtx = myCnv.getContext("2d");

        myCnv.width = image.width;
        myCnv.height = image.height;
        myCtx.drawImage(image, 0, 0);

        var imgData = myCtx.getImageData(0, 0, myCnv.width, myCnv.height);

        for (var y = 0; y < imgData.height; y++) {
            for (var x = 0; x < imgData.width; x++) {

                var pos = (y * 4) * imgData.width + (x * 4);

                switch (effect) {
                    case ("sepia"):
                        var mono = imgData.data[pos] * 0.32 + imgData.data[pos + 1] * 0.5 + imgData.data[pos + 2] * 0.18;
                        imgData.data[pos] = mono + 50;
                        imgData.data[pos + 1] = mono;
                        imgData.data[pos + 2] = mono - 50;
                        break;
                    case ("negative"):
                        imgData.data[pos] = 255 - imgData.data[pos];
                        imgData.data[pos + 1] = 255 - imgData.data[pos + 1];
                        imgData.data[pos + 2] = 255 - imgData.data[pos + 2];
                        break;
                    case ("light"):
                        imgData.data[pos] = imgData.data[pos] + 80;
                        imgData.data[pos + 1] = imgData.data[pos + 1] + 80;
                        imgData.data[pos + 2] = imgData.data[pos + 2] + 80;
                        break;
                    case ("dark"):
                        imgData.data[pos] = imgData.data[pos] - 80;
                        imgData.data[pos + 1] = imgData.data[pos + 1] - 80;
                        imgData.data[pos + 2] = imgData.data[pos + 2] - 80;
                        break;
                    case ("noise"):
                        var noise = (0.5 - Math.random()) * 160;
                        imgData.data[pos] = imgData.data[pos] + noise;
                        imgData.data[pos + 1] = imgData.data[pos + 1] + noise;
                        imgData.data[pos + 2] = imgData.data[pos + 2] + noise;
                        break;
                    default:
                        imgData.data[pos] = imgData.data[pos + 1] = imgData.data[pos + 2] = imgData.data[pos] * 0.32 + imgData.data[pos + 1] * 0.5 + imgData.data[pos + 2] * 0.18
                }
            }
        }
        myCtx.putImageData(imgData, 0, 0, 0, 0, imgData.width, imgData.height);
        return myCnv.toDataURL();
    }
};
$.fn.colorUp();