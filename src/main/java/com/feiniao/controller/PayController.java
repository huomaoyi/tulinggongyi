package com.feiniao.controller;

import com.feiniao.utils.HttpUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@RequestMapping("/pay")
@CrossOrigin
public class PayController {

    @RequestMapping("getPayUrl")
    @ResponseBody
    public String getPayUrl(@RequestParam("amount") int payAmount) {
        String url = "https://sandbox-walletapi.onethingpcs.com/api/linktest/tx_generate";
        String parm = "{\"email\":\"774392980@qq.com\",\"to\":\"0x5c949f437ef1cd1fe2f0d6268931ff3f29b04662\",\"value\":\"" + payAmount +"000000000000000000\",\"callback\":\"www.baidu.com\",\"title\":\"transfer test\",\"desc\":\"to yiyanwannian\",\"tx_type\":\"tx_third\",\"sign\":\"ad3e466a20449e2318c50f94fb4954dc\"}";
        String responseData = HttpUtil.sendPostHttpByStr(url,parm);

        return responseData;
    }
}
