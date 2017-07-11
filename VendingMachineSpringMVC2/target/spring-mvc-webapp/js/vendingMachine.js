$(document).ready(function () {;
    var changeMade = false;
    $('#theOne').val("0.00");
    $('#theOne').attr("readonly", true);
    
    $('#makeChangeButton').on('click', function (event) {

        if (changeMade) {
            $('#makeChangeButton').css('border', 'none');
            $('#compMessage').empty();
            $('#changeMessage').empty();
            $('#theOne').empty();
            $('#theOne').val('0.00');
            $('#purchaseSelection').val('');

            changeMade = false;
     
        } else if (!changeMade) {

            var inputTotal = parseInt($('#theOne').val() * 100);
            var exitQuarters = parseInt(inputTotal / 25);
            inputTotal = inputTotal % 25;
            var exitDimes = parseInt(inputTotal / 10);
            inputTotal = inputTotal % 10;
            var exitNickels = parseInt(inputTotal / 5);
            inputTotal = inputTotal % 5;
            var exitPennies = inputTotal;
            var totalChangeE = '';
            var changeWindowE = $('#changeMessage');

            if (exitQuarters > 0) {
                if (exitQuarters > 1) {
                    var quartersE = '<span style="margin-right: 8%;">' + exitQuarters + ' Quarters</span>';
                    totalChangeE += quartersE;
                } else {
                    var quartersE = '<span style="margin-right: 8%;">' + exitQuarters + ' Quarter</span>';
                    totalChangeE += quartersE;
                }
            }
            if (exitDimes > 0) {
                if (exitDimes > 1) {
                    var dimesE = '<span style="margin-right: 8%;">' + exitDimes + ' Dimes</span>';
                    totalChangeE += dimesE;
                } else {
                    var dimesE = '<span style="margin-right: 8%;">' + exitDimes + ' Dime</span>';
                    totalChangeE += dimesE;
                }
            }

            if (exitNickels > 0) {
                if (exitNickels > 1) {
                    var nickelsE = '<span style="margin-right: 8%;">' + exitNickels + ' Nickels</span>';
                    totalChangeE += nickelsE;
                } else {
                    var nickelsE = '<span style="margin-right: 8%;">' + exitNickels + ' Nickel</span>';
                    totalChangeE += nickelsE;
                }
            }

            if (exitPennies > 0) {
                if (exitPennies > 1) {
                    var penniesE = '<span style="margin-right: 8%;">' + exitPennies + ' Pennies</span>';
                    totalChangeE += penniesE;
                } else {
                    var penniesE = '<span style="margin-right: 8%;">' + exitPennies + ' Penny</span>';
                    totalChangeE += penniesE;
                }
            }


            changeWindowE.append(totalChangeE);
            changeMade = true;
            $('#makeChangeButton').css('border', '2px solid red');

        }

    });
});

function addDollar() {

    var amountIn = $('#theOne').val();
    var numberAmountDollar = parseFloat(amountIn);
    var oneDollar = 1.00;
    numberAmountDollar += oneDollar;
    $('#theOne').val(numberAmountDollar.toFixed(2));

}

function addQuarter() {

    var amountIn = $('#theOne').val();
    var numberAmountQuarter = parseFloat(amountIn);
    var oneQuarter = 0.25;
    numberAmountQuarter += oneQuarter;
    $('#theOne').val(numberAmountQuarter.toFixed(2));

}

function addDime() {

    var amountIn = $('#theOne').val();
    var numberAmountDime = parseFloat(amountIn);
    var oneDime = 0.10;
    numberAmountDime += oneDime;
    $('#theOne').val(numberAmountDime.toFixed(2));

}

function addNickel() {

    var amountIn = $('#theOne').val();
    var numberAmountNickel = parseFloat(amountIn);
    var oneNickel = 0.05;
    numberAmountNickel += oneNickel;
    $('#theOne').val(numberAmountNickel.toFixed(2));

}

function addItem(itemId) {
    $('#purchaseSelection').val('');
    $('#purchaseSelection').val(itemId);
}

