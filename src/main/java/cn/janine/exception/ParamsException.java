package cn.janine.exception;

/**
 * 参数异常,继承制ServiceException
 * 
 *
 */
public class ParamsException extends ServiceException {
    /**
     * 自动生成的serialVersionUID
     */
    private static final long serialVersionUID = 8336801071550068539L;

    /**
     * 无参构造方法
     */
    public ParamsException() {
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
    public ParamsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 构造方法
     * 
     * @param message 异常信息
     * @param cause Throwable
     */
    public ParamsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造方法
     * 
     * @param message 异常信息
     */
    public ParamsException(String message) {
        super(message);
    }

    /**
     * 构造方法
     * 
     * @param cause Throwable
     */
    public ParamsException(Throwable cause) {
        super(cause);
    }

}
