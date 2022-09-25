function sum() {
    let x = parseFloat(document.getElementById("x-sum").value);
    let y = parseFloat(document.getElementById("y-sum").value);
    document.getElementById('sum-result').innerHTML = x+y;
}

function multiply() {
    let x = parseFloat(document.getElementById("x-mul").value);
    let y = parseFloat(document.getElementById("y-mul").value);
    document.getElementById('mul-result').innerHTML = x * y;
}

function divide() {
    let x = parseFloat(document.getElementById("x-divide").value);
    let y = parseFloat(document.getElementById("y-divide").value);
    document.getElementById('divide-result').innerHTML = x/y;
}

