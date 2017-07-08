/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global FB */



window.fbAsyncInit = function() {
            FB.init({
              appId      : '1904600703162541',
              cookie     : true,
              xfbml      : true,
              version    : 'v2.9'
            });
            FB.AppEvents.logPageView();   
          };
          
          
          
          (function(d, s, id){
             var js, fjs = d.getElementsByTagName(s)[0];
             if (d.getElementById(id)) {return;}
             js = d.createElement(s); js.id = id;
             js.src = "//connect.facebook.net/en_US/sdk.js";
             fjs.parentNode.insertBefore(js, fjs);
           }(document, 'script', 'facebook-jssdk'));
           
           window.onload = getLoginStatus;
           
                    function getLoginStatus()
                    {
                        FB.getLoginStatus(function(response) {
                                        if (response.status === 'connected') {
                                          // the user is logged in and has authenticated your
                                          // app, and response.authResponse supplies
                                          // the user's ID, a valid access token, a signed
                                          // request, and the time the access token 
                                          // and signed request each expire
                                          var uid = response.authResponse.userID;
                                          var accessToken = response.authResponse.accessToken;

                                           window.location.replace('Session?uid='+uid+'&accessToken='+accessToken);
                                           
                                        } 
                                       });
                                       
                        
                        

                    }


