package cn.janine.exception;

/**
 * 不相等异常,继承制ServiceException
 * 
 *
 */
public class NotEqualException extends ServiceException {
    /**
     * 自动生成的serialVersionUID
     */
    private static final long serialVersionUID = -5612925588190340248L;

    /**
     * 无参构造方法
     */
    public NotEqualException() {
        super();
    }

    /**
     * 构造方法
     * 
     * @param message 异常信息
     * @param cause Throwable
     * @param enableSuppression boolean
     * @param writableStackTrace boolean
     */
    public NotEqualException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 构造方法
     * 
     * @param message 异常信息
     * @param cause Throwable
     */
    public NotEqualException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造方法
     * 
     * @param message 异常信息
     */
    public NotEqualException(String message) {
        super(message);
    }

    /**
     * 构造方法
     * 
     * @param cause Throwable
     */
    public NotEqualException(Throwable cause) {
        super(cause);
    }

}
