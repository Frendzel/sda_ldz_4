package pl.erbel;

public class JednorozecException extends Throwable {

    Jednorozec jednorozec;

    public JednorozecException(String message, Jednorozec jednorozec) {
        super(message);
        this.jednorozec = jednorozec;
    }

    public JednorozecException(String message, Throwable cause, Jednorozec jednorozec) {
        super(message, cause);
        this.jednorozec = jednorozec;
    }

    public JednorozecException(Throwable cause, Jednorozec jednorozec) {
        super(cause);
        this.jednorozec = jednorozec;
    }

    public JednorozecException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Jednorozec jednorozec) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.jednorozec = jednorozec;
    }

    @Override
    public String toString() {
        return "JednorozecException{" +
                "jednorozec=" + jednorozec +
                '}' +
                super.toString();
    }
}
