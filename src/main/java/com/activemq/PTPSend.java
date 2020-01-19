package com.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @auther: 高希阳
 * @Date: 2019/1/28 13:47
 * @Description:点对点的发送端(消息生产者)
 * 使用JMS方式发送消息
 * 参考链接：https://www.cnblogs.com/zhuxiaojie/p/5564187.html
 * https://blog.csdn.net/liuyuanq123/article/details/79109218
 * https://blog.csdn.net/qq_20009015/article/details/81409346
 */
public class PTPSend {
    //连接账号
    private String userName = "admin";
    //连接密码
    private String password = "admin";
    //连接地址
    private String brokerURL = "tcp://127.0.0.1:61616";
    //connection的工厂
    private ConnectionFactory factory;
    //连接对象
    private Connection connection;
    //一个操作会话
    private Session session;
    //目的地，其实就是连接到哪个队列，如果是点对点，那么它的实现是Queue，如果是订阅模式，那它的实现是Topic
    private Destination destination;
    //生产者，就是产生数据的对象
    private MessageProducer producer;

    public static void main(String[] args) {
        PTPSend send = new PTPSend();
        send.start();
    }
    public void start(){

        try {
            //根据用户名，密码，url创建一个连接工厂
            factory = new ActiveMQConnectionFactory(userName, password, brokerURL);
            //从工厂中获取一个连接
            connection = factory.createConnection();
            connection.start();
            /**
             * 功能描述：创建一个session
             * 第一个参数：是否支持事务，如果为true。则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
             * 第一个参数为false时表示不支持事务，第二个参数paramB的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
             * Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发送异常，也会被当做正常发送成功。
             * Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。
             * jms服务器才会认为发送成功，并删除消息
             * DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。
             * Session.SESSION_TRANSACTED表示Session启用了事务。事务提交并确认；在事务性会话中，当一个事务被提交的时候，确认自动发生；即只需设置Session.SESSION_TRANSACTED。
             * AUTO_ACKNOWLEDGE = 1    自动确认
             * CLIENT_ACKNOWLEDGE = 2    客户端手动确认
             * DUPS_OK_ACKNOWLEDGE = 3    自动批量确认
             * SESSION_TRANSACTED = 0    事务提交并确认
             */
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //创建一个到达的目的地，其实想一下就知道了，activemq不可能同时只能跑一个队列吧，这里就是连接一个名为“text-msg”的队列，
            //这个绘画将会到这个队列，当然，如果这个队列不存在，将会被创建
            destination = session.createQueue("text-msg");
            //从session中获取一个消息生产者
            producer = session.createProducer(destination);
            /**
             * 设置生产者的模式，有两种可选
             * DeliveryMode.PERSISTENT 当activemq关闭的时候，队列数据将会被保存
             * DeliveryMode.NON_PERSISTENT 当activemq关闭的时候，队列里面的数据将会被清空
             */
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            for (int i = 0; i < 100; i++) {
                //创建一条消息，当然，消息的类型有很多，如文字，字节，对象等,可以通过session.create..方法来创建出来
                String message = "生产者发送消息第" + (i + 1) + "条";
                TextMessage textMessage = session.createTextMessage(message);
                //发送一条消息
                producer.send(textMessage);
                //提交事务
                session.commit();
                System.out.println(message);
            }
            System.out.println("发送消息成功");
            //即便生产者的对象关闭了，程序还在运行哦
            //producer.close();
            //关闭资源(这样程序也关闭了)
            //session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
