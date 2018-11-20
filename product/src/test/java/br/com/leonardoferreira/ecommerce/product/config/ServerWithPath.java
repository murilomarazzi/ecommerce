package br.com.leonardoferreira.ecommerce.product.config;

import com.netflix.loadbalancer.Server;

public class ServerWithPath extends Server {

    private final String path;

    public ServerWithPath(final String host, final int port, final String path) {
        super(host, port);
        this.path = path;
    }

    @Override
    public String getHost() {
        return String.format("%s:%d/%s", super.getHost(), super.getPort(), path);
    }

    @Override
    public int getPort() {
        return -1;
    }
}
