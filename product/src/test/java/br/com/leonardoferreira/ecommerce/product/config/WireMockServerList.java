package br.com.leonardoferreira.ecommerce.product.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractServerList;
import com.netflix.loadbalancer.Server;
import java.util.Collections;
import java.util.List;

public class WireMockServerList extends AbstractServerList<Server> {

    private IClientConfig clientConfig;

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    @Override
    public List<Server> getInitialListOfServers() {
        return Collections.singletonList(new ServerWithPath("localhost", 8888, clientConfig.getClientName()));
    }

    @Override
    public List<Server> getUpdatedListOfServers() {
        return Collections.singletonList(new ServerWithPath("localhost", 8888, clientConfig.getClientName()));
    }

}
