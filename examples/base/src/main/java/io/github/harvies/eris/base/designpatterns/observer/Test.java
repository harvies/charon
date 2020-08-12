package io.github.harvies.eris.base.designpatterns.observer;

/**
 * https://mp.weixin.qq.com/s/JIKmq_Rit_gah8o9OgHgLA
 */
public class Test {
    public static void main(String[] args) {

        XiaoMei xiao_mei = new XiaoMei();
        LaoWang lao_wang = new LaoWang();
        LaoLi lao_li = new LaoLi();


        xiao_mei.addPerson(lao_wang);
        xiao_mei.addPerson(lao_li);


        xiao_mei.notifyPerson();
    }
}
