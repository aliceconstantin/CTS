package ro.ase.cts.exception;

public class NegativePriceException extends Exception {
	public NegativePriceException(String message) {
		System.out.println(message);
	}
}
