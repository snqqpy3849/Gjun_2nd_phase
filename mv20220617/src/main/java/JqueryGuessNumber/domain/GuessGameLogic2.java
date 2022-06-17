package JqueryGuessNumber.domain;

public class GuessGameLogic2 {
	  private int theNumber;
	  private int remainder = 5;
	  private String hint;
	  public GuessGameLogic2(int startNumber, int endNumber) {
	    this.theNumber = generateRandomNumber(startNumber , endNumber);
	  }
	  public GuessGameLogic2(int startNumber, int endNumber, int remainder) {
	    this.remainder = remainder;
	    this.theNumber = generateRandomNumber(startNumber , endNumber);
	  }
	  public boolean isCorrectGuess(int guess) {
	    if (guess == theNumber) {
	      return true;
	    } else {
	    	if(guess > theNumber)
		        hint="Guess Number to Big";       
		    else
		        hint="Guess Number to Small"; 
	      remainder--;
	      return false;
	    }
	  }
	  private int generateRandomNumber(int startNumber, int endNumber) {
	    double range = (double) (endNumber - startNumber + 1);
	    return startNumber + (int) (Math.random() * range);
	  }
	  public int getRemainder() {
	    return remainder;
	  }
	public int getTheNumber() {
		return theNumber;
	}
	public void setTheNumber(int theNumber) {
		this.theNumber = theNumber;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public void setRemainder(int remainder) {
		this.remainder = remainder;
	}

}
