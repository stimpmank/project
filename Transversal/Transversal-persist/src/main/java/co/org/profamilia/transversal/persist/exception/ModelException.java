package co.org.profamilia.transversal.persist.exception;

public class ModelException extends Exception {
    public ModelException(String msg) {
        super(msg);
    }

    public ModelException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
