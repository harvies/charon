package io.github.harvies.eris.base.designpatterns.command;

/**
 * 具体命令角色
 * @author zongyingfeng
 *
 */
public class ConcreteCommand implements Command {
    //持有相应的接收者对象
    private Receiver receiver = null;
    /**
     * 构造方法
     */
    public ConcreteCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute() {
    	System.out.println("服务员接收到点菜，通知厨师做菜");
        //通常会转调接收者对象的相应方法，让接收者来真正执行功能
        receiver.action();
    }

}
