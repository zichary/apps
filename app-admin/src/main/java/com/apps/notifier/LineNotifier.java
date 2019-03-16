package com.apps.notifier;

import com.apps.http.LineHttp;
import com.apps.properties.LineProperties;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: SimonYang
 * @Date: 2019-03-06
 * @Version: 1.0.0
 */
@Slf4j
public class LineNotifier extends AbstractEventNotifier {

    @Autowired
    private LineProperties lineProperties;
    private static final String DEFAULT_MESSAGE = "#{application.name} (#{application.id}) is #{to.status}";
    private final SpelExpressionParser parser = new SpelExpressionParser();

    private Expression message;
    private List<String> notifyStatuses = Arrays.asList("UP", "DOWN", "OFFLINE");

    @Autowired
    private LineHttp lineHttp;

    protected LineNotifier(InstanceRepository repository) {
        super(repository);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        if (!lineProperties.isEnabled()) {
            return null;
        }
        this.message = parser.parseExpression(DEFAULT_MESSAGE, ParserContext.TEMPLATE_EXPRESSION);
        try {
            // boot-test (2a87974b) is UP
            String msg = message.getValue(event, String.class);
            lineHttp.post(msg);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
