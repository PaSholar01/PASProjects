/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    
});

function searchDVDs() {
     $.ajax({
        type: 'POST',
        url: 'search/dvds',
        data: JSON.stringify({
            firstName: $('#search-first-name').val(),
            lastName: $('#search-last-name').val(),
            company: $('#search-company').val(),
            email: $('#search-email').val(),
            phone: $('#search-phone').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'data-Type': 'json',
        success: function (data) {
            $('#errorMessage').empty();
            fillContactTable(data);
        },
        error: function () {
            $('#errorMessages').append($('<li>').attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error calling web service. Please try again later.'));
        }
    });
}
