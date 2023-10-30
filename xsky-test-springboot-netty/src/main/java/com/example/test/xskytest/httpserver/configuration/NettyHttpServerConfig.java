package com.example.test.xskytest.httpserver.configuration;


import com.example.test.xskytest.httpserver.netty.iohandler.FilterLogginglHandler;
import com.example.test.xskytest.httpserver.netty.iohandler.HttpServerHandler;
import com.example.test.xskytest.httpserver.netty.iohandler.InterceptorHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//参考链接：https://www.cnblogs.com/flydean/p/15906513.html
//参考链接：https://developer.aliyun.com/article/908251
public class NettyHttpServerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyHttpServerConfig.class);
    private ChannelGroup allChannels =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public ChannelGroup getAllChannel() {
        return allChannels;
    }

    /**
     * 关闭当前server
     */
    public void closeServer() {
        try {
            allChannels.close();
            //allChannels.flush();//close的话channel就会清空，flush还没发现用处
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动server
     */
    public void start(String ports, InterceptorHandler interceptorHandler, HttpServerHandler httpServerHandler) {
        List<Integer> portList = Arrays.stream(ports.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childOption(NioChannelOption.TCP_NODELAY, true);
        bootstrap.childOption(NioChannelOption.SO_REUSEADDR, true);
        bootstrap.childOption(NioChannelOption.SO_KEEPALIVE, false);
        bootstrap.childOption(NioChannelOption.SO_RCVBUF, 2048);
        bootstrap.childOption(NioChannelOption.SO_SNDBUF, 2048);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) {
                ch.pipeline().addLast("codec", new HttpServerCodec());
                ch.pipeline().addLast("aggregator", new HttpObjectAggregator(512 * 1024));
                ch.pipeline().addLast("logging", new FilterLogginglHandler());
                ch.pipeline().addLast("interceptor", interceptorHandler);
                ch.pipeline().addLast("bizHandler", httpServerHandler);
            }
        })
        ;
        for (Integer port : portList) {
            ChannelFuture channelFuture = null;
            try {
                channelFuture = bootstrap.bind(port).syncUninterruptibly().addListener(future -> {
                    String logBanner = "Netty Http Server started on port {}.";
                    LOGGER.info(logBanner, port);
                }).sync();
                // 将channel保存下来
                allChannels.add(channelFuture.channel());
                System.out.printf("serverChannelList" + allChannels);
            } catch (InterruptedException e) {
                //这里只是为了测试用，保证出错后所有启动的端口都停掉
                allChannels.close();
                throw new RuntimeException(e);
            }
            channelFuture.channel().closeFuture().addListener(future -> {
                LOGGER.info("Netty Http Server Start Shutdown ............");
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            });
        }
    }

}