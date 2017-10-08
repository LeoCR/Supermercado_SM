(function(){
    var prooveedoresSlider={
        $totalSwipsProvders:{},
        $countSlides:{},
        $sliderContainerProviders:{},
        $totalProviders:{},
        $windowClient:{},
        $provdrHTML:{},
        init:function(){
            this.$provdrHTML='';
            this.$countSlides=0;
            this.$totalProviders=7;
            this.$windowClient=jQuery(window);
            this.$sliderContainerProviders=jQuery("body").find(".content-slider");
            this.load();
        },
        load:function(){
            console.log("jQuery('main#main').find('#wrap_provdrs li').length "+jQuery('main#main').find('#wrap_provdrs li').length);
                jQuery.get("http://localhost:8080/supermercado-sm/api/prooveedor/totalDeProoveedores",
                function (data, status) {
                    console.log('data.totalDeProoveedores[0].totalDeProoveedores '+ data.totalDeProoveedores);
                    this.$totalProviders=data.totalDeProoveedores;
                });
                jQuery('main#main').find('#wrap_provdrs').html('');
                jQuery.get("http://localhost:8080/supermercado-sm/api/prooveedor/ver",
                                function (data, status) {
                                    var prooveedores = data.prooveedores;
                                    this.$provdrHTML= "";
                                    var i;
                                    for (i in prooveedores) {
                                        this.$provdrHTML+='<li class="swiper-slide_thumbs swiper-slide">'+
                                        '<a href="'+prooveedores[i].webSite+'" class="thumb_provider">'+
                                        '<figure><figcaption><p>'+prooveedores[i].nombre+'</p>'+
                                        '</figcaption>'
                                        +'<img src="http://localhost:8080/supermercado-sm/resources/images/prooveedores/'+
                                        prooveedores[i].logo+'" alt="'+prooveedores[i].nombre+
                                        '" title="'+prooveedores[i].nombre+'" class="img_provider" />'+
                                        '</figure></a></li>';
                                    }
                                    jQuery('main#main').find('#wrap_provdrs').html(this.$provdrHTML);
                                }
                );
                setTimeout(function(){
                    console.log('setTimeout prooveedoresSlider.setSliderProviders()');
                    prooveedoresSlider.setSliderProviders();
                }, 700);

        },
        setSliderProviders:function(){
                console.log('setSliderProviders');
               this.$sliderContainerProviders.lightSlider({
                    loop:true,
                    keyPress:true
                });
        }
    }
    prooveedoresSlider.init();
})()
                jQuery( window ).resize(function() {
                    var $scrollpos=$(window).scrollTop();
                        jQuery(window).scroll(function(){
                            var $clientWindow=jQuery('body').width();
                            $scrollpos=$(window).scrollTop();
                            console.log('$clientWindow '+ $clientWindow);
                            console.log('scrollpos=  '+$scrollpos);
                            if($scrollpos>=295){
                                jQuery('body').addClass('fixed_desktop_nav');
                            }
                            else{
                                jQuery('body').removeClass('fixed_desktop_nav');
                            }
                        });
                });
                jQuery('li.nav_link_menu a i.icon-angle-right').on('click',function () {
                     jQuery(this).parent('a').parent('li').toggleClass('opened_sub_menu');
                    jQuery(this).toggleClass('down_arrow');
                });
                jQuery('.btn_nav_top , .closebtn').on('click',function(){
                    var $clientWindow=jQuery('body').width();
                        jQuery('body').toggleClass('closed_mobile_nav opened_mobile_nav',function(){
                            if(jQuery(this).is('.opened_mobile_nav')){
                                console.log('This is opened_mobile_nav');
                            }
                            else{
                                console.log('This is not opened_mobile_nav');
                            }
                        });
                });
                jQuery( window ).scroll(function() {
                    var $header_nav_movile=jQuery('#sidenav_header').offset();
                    console.log( $header_nav_movile.top);
                    if($header_nav_movile.top<=112){
                        jQuery('body').addClass('header_to_top');
                    }
                    else{
                        jQuery('body').removeClass('header_to_top');
                    }
                });
                jQuery('.user_settings_menu').on('click',function(){
                    jQuery(this).toggleClass('opened_sub_menu');
                });
