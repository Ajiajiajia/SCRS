package cn.abtion.neuqercc.common.exceptions;

/**
 * @author abtion.
 * @since 17/9/22 17:53.
 * email caiheng@hrsoft.net
 */

public class LogicErrorException extends Throwable {
    public LogicErrorException() {
        super("逻辑上不可达的代码，请检查！！！");
    }
}
