package com.mitri.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * <p>Title: Springboot-Enhance--com.mitri.service.PushService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017:11 </p>
 * <p>Create DateTime: 2017/11/17 上午10:12 </p>
 *
 * @author Potter
 * @version v2
 */
@Service
public class PushService {

  private DeferredResult<String> deferredResult; //①

  public DeferredResult<String> getAsyncUpdate() {
    deferredResult = new DeferredResult<String>();
    return deferredResult;
  }

  @Scheduled(fixedDelay = 5000)
  public void refresh() {
    if (deferredResult != null) {
      deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
    }
  }

}
