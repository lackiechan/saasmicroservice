package com.tenlnet.framework.monitor.notify;

import com.tenlnet.framework.monitor.service.NotifyService;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.*;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-09-01 17:15
 */
public class WechatNotifier extends AbstractStatusChangeNotifier {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final String DEFAULT_MESSAGE = "*#{instance.registration.name}* (#{instance.id}) is *#{event.statusInfo.status}**";

    private final SpelExpressionParser parser = new SpelExpressionParser();
    private Expression message;
    @Autowired
    private NotifyService notifyService;

    private String msgtype = "markdown";
    private String title = "服务告警";

    private InstanceRepository instanceRepository=null;


    public WechatNotifier(InstanceRepository repositpry) {
        super(repositpry);
        this.instanceRepository=repositpry;
        this.message = parser.parseExpression(DEFAULT_MESSAGE, ParserContext.TEMPLATE_EXPRESSION);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent instanceEvent, Instance instance) {
        String remark=getMessage(instanceEvent,instance);
        logger.info("服务告警：{}",remark);

        return Mono.fromRunnable(() ->notifyService.sendZenTaoNofity("13430375504",title,title,remark));

//        return null;
    }

    private String getMessage(InstanceEvent event,Instance instance) {
        String content="";
        if(event instanceof InstanceRegisteredEvent){
            content=instance.getRegistration().getName()+"实例："+instance.getId()+"实例上线";

        }else if (event instanceof InstanceStatusChangedEvent) {
            Map<String,Object> appInfo= instance.getInfo().getValues();

            String appName="";
            if(appInfo!=null&&appInfo.size()>0){
                Map<String,Object> app= (Map<String, Object>) appInfo.get("app");
                appName= (String) app.get("name");
            }else if(instance.getRegistration()!=null){
                appName=instance.getRegistration().getName();
            }

            String statusDesc="";
            String status=getLastStatus(event.getInstance());

            if("OFFLINE".equals(instance.getStatusInfo().getStatus())){
                statusDesc="下线";
            }else if("UP".equals(instance.getStatusInfo().getStatus())){
                statusDesc="上线";
            }

            content=appName+"实例："+instance.getId()+statusDesc;
        }







        return content;

//        Map<String, Object> root = new HashMap<>();
//        root.put("event", event);
//        root.put("instance", instance);
//        root.put("lastStatus", getLastStatus(event.getInstance()));
//        StandardEvaluationContext context = new StandardEvaluationContext(root);
//        context.addPropertyAccessor(new MapAccessor());
//        return message.getValue(context, String.class);
    }

    @Override
    public Mono<Void> notify(InstanceEvent event) {
//        Mono<Instance> instanceMono= instanceRepository.find(event.getInstance());

//        instanceMono.filter(instance -> sendNotify(event, instance));
//        final String content="";
//        Map<String,Object> appInfo= instance.getInfo().getValues();
//        Map<String,Object> app= (Map<String, Object>) appInfo.get("app");
//        String appName= (String) app.get("name");
//
//
//        String statusDesc="";
//        String status=getLastStatus(event.getInstance());
//
//        if("OFFLINE".equals(status)){
//            statusDesc="下线";
//        }else if("YO".equals(status)){
//            statusDesc="上线";
//        }
//
//        content=appName+"实例："+event.getInstance()+statusDesc;



        return super.notify(event);

    }

    public boolean sendNotify(InstanceEvent event,Instance instance){
        String remark=getMessage(event,instance);
        logger.info("服务告警：{}",remark);
        Mono.fromRunnable(() ->notifyService.sendZenTaoNofity("13430375504",title,title,remark));
        return true;
    }

    @Override
    protected boolean shouldNotify(InstanceEvent event, Instance instance) {
        logger.info("shouldNotify notify event:{}",event.toString());

        if (event instanceof InstanceStatusChangedEvent) {
            InstanceStatusChangedEvent statusChange = (InstanceStatusChangedEvent) event;
            String from = getLastStatus(event.getInstance());
            String to = statusChange.getStatusInfo().getStatus();
            return true;
        }

//        if(event instanceof InstanceRegisteredEvent){
//            return true;
//        }else if (event instanceof InstanceDeregisteredEvent) {
//            return true;
//        }else if (event instanceof InstanceStatusChangedEvent) {
//            InstanceStatusChangedEvent statusChange = (InstanceStatusChangedEvent) event;
//            String from = getLastStatus(event.getInstance());
//            String to = statusChange.getStatusInfo().getStatus();
//            return true;
//        }else if (event instanceof InstanceInfoChangedEvent) {
//
//            return true;
//        }else if (event instanceof InstanceEndpointsDetectedEvent) {
//
//            return true;
//        }

        return false;


    }


}
