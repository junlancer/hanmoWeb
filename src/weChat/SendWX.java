package weChat;

public class SendWX {

    public void send(String tel, String sec) {
        WeChatMsgSend swx = new WeChatMsgSend();
        try {
            String token = swx.getToken("wwd51b29a3fb154c92", "KWSGMIpASmJ_wY8ettuAWafhfAdfTUKN3OParcIfaaY");
            String postdata = swx.createpostdata("ErShiYi", "text", 1000002, "content", "手机号：" + tel + "\n搜索内容：" + sec);
            String resp = swx.post("utf-8", WeChatMsgSend.CONTENT_TYPE, (new WeChatUrlData()).getSendMessage_Url(), postdata, token);
            System.out.println("获取到的token======>" + token);
            System.out.println("请求数据======>" + postdata);
            System.out.println("发送微信的响应数据======>" + resp);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}