package me.xueyao;

import com.jd.open.api.sdk.JdException;
import me.xueyao.util.JdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SimonPubApiApplicationTests {

    @Autowired
    private JdUtil jdUtil;
    @Test
    void contextLoads() throws JdException {
        //String clickUrl = jdUtil.getClickUrl();
    }

}
