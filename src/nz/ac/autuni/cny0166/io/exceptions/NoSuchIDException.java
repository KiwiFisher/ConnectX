package nz.ac.autuni.cny0166.io.exceptions;

public class NoSuchIDException extends Exception {

    /**
     * For use when a tagID is not available
     * @param errorMessage The error message to display
     */
    public NoSuchIDException(String errorMessage) {

        super(errorMessage);

    }

}
