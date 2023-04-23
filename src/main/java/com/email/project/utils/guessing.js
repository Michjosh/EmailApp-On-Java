
const targetNum = Math.floor(Math.random() * 100) + 1;

const maxGuesses = 5;
let numGuesses = 0;

function checkGuess(guess) {
    numGuesses++;
    if (guess === targetNum) {
        return `Congratulations, you guessed the number in ${numGuesses} guesses!`;
    }

    if (numGuesses === maxGuesses) {
        return `Sorry, you ran out of guesses. The number was ${targetNum}.`;
    }

    const hint = guess < targetNum ? 'higher' : 'lower';
    return `Incorrect, try guessing ${hint}. You have ${maxGuesses - numGuesses} guesses left.`;
}
let guess;
while (numGuesses < maxGuesses) {
    guess = parseInt(prompt('Guess a number between 1 and 100: '));
    const result = checkGuess(guess);
    alert(result);

    if (guess === targetNum) {
        break;
    }
}
