package org.riskengine.kernel;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
@Slf4j
public class RequestProcessor {
    private final ExecutorService executorService;

    public RequestProcessor() {
        executorService = Executors.newVirtualThreadPerTaskExecutor();
    }


    public <T> T exec(Trx trx) throws Exception {
        T res;
        try {
            Future<Object> future = executorService.submit(trx);
            res = (T) future.get(30_000L, TimeUnit.SECONDS);

        } catch (ExecutionException e) {
            log.error("Execution exception due to {}", ExceptionUtils.getStackTrace(e.getCause()));
            throw new Exception(e.getCause());
        } catch (InterruptedException | TimeoutException e) {
            log.error("exception {}", ExceptionUtils.getStackTrace(e));
            throw new RuntimeException(e);
        }

        return res;
    }


}
