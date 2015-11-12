package cn.janine.exception;

/**
 * 异常:不可删除,继承制ServiceException
 * 
 *
 */
public class UndeletableException extends ServiceException {
    /**
     * 自动生成的serialVersionUID
     */
    private static final long serialVersionUID = -3690128249981919955L;

    /**
     * 无参构造方法
     */
    public UndeletableException() {
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
    public UndeletableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 构造方法
     * 
     * @param message 异常信息
     * @param cause Throwable
     */
    public UndeletableException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造方法
     * 
     * @param message 异常信息
     */
    public UndeletableException(String message) {
        super(message);
    }

    /**
     * 构造方法
     * 
     * @param cause Throwable
     */
    public UndeletableException(Throwable cause) {
        super(cause);
    }

}
