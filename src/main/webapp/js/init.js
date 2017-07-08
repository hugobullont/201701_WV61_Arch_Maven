(function($){
  $(function(){

    $('.button-collapse').sideNav();
    $('.parallax').parallax();

  }); // end of document ready
})(jQuery); // end of jQuery name space

$(document).ready(function() {
    $('input#input_text, textarea#textarea1').characterCounter();
  });
  
$('.carousel.carousel-slider').carousel({fullWidth: true});