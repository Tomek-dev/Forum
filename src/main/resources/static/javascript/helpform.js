(function($){
  var $form = $('#help-form');
  $form.on('submit', function(e) {
    e.preventDefault();
    $.ajax({
      url: $form.attr('action'),
      type: 'post',
      data: $form.serialize(),
      success: function(response) {
        if ($(response).find('.has-error').length) {
          $form.replaceWith(response);
        }
      }
  });
})
}(jQuery));