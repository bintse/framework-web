package cn.janine.exception;

/**
 * Service层公用的Exception.
 * 
 * 继承制RuntimeException,从被Spring事务管理的函数中抛出时，会出发事务回滚
 * 
 *
 */
public class ServiceException extends RuntimeException {
    /**
     * 自动生成的serialVersionUID
     */
    private static final long serialVersionUID = 6461493341838706388L;

    /**
     * 无参构造方法
     */
    public ServiceException() {
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
    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 构造方法
     * 
     * @param message 异常信息
     * @param cause Throwable
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 构造方法
     * 
     * @param message 异常信息
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * 构造方法
     * 
     * @param cause Throwable
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

}
