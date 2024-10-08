package lan.net;

import io.netty.channel.ChannelHandler;
import org.apache.camel.component.netty.ChannelHandlerFactory;

public class NettyLineDecoder extends io.netty.handler.codec.LineBasedFrameDecoder implements ChannelHandlerFactory {
    private static volatile int defaultMaxLength = 1024;

    public NettyLineDecoder(int maxLength) {
        super(maxLength);
        defaultMaxLength = maxLength;
    }

    @Override
    public ChannelHandler newChannelHandler() {
        return new NettyLineDecoder(defaultMaxLength);
    }
}
