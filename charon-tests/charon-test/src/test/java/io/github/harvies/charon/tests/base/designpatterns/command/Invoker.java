package io.github.harvies.charon.tests.base.designpatterns.command;

/**
 * 请求者角色
 * @author zongyingfeng
 *
 */
public class Invoker {
    /**
     * 持有命令对象
     */
    private Command command = null;
    /**
     * 构造方法
     */
    public Invoker(Command command){
        this.command = command;
    }
    /**
     * 行动方法
     */
    public void action(){
        System.out.println("点菜");
        command.execute();
    }
}
