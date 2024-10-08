package lan.net;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.apache.camel.component.netty.ChannelHandlerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// extends ChannelInboundHandlerAdapter
public class NettyAmiDecoder extends MessageToMessageDecoder<String> implements ChannelHandlerFactory {
    private static final Logger log = LoggerFactory.getLogger(NettyAmiDecoder.class);
    private static final Pattern key_value_pattern = Pattern.compile("^(?<key>[^:]+): (?<value>.*)$");
    private static final Pattern asterisk_call_manager = Pattern.compile("^Asterisk Call Manager/\\d+\\.\\d+\\.\\d+$");
    private final StringBuilder builder = new StringBuilder();

    @Override
    public ChannelHandler newChannelHandler() {
        return new NettyAmiDecoder();
    }

    public static LinkedHashMap<String, String> toLinkedHashMap(String body) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (String line : body.split("\n")) {
            Matcher matcher = key_value_pattern.matcher(line);
            if (matcher.matches())
                map.put(matcher.group("key"), matcher.group("value"));
        }
        return map;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, String string, List<Object> list) throws Exception {
        if (string == null)
            return;

        if (asterisk_call_manager.matcher(string).matches()) {
            list.add(toLinkedHashMap(String.format("Line: %s\n", string)));
            return;
        }

        if (string.isEmpty()) {
            if (!builder.isEmpty())
                list.add(toLinkedHashMap(builder.toString()));
            builder.setLength(0);
            return;
        }

        builder.append(string).append("\n");
    }
}
