package lan.net;

import org.apache.camel.CamelConfiguration;
import org.apache.camel.CamelContext;
import org.apache.camel.Configuration;
import org.apache.camel.spi.PropertiesComponent;
import org.apache.camel.spi.Registry;

@Configuration
public class FreepbxAgentConfiguration implements CamelConfiguration {
    public static final String readTimeoutProperty = "freepbx.netty_read_timeout";
    public static final String maxLineLengthProperty = "freepbx.netty_max_line_length";
    private static final String readTimeoutValue = "6";
    private static final String maxLineLengthValue = "1024";

    @Override
    public void configure(CamelContext camelContext) throws Exception {
        CamelConfiguration.super.configure(camelContext);
        camelContext.setManagementName("freepbx-agent");
        final PropertiesComponent properties = camelContext.getPropertiesComponent();
        final Registry registry = camelContext.getRegistry();
        registry.bind("stringEncoder", new io.netty.handler.codec.string.StringEncoder());
        registry.bind("stringDecoder", new io.netty.handler.codec.string.StringDecoder());
        registry.bind("amiDecoder", new lan.net.NettyAmiDecoder());
        registry.bind("nettyTimeoutHandler", new lan.net.NettyTimeoutHandler(
                Integer.parseInt(properties.resolveProperty(readTimeoutProperty).orElse(readTimeoutValue))
        ));
        registry.bind("nettyLineDecoder", new lan.net.NettyLineDecoder(
                Integer.parseInt(properties.resolveProperty(maxLineLengthProperty).orElse(maxLineLengthValue))
        ));
    }
}
