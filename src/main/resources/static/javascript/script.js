$(document).ready(function() {
  $('#searchForm').on('submit', function(e) {
    e.preventDefault(); // Prevent default form submission behavior

    var formData = $(this).serialize(); // Get form data
    var url = $(this).attr('action'); // Get the form's action attribute
    var tableContainer = $('.tableContainer'); // Add a class to your table container and select it here

    $.ajax({
      type: 'GET',
      url: url,
      data: formData,
      success: function(response) {
        var newTable = $(response).find('.tableContainer').html();
        tableContainer.html(newTable);
      },
      error: function(xhr, status, error) {
        console.error(error); // Handle any errors
      }
    });
  });
});
