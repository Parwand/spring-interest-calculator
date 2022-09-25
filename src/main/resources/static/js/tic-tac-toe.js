let turn = 'x';
let head = document.querySelector('.head');
let squares = [];

function game(id) {
    let square = document.getElementById(id);

    if (turn === 'x' && square.innerHTML === '') {
        square.innerHTML = "X";
        turn = 'o';
        head.innerHTML = "O NOW";
    }

    else if (turn === 'o' && square.innerHTML === '') {
        square.innerHTML = "O";
        turn = 'x';
        head.innerHTML = "X NOW";
    }

    winner();
}

function winner() {
    
    for (let i = 1; i < 10; i++){
        squares[i] = document.getElementById('square'+ i).innerHTML;
    }

    if(squares[1] === squares[2] && squares[2] === squares[3] && squares[3] !== '') {
        end(1, 2, 3);
    }

    else if(squares[4] === squares[5] && squares[5] === squares[6] && squares[6] !== '') {
        end(4, 5, 6);
    }

    else if(squares[7] === squares[8] && squares[8] === squares[9] && squares[9] !== '') {
        end(7, 8, 9);
    }

    else if(squares[1] === squares[4] && squares[4] === squares[7] && squares[7] !== '') {
        end(1, 4, 7);
    }
    
    else if(squares[2] === squares[5] && squares[5] === squares[8] && squares[8] !== '') {
        end(2, 5, 8);
    }

    else if(squares[3] === squares[6] && squares[6] === squares[9] && squares[9] !== '') {
        end(3, 6, 9);
    }

    else if(squares[1] === squares[5] && squares[5] === squares[9] && squares[9] !== '') {
        end(1, 5, 9);
    }

    else if(squares[3] === squares[5] && squares[5] === squares[7] && squares[7] !== '') {
        end(3, 5, 7);
    }

    else if(squares[1] !== '' &&
            squares[2] !== '' &&
            squares[3] !== '' &&
            
            squares[4] !== '' &&
            squares[5] !== '' &&
            squares[6] !== '' &&

            squares[7] !== '' &&
            squares[8] !== '' &&
            squares[9] !== '' ) {
        head.style.backgroundColor = "red";
        head.innerHTML = "NO WINNER";
        setTimeout(() => {location.reload()}, 3000)
    }
    
}

function end(num1, num2, num3) {
    head.innerHTML = squares[num1] + ' is Winner';
    document.getElementById('square' + num1).style.backgroundColor = 'rgb(34, 238, 126)';
    document.getElementById('square' + num2).style.backgroundColor = 'rgb(34, 238, 126)';
    document.getElementById('square' + num3).style.backgroundColor = 'rgb(34, 238, 126)';
    setInterval(function(){head.innerHTML += '.';}, 1000);
    setTimeout(() => {location.reload();}, 4000);
}

function newGame() {
    location.reload();
}