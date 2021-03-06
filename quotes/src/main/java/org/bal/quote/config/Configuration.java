package org.bal.quote.config;


import brave.rpc.RpcRequest;
import brave.rpc.RpcRuleSampler;
import brave.sampler.Sampler;
import brave.sampler.SamplerFunction;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.sleuth.instrument.rpc.RpcServerSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import static brave.rpc.RpcRequestMatchers.serviceEquals;

@ComponentScan(basePackages = {"org.bal.quote.server", "org.bal.quote.repository"})
@SpringBootConfiguration
public class Configuration {

    @Bean(name = RpcServerSampler.NAME)
    SamplerFunction<RpcRequest> myRpcSampler() {
        return RpcRuleSampler.newBuilder()
                .putRule(serviceEquals("grpc.health.v1.Health"), Sampler.NEVER_SAMPLE).build();
    }
}
