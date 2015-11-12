package cn.janine.exception;

/**
 * 异常:目标不可用,继承制ServiceException
 * 
 *
 */
public class DisabledException extends ServiceException {
    /**
     * 自动生成的serialVersionUID
     */
    private static final long serialVersionUID = -8460029016779255060L;

    /**
     * 无参构造方法
     */
    public DisabledException() {
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
    public DisabledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 构造方法
     * 
     * @param message 异常信息
     * @param cause Throwable
     */
    public DisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造方法
     * 
     * @param message 异常信息
     */
    public DisabledException(String message) {
        super(message);
    }

    /**
     * 构造方法
     * 
     * @param cause Throwable
     */
    public DisabledException(Throwable cause) {
        super(cause);
    }

}
