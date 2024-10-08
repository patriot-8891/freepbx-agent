package lan.net;

import io.netty.channel.socket.SocketChannel;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.netty.NettyConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class NettyReplyProcessor implements Processor {
    private static final Logger log = LoggerFactory.getLogger(NettyReplyProcessor.class);
    private StringBuilder builder = new StringBuilder();
    private SocketChannel socketChannel;

    @Override
    public void process(Exchange exchange) throws Exception {
        final SocketChannel channel = (SocketChannel) exchange.getProperty(NettyConstants.NETTY_CHANNEL);
        if ((channel != null) && (channel != socketChannel))
            socketChannel = channel;

        if (socketChannel == null)
            throw new RuntimeException("SocketChannel is null");

        final Object body = exchange.getMessage().getBody();
        if (body == null)
            return;

        if ((body instanceof Map<?, ?> map) && (!map.isEmpty())) {
            builder.setLength(0);
            for (Object key : map.keySet()) {
                builder.append(key).append(": ").append(map.get(key)).append("\n");
            }
            builder.append("\n");
            socketChannel.writeAndFlush(builder.toString());
            return;
        }
        socketChannel.writeAndFlush(body);
    }
}
