package com.atguigu.cloud.apis;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(value = "cloud-payment-service")
@FeignClient(value = "cloud-gateway")
public interface PayFeignApi {

    /**
     * 新增一条支付相关流水记录
     *
     * @param payDTO 支付dto
     * @return {@link ResultData}
     */
    @PostMapping("/pay/add")
    ResultData addPay(@RequestBody PayDTO payDTO);

    /**
     * 按照主键记录查询支付流水信息
     *
     * @param id id
     * @return {@link ResultData}
     */
    @GetMapping("/pay/get/{id}")
    ResultData getPayInfo(@PathVariable("id") Integer id);

    /**
     * openfeign天然支持负载均衡演示
     *
     * @return {@link String}
     */
    @GetMapping(value = "/pay/get/info")
    String loadBalance();


    /**
     * Resilience4j CircuitBreaker 的例子
     *
     * @param id id
     * @return {@link String}
     */
    @GetMapping(value = "/pay/circuit/{id}")
    String payCircuit(@PathVariable("id") Integer id);


    /**
     * Resilience4j Bulkhead 的例子
     *
     * @param id id
     * @return {@link String}
     */
    @GetMapping(value = "/pay/bulkhead/{id}")
    String bulkHead(@PathVariable("id") Integer id);

    /**
     * Resilience4j Ratelimit 的例子
     *
     * @param id id
     * @return {@link String}
     */
    @GetMapping(value = "/pay/ratelimit/{id}")
    String myRatelimit(@PathVariable("id") Integer id);


    /**
     * Micrometer(Sleuth)进行链路监控的例子
     *
     * @param id id
     * @return {@link String}
     */
    @GetMapping(value = "/pay/micrometer/{id}")
    String myMicrometer(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例01
     *
     * @param id id
     * @return {@link ResultData}
     */
    @GetMapping(value = "/pay/gateway/get/{id}")
    ResultData getById(@PathVariable("id") Integer id);

    /**
     * GateWay进行网关测试案例02
     *
     * @return {@link ResultData}
     */
    @GetMapping(value = "/pay/gateway/info")
    ResultData<String> getGatewayInfo();
}
