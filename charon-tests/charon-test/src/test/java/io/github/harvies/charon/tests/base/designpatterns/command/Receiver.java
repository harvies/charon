package io.github.harvies.charon.tests.base.designpatterns.command;

/**
 * 接收者角色
 * @author zongyingfeng
 *
 */
public class Receiver {
    /**
     * 真正执行命令相应的操作
     */
    public void action(){
        System.out.println("执行操作：做菜");
    }
}
