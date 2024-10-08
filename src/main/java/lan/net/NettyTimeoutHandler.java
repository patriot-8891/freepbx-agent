package lan.net;

import io.netty.channel.ChannelHandler;
import org.apache.camel.component.netty.ChannelHandlerFactory;

public class NettyTimeoutHandler extends io.netty.handler.timeout.ReadTimeoutHandler implements ChannelHandlerFactory {
    private static volatile int defaultTimeoutSeconds = 6;

    public NettyTimeoutHandler(int timeoutSeconds) {
        super(timeoutSeconds);
        defaultTimeoutSeconds = timeoutSeconds;
    }

    @Override
    public ChannelHandler newChannelHandler() {
        return new NettyTimeoutHandler(defaultTimeoutSeconds);
    }
}
