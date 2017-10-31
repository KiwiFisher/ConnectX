package nz.ac.autuni.cny0166.io.exceptions;

public class EzTXTSyntaxException extends StringIndexOutOfBoundsException {

    /**
     * For use when any external files have not been formatted correctly.
     * @param e The error message to display
     */
    public EzTXTSyntaxException(String e) {

        super(e);

    }

}
