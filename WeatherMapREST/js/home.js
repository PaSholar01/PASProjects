$(document).ready(function() {
	hideResults();
	$('#add-button').on('click', function(event){

	var hasValidationErrors = checkForValidationErrors($('#search-by-zip').val());

	if(hasValidationErrors){
		alert("Invalid zipcode");
		return false;
	}

//function getWeatherData() {
	$('#errorMessages').empty();
	var currentCitySpan = $('#json-city').empty();
	var currentIconDiv = $('#current-icon').empty();
	var currentDataDiv = $('#current-data').empty();

	var inputZip = $('#search-by-zip').val();
	
	var inputUnit = $('#unit-type').val();
	

	$.ajax({
		type: 'GET',
		url: 'http://api.openweathermap.org/data/2.5/weather?zip=' + inputZip + '&units=' + inputUnit + '&appid=54a9d2703cd5cc7468b6d34353f75114',
		success: function(dataObject){
			$('#current').show();
			var city = dataObject.name;
			currentCitySpan.append().text(city);

			$.each(dataObject.weather, function(index, data){

				//var city = data.name;
				var currentIcon = data.icon;
				var currentIconURL = 'http://openweathermap.org/img/w/' + currentIcon + '.png';
				var currentConditions = data.description;
				var currentMainCondition = data.main;
				//var currentTemp = data.main.temp;
				//var currentHumidity = data.main.humidity;
				//var currentWindSpeed = data.wind.speed;

				var rowIconDiv = '<tr>';
					rowIconDiv += '<td><img src = "' + currentIconURL + '"></td>';
					rowIconDiv += '<td>' + currentMainCondition + ": " + currentConditions + '</td>';

				//currentCitySpan.append().text(city);
				currentIconDiv.append(rowIconDiv);
				//currentDataDiv.append(dataP1);


			});

			var currentTemp = dataObject.main.temp;
			var currentHumidity = dataObject.main.humidity;
			var currentWindSpeed = dataObject.wind.speed;

			var dataP1 = '<p>' + "Temperature: " + currentTemp + '</p>';
			var dataP2 = '<p>' + "Humidity: " + currentHumidity + "%" + '</p>';
			var dataP3 = '<p>' + "Wind: " + currentWindSpeed + " miles/hour" + '</p>';

			currentDataDiv.append(dataP1 + dataP2 + dataP3);



		},
		error: function() {
			$('#errorMessages').append('<li>').attr({class: 'list-group-item list-group-item-danger'}).text('Error calling web service. Please try again later.');

		}

	});

	$.ajax({
		type: 'GET',
		url: 'http://api.openweathermap.org/data/2.5/forecast/daily?zip=' + inputZip + '&units=' + inputUnit + '&appid=54a9d2703cd5cc7468b6d34353f75114',
		success: function(dataArrayObject){
			$('#five-day').show();
			$('#five-day-cast').empty();
			var index = 0;
			$.each(dataArrayObject.list, function(index, day){
				if(index < 5){
					var rawDate = new Date(day.dt * 1000);
					console.log(rawDate);
					var day = rawDate.getDate();
					var month = rawDate.toString().substring(4,7);
					var tempMax = this.temp.max;
					var tempMin = this.temp.min;
					var icon = this.weather[0].icon;
					var currentIconUrl = 'http://openweathermap.org/img/w/' + icon + '.png'; 
					var currentCondition = this.weather[0].main;

					if(inputUnit == "metric"){
					var dataP1 = '<p>' + day + month + '</p>';
					var dataP2 = '<p><img src = "' + currentIconUrl + '">' + currentCondition + '</p>';
					var dataP3 = '<p>' + "H " + tempMax + "C L " + tempMin + "C" + '</p>';
					}

					if(inputUnit == "imperial"){
					var dataP1 = '<p>' + day + month + '</p>';
					var dataP2 = '<p><img src = "' + currentIconUrl + '">' + currentCondition + '</p>';
					var dataP3 = '<p>' + "H " + tempMax + "F L " + tempMin + "F" + '</p>';
					
					}
					var forecastDiv = '<div class="col-sm-2">' + dataP1 + dataP2 + dataP3 + '</div>';

					$('#five-day-cast').append(forecastDiv);
					index++;
			    }

			});


		},

		error: function(){
			$('#errorMessages').append('<li>').attr({class: 'list-group-item list-group-item-danger'}).text('Error calling web service. Please try again later.');
		}

	});
});
});

function checkForValidationErrors(input){
	
	if(input.length > 5 || input.length < 5){
		return true;
	}

	//var checkNum = Integer.parseInt(input);

	//if(checkNum == NaN){
	//	return true;
	}

function hideResults(){
	$('#current').hide();
	$('#five-day').hide();
}

